package org.muieer.java.concurrent.async;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
* blog1: https://www.baeldung.com/java-completablefuture
* blog2: https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
* CompletableFuture 是一个简化异步计算的框架
* 调用链的阻塞状态是由头结点决定的，若头结点是异步的，后续节点即使是同步的，也不会阻塞
* */
public class CompletableFutureExample {

    public static void main(String[] args) {
        dealException();
    }

    // 使用 jconsole 观察线程变化：长久以后，看到峰值线程数量 - 活跃线程数量 = 线程池数量(3) + 主线程 + 2 个异步调用线程 = 6
    static void dealException() {

        var executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {}); // 挂起主线程，不让程序立即结束

        var start = Instant.now().toEpochMilli();

        CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("sleep 40s");
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException();
        }).handleAsync((obj, throwable) -> { // 会在当前异步线程的基础上开启一个新的异步线程
            String res = "success";
            try {
                System.out.println("sleep 20s");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (throwable != null) {
//                throw new RuntimeException();
                res = "has throwable";
            }
            callBack(res);
            return obj;
        })
//        .join() // 异步调用使用回调，而不是 join() get() 阻塞调用线程，这样就又变成了同步
        ;

        System.out.println("执行到方法尾部用时 " + (Instant.now().toEpochMilli() - start) + "ms");
    }

    static void callBack(String res) {
        System.out.println("async callBack res: " + res);
    }

    // 异步计算调用链示例
    static void flowDemo() {

        var executorService = Executors.newFixedThreadPool(3);
        var start = Instant.now().toEpochMilli();

        // 异步
        var futureA = executorService.submit(() -> {
            Thread.sleep(15000);
            return "A";
        });

        // 异步
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return futureA.get(); // 因为是异步，所以此处不会阻塞
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 异步，thenApply 是同步操作，但不会阻塞在此处等待上游完成，因为上游是异步的，上游完成时会回调该操作
        future1.thenApply(str -> {
            try {
                // future1 执行结束，触发此操作，也不会阻塞调用被调用线程
                Thread.sleep(20000);
                System.out.println(123 + str);
                return str;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // 同步，此处阻塞，因为上游是同步操作
//        CompletableFuture.completedFuture("blocked").thenApply/*该操作是同步的*/(str -> {
//            try {
//                System.out.println(str);
//                return futureA.get();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        }).thenAcceptAsync(System.out::println);

        // 异步
        var futureB = executorService.submit(() -> {
            Thread.sleep(7000);
            return "B";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                return futureB.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 异步
        var futureC = executorService.submit(() -> {
            Thread.sleep(9000);
            return "C";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                return futureC.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 等待所有异步操作完成：方式一，异步
        CompletableFuture<Void> allFuture = CompletableFuture.allOf(future1, future2, future3);
        allFuture.thenAccept(v -> { // 所有异步执行结束
            try {
                System.out.println("call back all res: " + List.of(future1.get(), future2.get(), future3.get()));
                System.out.println("call back time - start = " + (Instant.now().toEpochMilli() - start));
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 等待所有异步操作完成：方式二，同步
//        List<String> res = Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.toList());
//        System.out.println(res);

        System.out.println("now - start = " + (Instant.now().toEpochMilli() - start));
    }
}
