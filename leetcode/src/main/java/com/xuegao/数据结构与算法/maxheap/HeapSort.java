package com.xuegao.数据结构与算法.maxheap;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.maxheap
 * <br/> @ClassName：HeapSort
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/03 20:09
 */
public class HeapSort {
    // 堆排序
    // 堆排序原理：简而言之，就是利用大顶堆和小顶堆的特性，不断取出堆顶（堆中元素的最值），然后再使堆平衡。
    // 构建最大堆：借鉴上面构建堆的思路，从最后一个不是叶子节点的节点开始下沉
    // 取出堆顶：不是真取出，而是通过交换堆顶（arr[0]）到堆尾（arr[i]）实现出堆顶 ==> 大顶堆的排序结果是从小到大
    // 新堆再平衡：由于上一步已经将堆尾换到了堆首，所以直接再下沉就行
    // 注意：这里采取的是数组从0开始放，即 idx 位置的 parent = idx / 2，leftChild = idx 2，rightChild = idx 2 + 1
    public void sort(int[] arr) {
        int length = arr.length;

        // 1.构建最大堆：从最后一个不是叶子节点的节点开始下沉
        // i=(length-1)/2，表示获取最后一个叶子节点的父亲，即最后一个不是叶子节点的节点
        for (int i = (length - 1) / 2; i >= 0; i--) {
            siftDown(arr, length, i);
        }

        for (int i = length - 1; i > 0; i--) {
            // 2.取出堆顶：通过交换堆顶（arr[0]）到堆尾（arr[i]），实现出堆顶
            swap(arr, 0, i);
            // 3.新堆再平衡：上一步已经将堆尾换到了堆首，所以直接再下沉就行
            // 注：可以看到这里新堆的长度变成了i（比之前少了1）
            siftDown(arr, i, 0);
        }
    }

    private void siftDown(int[] arr, int length, int idx) {
        // 下沉到叶节点，就没有儿子了，就不用下沉了
        // 这里用的leftChild（2*idx），因为如果左儿子都超过length了，右儿子也一定超过了
        while (2 * idx < length) {

            // 判断左儿子和右儿子哪个大
            int j = 2 * idx;
            if (j + 1 < length && arr[j + 1] > arr[j]) {
                j++; // 如果右儿子大，就j++
            }
            // 判断父亲是否比儿子大
            if (arr[idx] >= arr[j]) {
                break;
            }

            // 父亲小于儿子，那么就交换父子
            swap(arr, idx, j);
            // 更新父亲idx，继续去下一棵子树判断下沉
            idx = j;
        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int t = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = t;
    }
}