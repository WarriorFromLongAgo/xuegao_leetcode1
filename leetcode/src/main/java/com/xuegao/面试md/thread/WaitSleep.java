package com.xuegao.面试md.thread;

/**
 * <br/> @PackageName：com.xuegao.面试md.thread
 * <br/> @ClassName：WaitSleep
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/02/19 18:45
 */
public class WaitSleep {
    public static void main(String[] args) {
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    public static class Thread1 implements Runnable {

        @Override
        public void run() {

            synchronized (WaitSleep.class) {
                System.out.println("进入thread1...");
                System.out.println("thread1是等待...");
                try {
                    //调用wait()方法，线程会放弃对象锁，进入等待此对象的等待锁定池
                    WaitSleep.class.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("thread1正在进行 ....");
                System.out.println("thread1结束!!!");
            }
        }


    }

    private static class Thread2 implements Runnable {

        @Override
        public void run() {
            synchronized (WaitSleep.class) {
                System.out.println("进入thread2...");
                System.out.println("thread2是睡眠");
                //只有针对此对象调用notify()方法后本线程才进入对象锁定池准备获取对象锁进入运行状态。
                WaitSleep.class.notify();

                //==================
                //区别
                //如果我们把代码：TestD.class.notify();给注释掉，即TestD.class调用了wait()方法，但是没有调用notify()
                //方法，则线程永远处于挂起状态。
            }

            try {
                //sleep()方法导致了程序暂停执行指定的时间，让出cpu该其他线程，
                //但是他的监控状态依然保持者，当指定的时间到了又会自动恢复运行状态。
                //在调用sleep()方法的过程中，线程不会释放对象锁。
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread2正在进行....");
            System.out.println("thread2结束!!!");
        }
    }

}

