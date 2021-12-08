package com.xuegao.面试md.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/12/8 12:59
 */
public class ComplateFutureABC {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 3; i++) {
            CompletableFuture<Void> complete = CompletableFuture
                    .runAsync(() -> {
                        System.out.print(Thread.currentThread().getName() + " = A = ");
                        long id = Thread.currentThread().getId();
                        System.out.println(id);
                    })
                    .whenComplete((unused, throwable) -> {
                        System.out.print(Thread.currentThread().getName() + " = B = ");
                        long id = Thread.currentThread().getId();
                        System.out.println(id);
                    })
                    .whenComplete((unused, throwable) -> {
                        System.out.print(Thread.currentThread().getName() + " = C = ");
                        long id = Thread.currentThread().getId();
                        System.out.println(id);
                    });
            complete.get();
        }
    }
}