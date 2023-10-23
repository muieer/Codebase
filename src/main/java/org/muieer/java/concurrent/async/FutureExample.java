package org.muieer.java.concurrent.async;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws Exception {
//        demo1(Boolean.parseBoolean(args[0]));
        demo2();
    }

    public static void demo2() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        var future = executorService.submit(() -> {
            Thread.sleep(3000);
            System.out.println("done");
            return "done";
        });
//        future.get();
        System.out.println("continue ...");
    }

    public static void demo1(boolean cancel) throws Exception {
        var futureTask = new MyFutureTask(() -> {
            Thread.sleep(20000);
            System.out.println("done");
            return "done";
        });
        new Thread(futureTask).start();

        System.out.println(futureTask.get(30, TimeUnit.SECONDS));
        // 30 秒后取消任务
        if (cancel) {
            System.out.println("cancel res is " + futureTask.cancel(true));
        }
        // main 线程在此等待 30 秒
        Thread.sleep(30000);
        System.out.println("end!");
    }
}

class MyFutureTask extends FutureTask<String> {

    public MyFutureTask(Callable<String> callable) {
        super(callable);
    }

    @Override
    public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        String res = "";
        try {
            res = super.get(timeout, unit);
        } catch (Exception e){
//            e.printStackTrace();
            res = "retrieves result failure";
        }

        return res;
    }
}
