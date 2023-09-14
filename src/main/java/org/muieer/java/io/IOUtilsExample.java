package org.muieer.java.io;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class IOUtilsExample {


    /*
    * InputStream 是字节输入流，处理的是字节(byte)
    * Reader 是字符输入流，处理的是字符(char)
    *
    * 为什么需要关闭流：https://stackoverflow.com/questions/26541513/why-is-it-good-to-close-an-inputstream
    * 原因：创建一个文件输入流，JVM 会在 OS 内核创建文件句柄，进行 rwx 操作，句柄资源是有限的，需要关闭流回收资源
    * */

    public static final int EOF = -1;

    public static void main(String[] args) {

        StringToInputStream("hello");

    }

    public static void StringToInputStream(String str) {
        try (InputStream inputStream = IOUtils.toInputStream(str, StandardCharsets.UTF_8)) {
            int b;
            while (EOF != (b = inputStream.read())) {
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
