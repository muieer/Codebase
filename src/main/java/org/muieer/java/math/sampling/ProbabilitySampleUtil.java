package org.muieer.java.math.sampling;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
/*
* 适用场景：按照预设概率采样，如果一个值第一次被采样了，那么后续也会被采样，只适用于采样值分布均匀的数据，有严重倾斜的数据不适用
* */
public class ProbabilitySampleUtil implements Serializable {

    private static final HashFunction hashFunction = Hashing.murmur3_128();
    private double fraction; //一个值被采样的概率

    private ProbabilitySampleUtil() {
    }

    /*
     * @param 输入参数为预期采样概率，为 0 即绝不会被采样，为 1 即一定会被采样
     * @return 为 ture 即是采样成功
     * */
    public ProbabilitySampleUtil(double fraction) {
        if (!(fraction < 0.0) && !(fraction > 1.0)) {
            this.fraction = fraction;
        } else {
            throw new IllegalArgumentException("采样概率不能小于 0 或大于 1");
        }
    }

    public boolean sample(String value) {
        return
            (double)hashFunction.hashBytes(value.getBytes(StandardCharsets.UTF_8)).asBytes()[7]
                                                                        >= -128.0 + 256.0 * (1.0 - this.fraction);
    }

    public static void main(String[] args) throws Exception {
        ProbabilitySampleUtil probabilitySampleUtil = new ProbabilitySampleUtil(0.2);
        System.out.println(probabilitySampleUtil.sample("9"));
    }
}