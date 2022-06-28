package com.xuegao.数据结构与算法.其他算法.数列之和;

import com.xuegao.数据结构与算法.断言.AssertUtil;

public class shulie_sum {
    // 输入只有一行，包含两个用空格分开的整数M和N( 0 < M < 10, 0 < N < 100000 )

    // M表示数列中每个数各位数上的值，
    // N表示数列包含的数的个数。

    public static void main(String[] args) {
        int m = 2;
        int n = 5;
        double temp = temp(m, n);
        AssertUtil.isTrue(temp == 24690D);

        AssertUtil.isTrue(temp(2, 6) == 246912D);

        AssertUtil.isTrue(temp(3, 7) == 3703701D);
    }

    public static double temp(int m, int n) {
        if (0 >= m || m >= 10) {
            throw new RuntimeException("m 输入异常");
        }
        if (0 >= n || n >= 100000) {
            throw new RuntimeException("n 输入异常");
        }
        double result = 0;
        int multiply = 1;
        for (int i = n; i > 0; i--, n--) {
            // 个位数是 n 个 m 相加，乘以 multiply（1）

            // 十位数也是这样，但是要 * multiply（10），同时减少一个数

            // 百位数也是这样，但是要 * multiply（100），同时减少一个数
            int temp = (m * n) * multiply;
            result = result + temp;
            multiply = multiply * 10;
        }
        System.out.println("result = " + result);
        return result;
    }
}
