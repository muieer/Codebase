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
* */
public class CompletableFutureExample {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        var start = Instant.now().toEpochMilli();
        // 异步
        var futureA = executorService.submit(() -> {
            Thread.sleep(5000);
            return "A";
        });

        // 异步
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                return futureA.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        // 同步，此处阻塞
        System.out.println("blocked");
        CompletableFuture.completedFuture("blocked").thenApply(str -> {
            try {
                return futureA.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

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

//        CompletableFuture<Void> allFuture = CompletableFuture.allOf(future1, future2, future3);
//        allFuture.get();
//        System.out.println(List.of(future1.get(), future2.get(), future3.get()));
        List<String> res = Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(res);
        System.out.println(Instant.now().toEpochMilli() - start);

    }
}
