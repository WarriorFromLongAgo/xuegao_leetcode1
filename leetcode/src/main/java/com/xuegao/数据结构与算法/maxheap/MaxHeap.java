package com.xuegao.数据结构与算法.maxheap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.maxheap
 * <br/> @ClassName：MaxHeap
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/03/03 20:02
 */
public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    public MaxHeap(E[] arr) {
        data = new ArrayList<>(Arrays.asList(arr));
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftUp(i);
        }
    }

    // 堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 堆中元素个数
    public int size() {
        return this.data.size();
    }

    // 返回idx位置元素的父节点
    // 注：这里从0放起（parent = (i - 1）/2 )，若是从1放起（parent = i / 2)
    private int parent(int idx) {
        if (idx == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (idx - 1) / 2;
    }

    // 返回idx位置元素的孩子节点
    // 注：这里从0放起
    private int leftChild(int idx) {
        // 若从1放起，leftChild = idx * 2
        return idx * 2 + 1;
    }

    private int rightChild(int idx) {
        // 若从1放起，leftChild = idx * 2
        return idx * 2 + 2;
    }

    // 添加元素
    public void add(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            Collections.swap(data, k, parent(k));
            k = parent(k);
        }
    }

    // 获取堆中最大元素
    public E findMax() {
        if (data.size() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        }
        // 堆顶最大，
        return data.get(0);
    }

    public E extractMax() {
        E ret = findMax();

        Collections.swap(data, 0, data.size() - 1);
        data.remove(data.size() - 1);

        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        while (leftChild(k) < data.size()) {
            int j = leftChild(k);
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            Collections.swap(data, k, j);
            k = j;
        }
    }

    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}