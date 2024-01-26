package org.muieer.java.math.sampling;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.sampling.distribution.AliasMethodDiscreteSampler;
import org.apache.commons.rng.sampling.distribution.SharedStateDiscreteSampler;
import org.apache.commons.rng.simple.RandomSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* https://commons.apache.org/proper/commons-rng/userguide/rng.html#Table_of_contents
* https://en.wikipedia.org/wiki/Alias_method
* */
public class AliasMethodDiscreteSamplerExample {

    public static void main(String[] args) {

        UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();
        double[] probabilities = new double[]{0.1D, 0.2D, 0.05D, 0.5D, 0.15D};

        long start = System.currentTimeMillis();
        SharedStateDiscreteSampler sampler = AliasMethodDiscreteSampler.of(rng, probabilities);
        long end = System.currentTimeMillis();
        System.out.println("pre-processing cost " + (end - start) + " ms");

        start = System.currentTimeMillis();
        Map<Integer, List<Integer>> groupMap = Stream.of(new Object[1_000_000])
                .map(e -> sampler.sample())
                .collect(Collectors.groupingBy(Integer::intValue));
        end = System.currentTimeMillis();
        System.out.println("sampling cost " + (end - start) + " ms");

        // 观察采样结果的分布是否与概率表符合
        groupMap.forEach((key, value) -> System.out.println(key + ":" + value.size()));

    }
}
