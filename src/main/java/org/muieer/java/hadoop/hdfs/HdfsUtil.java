package org.muieer.java.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class HdfsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HdfsUtil.class);

    private HdfsUtil() {}

    /*
     * 指数退避算法，等待时间指数增长
     * https://developer.aliyun.com/article/748634
     * */
    public static boolean checkPathExist(FileSystem fs, Path path, int maxRetry, int initialDelaySeconds)  {

        boolean exist = false;
        long retryDelay = initialDelaySeconds * 1000L;

        for (int i = 0; i < maxRetry; ++i) {
            try {
                if (fs.exists(path)) {
                    exist = true;
                    break;
                } else {
                    Thread.sleep(retryDelay);
                    retryDelay *= 2;
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return exist;

    }

    // 创建一个新文件，并写入指定内容到目标路径
    public static void createNewFile(FileSystem fileSystem, String path, String content) throws Exception {
        Path newPath = new Path(path);
        FSDataOutputStream outputStream = fileSystem.create(newPath);
        outputStream.writeBytes(content);
        outputStream.close();
    }

    // 拷贝指定HDFS路径下所有文件到目标HDFS路径
    public static void copy(FileSystem fileSystem, String sourcePath, String targetPath) throws Exception {

        Path source = new Path(sourcePath);
        Path target = new Path(targetPath);

        // 判断源路径是否存在
        if (!fileSystem.exists(source)) {
            throw new RuntimeException("源路径不存在！");
        }

        // 判断目标路径是否存在
        if (!fileSystem.exists(target)) {
            fileSystem.mkdirs(target);
        }

        // 拷贝文件
        FileUtil.copy(fileSystem, source, fileSystem, target, false, true, fileSystem.getConf());
    }

    // 文件移动
    public static void move(FileSystem fileSystem, String from, String to) throws IOException {
        fileSystem.rename(new Path(from), new Path(to));
    }

    // 修改路径本身权限和尽可能修改所能修改的子目录和子文件权限
    public static void modifyCurrentPathAllFileOrDirectoryPermissionBecomeNoLimit(String currentPath, FileSystem fs) {

        Path path = new Path(currentPath);
        FsPermission fsPermission = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL);

        try {
            if (!fs.exists(path)) { return; }
            fs.setPermission(path, fsPermission);
        } catch (Exception e) {
            LOGGER.error("{} 路径权限修改失败", currentPath);
        }

        try {
            for (FileStatus status : fs.listStatus(path)) {
                try {
                    if (status.isFile()) {
                        fs.setPermission(status.getPath(), fsPermission);
                    } else if (status.isDirectory()) {
                        modifyCurrentPathAllFileOrDirectoryPermissionBecomeNoLimit(status.getPath().toUri().getPath(), fs);
                        fs.setPermission(status.getPath(), fsPermission);
                    }
                } catch (Exception e) {
                    LOGGER.error("{} 路径权限修改失败", status.getPath().toUri().getPath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 查看当前目录下，是否存在含有某个名字的文件和目录
    public static boolean containsFileNameInCurrentDirectory(FileSystem fs, String currentPath, String fileName) throws Exception{

        var path = new Path(currentPath);
        var contains = false;

        if (!fs.exists(path)) {
            return false;
        }

        try {
            for (FileStatus status : fs.listStatus(path)) {
                if (status.getPath().getName().contains(fileName)) {
                    contains = true;
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }

        return contains;

    }

    // 得到当前目录下，含有某个名字的文件和目录的 Path 列表
    public static List<Path> getContainsFileNamePathInCurrentDirectory(FileSystem fs, String currentPath, String fileName) throws Exception {
        var path = new Path(currentPath);

        if (!fs.exists(path)) {
            return List.of();
        }

        return Arrays.stream(fs.listStatus(path))
                .map(FileStatus::getPath)
                .filter(fileStatusPath -> fileStatusPath.getName().contains(fileName))
                .collect(toList());
    }

    public static boolean emptyDirectory(FileSystem fs, String currentPath, boolean skipHiddenFile) throws Exception {
        var path = new Path(currentPath);
        if (!fs.exists(path)) {
            return true;
        }
        // 过滤隐藏文件
        return Arrays.stream(fs.listStatus(path))
                .filter(fileStatus -> {
                    if (skipHiddenFile) {
                        return !fileStatus.getPath().getName().startsWith(".");
                    } else {
                        return true;
                    }
                }).count() == 0;
    }

    public static void main(String[] args) throws Exception {

        FileSystem fileSystem = FileSystem.get(new Configuration());
        // 父目录绝对路径
        System.out.println(new Path(args[0]).getParent().toUri().getPath());
        // 文件名
        System.out.println(new Path(args[0]).getName());
        // 是否为空目录
        System.out.println(emptyDirectory(fileSystem, args[0], true));
    }

}
