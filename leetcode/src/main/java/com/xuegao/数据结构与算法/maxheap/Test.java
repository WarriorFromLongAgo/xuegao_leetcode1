package com.xuegao.数据结构与算法.maxheap;

import java.util.Random;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.maxheap
 * <br/> @ClassName：Test
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/03 20:06
 */
public class Test {
    public static void main(String[] args) {
        int n = 10000000;

        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        // 1.向堆中添加一百万个随机数
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(Integer.MAX_VALUE));
        }

        // 2.不断从最大堆中取出堆顶  --> 从大到小
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.extractMax();
        }

        // 3.验证取出的元素是否按照从大到小排列
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < arr[i + 1]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap Success!");
    }
}