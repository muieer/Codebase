package org.muieer.java.clazz;

public class AnonymousClassExample {

    public static void main(String[] args) {
        instantiation();
    }

    // 抽象类的实例化
    public static void instantiation() {
        System.out.println(new Animal() {});
    }
}

abstract class Animal {

    @Override
    public String toString() {
        return "Animal";
    }
}
