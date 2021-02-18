package com.xuegao.数据结构与算法.剑指.剑指15;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指15
 * <br/> @ClassName：HammingWeight
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/21 15:03
 */
public class HammingWeight {
    public static void main(String[] args) {
        int i = HammingWeight(3);
        System.out.println(i);

        int i1 = HammingWeight1(3);
        System.out.println(i1);
        // int i1 = HammingWeight2(3);
        // int i2 = HammingWeight3(5);
    }

    public static int HammingWeight(int n) {
        int res = 0;

        while (n != 0) {
            res += n & 1;
            // 直接右移一位，去掉最右边的一位数
            n >>>= 1;
        }
        return res;
    }

    public static int HammingWeight1(int n) {
        int res = Integer.bitCount(n);
        return res;
    }

    public static int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            // 将最右边的 1 变成 0
            n &= n - 1;
        }
        return res;
    }

    public static int HammingWeight2(int n) {
        System.out.println(n);
        int i = n >>> 1;
        System.out.println(n);
        System.out.println(i);
        i = n >>> 1;
        System.out.println(n);
        System.out.println(i);
        i = n >>> 1;
        System.out.println(n);
        System.out.println(i);
        return 0;
    }

    public static int HammingWeight3(int n) {
        System.out.println(n);
        n >>>= 1;
        System.out.println(n);
        n >>>= 1;
        System.out.println(n);
        n >>>= 1;
        System.out.println(n);
        return 0;
    }
}