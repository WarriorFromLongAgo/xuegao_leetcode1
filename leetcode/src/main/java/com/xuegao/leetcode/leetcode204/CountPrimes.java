package com.xuegao.leetcode.leetcode204;

import java.util.HashSet;
import java.util.Set;

/**
 * <br/> @PackageName：com.xuegao.leetcode.leetcode204
 * <br/> @ClassName：CountPrimes
 * <br/> @Description：计数质数
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:32
 */
public class CountPrimes {

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();
        int i = countPrimes(49999);
        long endTime = System.currentTimeMillis();
        System.out.println(i);
        System.out.println("时间 = " + (endTime - beginTime));

        long beginTime2 = System.currentTimeMillis();
        int i2 = countPrimes2(49999);
        long endTime2 = System.currentTimeMillis();
        System.out.println(i2);
        System.out.println("时间 = " + (endTime2 - beginTime2));
    }

    // 只有唯一被唯一两个数整除的，1 和 自身
    // 质数是指在大于1的自然数中，除了1和它本身以外不再有其他因数的自然数。
    public static int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        // 这个是从 1 开始，一直加到 n
        for (int i = 2; i < n; i++) {
            // i 大 的数，从2开始自增，与n相除，看余数
            for (int j = 2; j <= i; j++) {
                // i = 自己
                // j = 一个自增的数
                // 如果在 j 自增的时候，可以被其他数整除，切这个数不是自己，那么就有问题
                if (i % j == 0) {
                    // 自己 除以 一个自增的数，可以被整除
                    if (j == i) {
                        // 并且这个数，是自己
                        set.add(i);
                    }
                    // 不是自己，直接break
                    break;
                }
            }
        }
        System.out.println(set);
        return set.size();
    }

    public static int countPrimes2(int n) {
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}