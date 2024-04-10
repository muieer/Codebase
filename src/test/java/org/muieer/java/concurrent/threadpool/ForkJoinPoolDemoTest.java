package org.muieer.java.concurrent.threadpool;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ForkJoinPoolDemoTest {

    @Test
    void arraySum() {
        var obj = new ForkJoinPoolDemo();
        //        int[] arr = obj.arrayAssignment(new int[18]);
        int[] arr = obj.arrayAssignment(new int[1_000_0000]);
        assertEquals(Arrays.stream(arr).sum(), obj.arraySum(arr));
    }

    @Test
    void arrayAssignment() {
        var obj = new ForkJoinPoolDemo();
        int[] arrA = obj.arrayAssignment(new int[1_000_0000]);
        int[] arrB = obj.arrayAssignment(new int[1_000_0000]);
        assertNotEquals(arrA[999999], arrB[999999]);
    }
}
