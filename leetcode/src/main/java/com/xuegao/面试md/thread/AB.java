package com.xuegao.面试md.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：AB
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/04/12 17:26
 */
public class AB {
    private static int status = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new A()).start();
        new Thread(new B()).start();
    }

    static class A implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (status % 2 == 0) {
                        System.out.print("A");
                        status++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class B implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (status % 2 == 1) {
                        System.out.print("B");
                        status++;
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}