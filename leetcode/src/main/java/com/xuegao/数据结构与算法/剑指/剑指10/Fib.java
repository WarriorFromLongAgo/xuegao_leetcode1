package com.xuegao.数据结构与算法.剑指.剑指10;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指10
 * <br/> @ClassName：Fib
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/21 18:01
 */
public class Fib {
    public static void main(String[] args) {
        int fib = fib(100);
        System.out.println(fib);

        // double v = fib2(10);
        // System.out.println(v);

        // double v1 = fib3(2);
        // System.out.println(v1);
    }

    public static int fib(int n) {
        if (n == 2 || n == 3) {
            return 1;
        }
        if (n > 2) {
            return fib(n - 1) + fib(n - 2);
        }
        return 0;
    }

    public static double fib2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n > 1) {
            return fib2(n - 1) * 4 + 0.65;
        }
        return 0;
    }

    public static double fib3(int n) {
        if (n == 1) {
            return 1;
        }
        double[] dp = new double[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] * 4 + 0.65;
        }
        return dp[n - 1];
    }
}