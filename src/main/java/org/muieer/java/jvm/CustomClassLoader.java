package org.muieer.java.jvm;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class CustomClassLoader extends ClassLoader {

    private FileSystem fileSystem;
    private String jarPath;
    private Map<String, Class<?>> loadClasses;

    public CustomClassLoader(String jarPath) throws Exception {
        fileSystem = FileSystem.get(new Configuration());
        this.jarPath = jarPath;
        this.loadClasses = new ConcurrentHashMap<>();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> clazz = loadClasses.get(name);
        if (clazz != null) {
            return clazz;
        }

        byte[] classData = getClassBytes2(name);
        if (classData == null) {
            throw new ClassNotFoundException("Class " + name + " not found");
        }

        clazz = defineClass(name, classData, 0, classData.length);
        loadClasses.put(name, clazz);

        return clazz;
    }

    private byte[] getClassBytes2(String className) {

        byte[] classBytes;
        String classPath = className.replace('.', '/') + ".class";

        try (
                ZipFile zipFile = new ZipFile(jarPath)
        ) {
            ZipEntry entry = zipFile.getEntry(classPath);
            try (
                    InputStream zipFileInputStream = zipFile.getInputStream(entry)
            ) {
                classBytes = IOUtils.toByteArray(zipFileInputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return classBytes;
    }

    private byte[] getClassBytes(String className) {

        byte[] classBytes = null;
        String classPath = className.replace('.', '/') + ".class";

        try (
                FSDataInputStream fsDataInputStream = fileSystem.open(new Path(jarPath));
                ZipInputStream zipInputStream = new ZipInputStream(fsDataInputStream);
        ) {
            while (true) {
                ZipEntry entry = zipInputStream.getNextEntry();

                if (entry == null) {
                    break;
                }

                if (entry.getName().equals(classPath)) {
                    classBytes = IOUtils.toByteArray(zipInputStream);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return classBytes;
    }

    // 谨慎重写此方法，要遵循双亲委派模型的类加载原则，loadClass 内部会调用 findClass() 方法
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    public static void main(String[] args) throws Exception {

        CustomClassLoader customClassLoader = new CustomClassLoader("/private/tmp/codebase-1.0-SNAPSHOT.jar");

        Class<?> windowsClass = customClassLoader.loadClass("org.muieer.java.jvm.os.Windows");
        OperatingSystem windows = (OperatingSystem)windowsClass.getDeclaredConstructor().newInstance();
        System.out.println(windows.getOsName());
        System.out.println(windows.getVersion());

        Class<?> MacOSClass = customClassLoader.loadClass("org.muieer.java.jvm.os.MacOS");
        OperatingSystem MacOS = (OperatingSystem)MacOSClass.getDeclaredConstructor().newInstance();
        System.out.println(MacOS.getOsName());
        System.out.println(MacOS.getVersion());

    }
}
