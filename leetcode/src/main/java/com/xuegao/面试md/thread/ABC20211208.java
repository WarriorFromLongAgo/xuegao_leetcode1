package com.xuegao.面试md.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/12/8 14:06
 */
public class ABC20211208 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    if (atomicInteger.get() % 3 == 0) {
                        System.out.println(Thread.currentThread().getName() + " A ");
                        atomicInteger.incrementAndGet();
                    } else {
                        i--;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    if (atomicInteger.get() % 3 == 1) {
                        System.out.println(Thread.currentThread().getName() + " B ");
                        atomicInteger.incrementAndGet();
                    } else {
                        i--;
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    if (atomicInteger.get() % 3 == 2) {
                        System.out.println(Thread.currentThread().getName() + " C ");
                        atomicInteger.incrementAndGet();
                    } else {
                        i--;
                    }
                }
            }
        }).start();
    }
}