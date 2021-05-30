package com.xuegao.面试md.thread;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：Join_ABC
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/5/12 20:30
 */
public class Join_ABC {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t1 = new Thread(new printABC(null), "A");
            Thread t2 = new Thread(new printABC(t1), "B");
            Thread t3 = new Thread(new printABC(t2), "C");
            t1.start();
            t2.start();
            t3.start();
            Thread.sleep(10); //这里是要保证只有t1、t2、t3为一组，进行执行才能保证t1->t2->t3的执行顺序。
        }

    }

    static class printABC implements Runnable {
        private Thread beforeThread;

        public printABC(Thread beforeThread) {
            this.beforeThread = beforeThread;
        }

        @Override
        public void run() {
            if (beforeThread != null) {
                try {
                    beforeThread.join();
                    System.out.print(Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.print(Thread.currentThread().getName());
            }

        }
    }

    // 作者：路人zhang
    // 链接：https://juejin.cn/post/6959078859568316452
    // 来源：掘金
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}