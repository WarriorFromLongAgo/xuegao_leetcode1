package com.xuegao.面试md.thread;

import java.util.concurrent.Semaphore;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：SemaphoreABC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/12 20:34
 */
public class SemaphoreABC {
    private static Semaphore A = new Semaphore(1); //因为先执行线程A，所以这里设s1的计数器为1
    private static Semaphore B = new Semaphore(0);
    private static Semaphore C = new Semaphore(0);


    private void printABC(Semaphore currentThread, Semaphore nextThread) {
        for (int i = 0; i < 10; i++) {
            try {
                currentThread.acquire();       //阻塞当前线程，即信号量的计数器减1为0
                System.out.print(Thread.currentThread().getName());
                nextThread.release();          //唤醒下一个线程，即信号量的计数器加1

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreABC printer = new SemaphoreABC();
        new Thread(() -> {
            printer.printABC(A, B);
        }, "A").start();
        Thread.sleep(10);
        new Thread(() -> {
            printer.printABC(B, C);
        }, "B").start();
        Thread.sleep(10);
        new Thread(() -> {
            printer.printABC(C, A);
        }, "C").start();
    }

    // 作者：路人zhang
    // 链接：https://juejin.cn/post/6959078859568316452
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}