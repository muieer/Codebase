package org.muieer.java.jvm;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.util.HexFormat;

/*
* 将 class 文件用 16 进制输出
* 8 个字节为一行
* */
public class ClassFileToHex {

    public static void main(String[] args) throws Exception {

        FileSystem fs = FileSystem.get(new Configuration());
        FSDataInputStream inputStream = fs.open(new Path(args[0]));

        HexFormat hexFormat = HexFormat.ofDelimiter(" ").withPrefix("").withUpperCase();
        byte[] buffer = new byte[8];

        for (int i = 1; inputStream.read(buffer) != -1; i++) {
            System.out.println(String.format(/*左对齐*/"第 %-2d 行,", i) + hexFormat.formatHex(buffer));
        }

    }

}
