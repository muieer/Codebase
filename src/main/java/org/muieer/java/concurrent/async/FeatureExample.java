package org.muieer.java.concurrent.async;

import java.util.concurrent.*;

public class FeatureExample {

    public static void main(String[] args) throws Exception{

        var featureTask = new MyFeatureTask(() -> {
            Thread.sleep(20000);
            System.out.println("done");
            return "done";
        });
        new Thread(featureTask).start();

        System.out.println(featureTask.get(30, TimeUnit.SECONDS));
        // 30 秒后取消任务
        if (Boolean.parseBoolean(args[0])) {
            System.out.println("cancel res is " + featureTask.cancel(true));
        }
        // main 线程在此等待 30 秒
        Thread.sleep(30000);
        System.out.println("end!");
    }
}

class MyFeatureTask extends FutureTask<String> {

    public MyFeatureTask(Callable<String> callable) {
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
