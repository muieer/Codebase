package org.muieer.java.gc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.HashMap;

import static java.lang.System.out;

/*
* -XX:-UseCompressedOops 不开启普通对象指针压缩
* */
public class JOLDemo {

    public static void main(String[] args) {
        out.println(VM.current().details());
        out.println(ClassLayout.parseClass(A.class).toPrintable());
        out.println(ClassLayout.parseClass(String.class).toPrintable());
    }

    public static class A {
        Object point = new Object();
    }
}
