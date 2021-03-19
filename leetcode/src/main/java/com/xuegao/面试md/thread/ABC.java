package com.xuegao.面试md.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：ABC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/19 20:36
 */
public class ABC {
    public static void main(String[] args) {
        new A().start();
        new B().start();
        new C().start();
    }

    private static Lock lock = new ReentrantLock();
    //通过state的值来确定是否打印
    private static int state = 0;

    static class A extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 0) {
                        System.out.print("A");
                        state++;
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

    static class B extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 1) {
                        System.out.print("B");
                        state++;
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

    static class C extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; ) {
                try {
                    lock.lock();
                    while (state % 3 == 2) {
                        System.out.print("C");
                        state++;
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
