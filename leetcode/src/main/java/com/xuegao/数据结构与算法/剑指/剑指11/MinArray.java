package com.xuegao.数据结构与算法.剑指.剑指11;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.剑指.剑指11
 * <br/> @ClassName：MinArray
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/21 10:09
 */
public class MinArray {
    public static void main(String[] args) {
        // int[] numbers = {3, 4, 5, 1, 2};
        int[] numbers = {1, 3, 5};
        int i = minArray(numbers);
        System.out.println(i);
    }

    public static int minArray(int[] numbers) {
        if (numbers.length == 1) {
            return numbers[0];
        }
        if (numbers.length < 2) {
            return 0;
        }
        for (int i = 0; i < numbers.length - 1; i++) {
            int min = numbers[i];
            if (min > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }
        return numbers[0];
    }
}