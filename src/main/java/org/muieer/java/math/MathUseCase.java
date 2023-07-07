package org.muieer.java.math;

import com.google.common.math.IntMath;

import java.util.Random;

public class MathUseCase {

    static final Random random = new Random();

    public static void main(String[] args) {

        powerOfTwoDemo();

    }

    static void powerOfTwoDemo() {

        int x = random.nextInt(2000);
        System.out.println("x is " + x);

        // 是否 2 的 n 次幂
        System.out.println("x is power of two:" + IntMath.isPowerOfTwo(x));
        // 大于等于给定数的最小 2 的 n 次幂
        System.out.println("the smallest next power of two of x is " + IntMath.ceilingPowerOfTwo(x));
    }

}
