package org.muieer.java.jvm;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.util.HexFormat;

/*
* 将 class 文件用 16 进制输出
* 8 个字节为一行
* Mac 平台开源 16 进制编辑器：https://github.com/HexFiend/HexFiend.git
* */
public class ClassFileToHex {

    public static void main(String[] args) throws Exception {

        FileSystem fs = FileSystem.get(new Configuration());
        FSDataInputStream inputStream = fs.open(new Path(args[0]));

        HexFormat hexFormat = HexFormat.ofDelimiter(" ").withPrefix("").withUpperCase();
        byte[] buffer = new byte[8];

        for (int i = 1; inputStream.read(buffer) != -1; i++) {
            /*
            * 第 1 行，CA FE BA BE 00 00 00 3D
            * 魔数是 CAFEBABE，共 4 个字节
            * minor_version 是 0，共 2 个字节
            * major_version 是 61，说明是 Java 17，共 2 个字节
            * */
            System.out.println(String.format(/*左对齐，宽度为 2*/"第 %-2d 行, ", i) + hexFormat.formatHex(buffer));
        }

    }

}
