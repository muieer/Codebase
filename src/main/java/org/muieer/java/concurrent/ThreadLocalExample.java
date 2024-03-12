package org.muieer.java.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadLocalExample {

    public static void main(String[] args) {
        //        unShareVariable();
        shareVariable();
    }

    public static void unShareVariable() {
        ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
        var task =
                new Runnable() {
                    @Override
                    public void run() {
                        Thread thread = Thread.currentThread();
                        System.out.printf(
                                "thread id %s, threadLocal value %d%n",
                                thread.getId(), threadLocal.get());
                        threadLocal.set(threadLocal.get() + 1);
                    }
                };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(task, 2, 2, TimeUnit.SECONDS);
    }

    public static void shareVariable() {
        var task =
                new Runnable() {
                    int value = 0;

                    @Override
                    public void run() {
                        Thread thread = Thread.currentThread();
                        System.out.printf(
                                "thread id %s, threadLocal value %d%n", thread.getId(), value);
                        value++;
                    }
                };
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(task, 2, 2, TimeUnit.SECONDS);
    }
}
