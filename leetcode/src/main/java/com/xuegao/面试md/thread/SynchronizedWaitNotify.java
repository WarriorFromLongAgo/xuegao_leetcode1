package com.xuegao.面试md.thread;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：SynchronizedWaitNotify
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/12 20:16
 */
public class SynchronizedWaitNotify {

    private int num;
    private static final Object LOCK = new Object();

    private void printABC(int targetNum) {
        synchronized (LOCK) {
            while (num % 3 != targetNum) {    //想想这里为什么不能用if代替while，想不起来可以看公众号上一篇文章
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            num++;
            System.out.print(Thread.currentThread().getName());
            LOCK.notifyAll();
        }
    }

    private void printABC2(int targetNum) {
        for (int i = 0; i < 10; i++) {
            synchronized (LOCK) {
                while (num % 3 != targetNum) { //想想这里为什么不能用if代替，想不起来可以看公众号上一篇文章
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                num++;
                System.out.print(Thread.currentThread().getName());
                LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedWaitNotify  synchronizedWaitNotify = new SynchronizedWaitNotify ();
        new Thread(() -> {
            synchronizedWaitNotify.printABC(0);
        }, "A").start();
        new Thread(() -> {
            synchronizedWaitNotify.printABC(1);
        }, "B").start();
        new Thread(() -> {
            synchronizedWaitNotify.printABC(2);
        }, "C").start();

        System.out.println();

        new Thread(() -> {
            synchronizedWaitNotify.printABC2(0);
        }, "A").start();
        new Thread(() -> {
            synchronizedWaitNotify.printABC2(1);
        }, "B").start();
        new Thread(() -> {
            synchronizedWaitNotify.printABC2(2);
        }, "C").start();
    }

    // 作者：路人zhang
    // 链接：https://juejin.cn/post/6959078859568316452
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}