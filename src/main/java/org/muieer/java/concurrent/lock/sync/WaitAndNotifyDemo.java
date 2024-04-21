package org.muieer.java.concurrent.lock.sync;

public class WaitAndNotifyDemo {
    private int count = 0;

    public static void main(String[] args) throws Exception {

        var obj = new WaitAndNotifyDemo();
        var runnable =
                new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 1; i <= 5; i++) {
                            System.out.printf(
                                    "Thread %s, loop index is %d\n",
                                    Thread.currentThread().getName(), i);
                            obj.plusCount();
                        }
                    }
                };

        var t1 = new Thread(null, runnable, "t1");
        var t2 = new Thread(null, runnable, "t2");

        t2.start();
        t1.start();

        t1.join();
        t2.join();

        System.out.printf("obj count is %d\n", obj.count);
    }

    private synchronized void plusCount() {
        try {
            if (count <= 5) {
                if (Thread.currentThread().getName().equals("t1")) {
                    System.out.printf(
                            "Thread %s ++, count is %d\n",
                            Thread.currentThread().getName(), ++count);
                    if (count == 5) {
                        System.out.printf(
                                "Thread %s notify all\n", Thread.currentThread().getName());
                        this.notifyAll();
                    }
                }
                if (Thread.currentThread().getName().equals("t2")) {
                    System.out.printf("Thread %s wait\n", Thread.currentThread().getName());
                    this.wait(); // 调用 wait 方法挂起当前线程，被移出 this 对象的等待集之后，重新获取 this 对象的锁后，会继续执行后续代码
                    System.out.printf(
                            "Thread %s continuing, ++, count is %d\n",
                            Thread.currentThread().getName(), ++count);
                }
            } else {
                System.out.printf(
                        "Thread %s ++, count is %d\n", Thread.currentThread().getName(), ++count);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
