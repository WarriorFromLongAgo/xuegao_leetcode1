package com.xuegao.剑指.剑指17;

import java.util.Arrays;

/**
 * <br/> @PackageName：com.xuegao.剑指.剑指17
 * <br/> @ClassName：PrintNumbers
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/21 17:09
 */
public class PrintNumbers {
    public static void main(String[] args) {
        int[] ints = printNumbers(2);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] printNumbers(int n) {
        int num = 1;
        for (int i = 0; i < n; i++) {
            num = num * 10;
        }
        int[] resultArr = new int[num - 1];
        int result = 0;
        for (; ; ) {
            result++;
            if (result / num == 1) {
                return resultArr;
            }
            resultArr[result - 1] = result;
        }
    }
}