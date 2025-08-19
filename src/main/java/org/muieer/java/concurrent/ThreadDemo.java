package org.muieer.java.concurrent;

public class ThreadDemo {

    public static void main(String[] args) {
        daemonThread();
    }

    /*
    * 用户线程执行结束时，守护线程主动退出
    * */
    private static void daemonThread() {
        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println("daemonThread 执行中，线程名: " + Thread.currentThread().getName());
                sleepNSeconds(2);
            }
        }, "background thread");
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s 线程执行中, 执行次数 %d\n",  Thread.currentThread().getName(), i);
            sleepNSeconds(1);
        }
    }

    private static void sleepNSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {}
    }
}
