package com.xuegao.面试md.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：Lock_ABC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/12 20:32
 */
public class Lock_ABC {

    private int num;   // 当前状态值：保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();


    private void printABC(int targetNum) {
        for (int i = 0; i < 10; ) {
            try {
                lock.lock();
                if (num % 3 == targetNum) {
                    num++;
                    i++;
                    System.out.print(Thread.currentThread().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Lock_ABC lockABC = new Lock_ABC();

        new Thread(() -> {
            lockABC.printABC(0);
        }, "A").start();

        new Thread(() -> {
            lockABC.printABC(1);
        }, "B").start();

        new Thread(() -> {
            lockABC.printABC(2);
        }, "C").start();
    }

    // 作者：路人zhang
    // 链接：https://juejin.cn/post/6959078859568316452
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}