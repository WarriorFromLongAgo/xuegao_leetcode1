package com.xuegao.数据结构与算法.sort;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.sort
 * <br/> @ClassName：SelectSort
 * <br/> @Description：选择排序
 * <br/> @author：xuegao
 * <br/> @date：2021/03/03 20:13
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] list = {27, 76, 47, 23, 7, 32, 19, 86};
        System.out.println("************选择排序************");
        System.out.println("排序前：");
        display(list);
        System.out.println("");

        System.out.println("排序后：");
        sort(list);
        display(list);
    }

    public static void sort(int[] arr) {
        // 同冒泡，找到len-1个最值就完成了排序
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i; // 用来存储最小元素的索引
            // i+1 是因为前 i 个元素已经是排好序的
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // 交换到前面
            if (minIdx != i) {
                int tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
            }
        }
    }

    /**
     * 遍历打印
     */
    public static void display(int[] list) {
        if (list != null && list.length > 0) {
            for (int num : list) {
                System.out.print(num + " ");
            }
            System.out.println("");
        }
    }
}