package com.xuegao.面试md.thread;

public class ThreadSleep2 {

    public static void main(String[] args) {
        System.out.println("begin our test");
        ThreadSleep22 sleep = new ThreadSleep22();
        try {
            Thread thread1 = new Thread(sleep, "路人甲");
            Thread thread2 = new Thread(sleep, "路人乙");
            thread1.start();
            thread2.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("test is over");
    }


}

class ThreadSleep22 implements Runnable {

    int count = 0;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " say : hello sleep !!");
        count();

    }

    public void count() {
        while (count < 20) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " say : count is " + count);
                try {
                    count++;
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }
}

