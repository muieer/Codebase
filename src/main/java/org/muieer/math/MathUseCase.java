package org.muieer.math;

import com.google.common.math.IntMath;
import lombok.extern.java.Log;

import java.util.Random;

@Log
public class MathUseCase {

    static final Random random = new Random();

    public static void main(String[] args) {

        powerOfTwoDemo();

    }

    static void powerOfTwoDemo() {

        int x = random.nextInt(2000);
        log.info("x is " + x);

        // 是否 2 的 n 次幂
        log.info("x is power of two:" + IntMath.isPowerOfTwo(x));
        // 大于等于给定数的最小 2 的 n 次幂
        log.info("the smallest next power of two of x is " + IntMath.ceilingPowerOfTwo(x));
    }

}
