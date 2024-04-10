package org.muieer.java.concurrent.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * https://www.baeldung.com/java-fork-join
 * ForkJoinPool 适合可以将任务分治处理的场景
 * */
public class ForkJoinPoolDemo {

    private static final int THRESHOLD = 9;

    public long arraySum(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ArraySumTask(array));
    }

    public int[] arrayAssignment(int[] array) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new ArrayAssignmentTask(array));
        return array;
    }

    public static class ArraySumTask extends RecursiveTask<Long> {

        private final int[] array;
        private final int low;
        private final int high;

        public ArraySumTask(int[] array) {
            this.array = array;
            this.low = 0;
            this.high = array.length - 1;
        }

        public ArraySumTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Long compute() {
            if (high - low > THRESHOLD) {
                //                return ForkJoinTask.invokeAll(createSubtasks()).stream()
                //                        .map(
                //                                task -> {
                //                                    Long sum = task.join();
                //                                    System.out.println(sum);
                //                                    return sum;
                //                                })
                //                        .mapToLong(value -> value)
                //                        .sum();
                return ForkJoinTask.invokeAll(createSubtasks()).stream()
                        .mapToLong(ForkJoinTask::join)
                        .sum();
            } else {
                return sum();
            }
        }

        private List<ArraySumTask> createSubtasks() {
            List<ArraySumTask> list = new ArrayList<>();
            int mid = low + ((high - low) >> 1);
            list.add(new ArraySumTask(array, low, mid));
            list.add(new ArraySumTask(array, mid + 1, high));
            return list;
        }

        private long sum() {
            long res = 0;
            for (int i = low; i <= high; i++) {
                res += array[i];
            }
            return res;
        }
    }

    public static class ArrayAssignmentTask extends RecursiveAction {

        private final int[] array;
        private final int low;
        private final int high;

        public ArrayAssignmentTask(int[] array) {
            this.array = array;
            this.low = 0;
            this.high = array.length - 1;
        }

        public ArrayAssignmentTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low > THRESHOLD) { // 将一个大任务切分为两个小任务
                ForkJoinTask.invokeAll(createSubtasks());
            } else { // 最小的处理单元
                assignment();
            }
        }

        private List<ArrayAssignmentTask> createSubtasks() {
            List<ArrayAssignmentTask> list = new ArrayList<>();
            int mid = low + ((high - low) >> 1);
            list.add(new ArrayAssignmentTask(array, low, mid));
            list.add(new ArrayAssignmentTask(array, mid + 1, high));
            return list;
        }

        private void assignment() {
            for (int i = low; i <= high; i++) {
                array[i] = ThreadLocalRandom.current().nextInt(256) - 128;
            }
        }
    }
}
