package com.xuegao.数据结构与算法.剑指.剑指16;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指16
 * <br/> @ClassName：MyPow
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/21 15:56
 */
public class MyPow {
    public static void main(String[] args) {
        double x = 2.00000;
        int n = -3;
        // double v = myPow(x, n);
        double v = myPow(x, n);
        System.out.println(v);

        double pow = Math.pow(x, n);
        System.out.println(pow);
    }

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        long b = n;
        double result = 1.0;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                result *= x;
            }
            x *= x;
            b >>= 1;
        }
        return result;
    }

    public static double myPow1(double x, int n) {
        double result = x * x;
        n--;
        n--;
        while (n != 0) {
            result = x * result;
            n--;
        }
        return result;
    }

    public static double myPow2(double x, int n) {
        double result = 1 / (x * x);
        n++;
        n++;

        while (n != 0) {
            result = 1 / x * result;
            n++;
        }
        return result;
    }
}