package com.xuegao.面试md.thread;

import java.util.concurrent.Semaphore;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：ABC2
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/19 20:45
 */
public class ABC2 {
    public static void main(String[] args) {
        new A().start();
        new B().start();
        new C().start();

    }

    // 以A开始的信号量,初始信号量数量为1
    private static Semaphore A = new Semaphore(1);
    // B、C信号量,A完成后开始,初始信号数量为0
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);

    static class A extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                    A.acquire();
                    System.out.println("A");
                    // B释放信号，B信号量加1（初始为0），此时可以获取B信号量
                    B.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class B extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    B.acquire();
                    System.out.println("B");
                    C.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class C extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    C.acquire();
                    System.out.println("C");
                    A.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}