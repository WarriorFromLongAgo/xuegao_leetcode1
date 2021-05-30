package com.xuegao.面试md.thread;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：SynchronizedWaitNotify
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/12 20:16
 */
public class SynchronizedWaitNotify3 {

    private int num;
    private static final Object LOCK = new Object();
    private int maxnum = 10;

    private void printABC(int targetNum) {
        while (true) {
            synchronized (LOCK) {
                while (num % 3 != targetNum) { //想想这里为什么不能用if代替，想不起来可以看公众号上一篇文章
                    if (num >= maxnum) {
                        break;
                    }
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                if (num >= maxnum) {
                    break;
                }
                num++;
                System.out.println(Thread.currentThread().getName() + ": " + num);
                LOCK.notifyAll();
            }
        }

    }

    public static void main(String[] args) {
        SynchronizedWaitNotify3 synchronizedWaitNotify3 = new SynchronizedWaitNotify3();
        new Thread(() -> {
            synchronizedWaitNotify3.printABC(0);
        }, "thread1").start();
        new Thread(() -> {
            synchronizedWaitNotify3.printABC(1);
        }, "thread2").start();
        new Thread(() -> {
            synchronizedWaitNotify3.printABC(2);
        }, "thread3").start();
    }
    // 作者：路人zhang
    // 链接：https://juejin.cn/post/6959078859568316452
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}