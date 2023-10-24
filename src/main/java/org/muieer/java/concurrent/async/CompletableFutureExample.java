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
* https://www.baeldung.com/java-completablefuture
* CompletableFuture 是一个简化异步计算的框架
* 调用链的阻塞状态是由头结点决定的，若头结点是异步的，后续节点即使是同步的，也不会阻塞
* */
public class CompletableFutureExample {

    public static void main(String[] args) throws Exception {

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
