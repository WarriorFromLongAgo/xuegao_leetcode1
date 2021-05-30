package com.xuegao.面试md.thread;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：SynchronizedWaitNotify
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/12 20:16
 */
public class SynchronizedWaitNotify2 {

    private final Object monitor = new Object();
    private volatile int count;

    SynchronizedWaitNotify2(int initCount) {
        this.count = initCount;
    }

    private void printOddEven() {
        synchronized (monitor) {
            while (count < 10) {
                try {
                    System.out.print(Thread.currentThread().getName() + "：");
                    System.out.println(++count);
                    monitor.notifyAll();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            monitor.notifyAll();
            // 防止count=10后，while()循环不再执行，有子线程被阻塞未被唤醒，导致主线程不能退出
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SynchronizedWaitNotify2 synchronizedWaitNotify2 = new SynchronizedWaitNotify2(0);
        new Thread(synchronizedWaitNotify2::printOddEven, "odd").start();
        Thread.sleep(10); //为了保证线程odd先拿到锁
        new Thread(synchronizedWaitNotify2::printOddEven, "even").start();
    }

    // 作者：路人zhang
    // 链接：https://juejin.cn/post/6959078859568316452
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}