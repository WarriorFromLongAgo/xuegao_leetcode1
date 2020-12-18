package com.xuegao.leetcode.leetcode146;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <br/> @PackageName：com.xuegao.leetcode.leetcode146
 * <br/> @ClassName：LruMap
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:29
 */
public class LruMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;

    public LruMap(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void mapToString(LruMap lru) {
        System.out.println("==============================================================================");
        lru.forEach((integer, integer2) -> System.out.println("key = " + integer + ", value = " + integer2));
        System.out.println("==============================================================================");
    }

    public static void main(String[] args) {
        LruMap lru = new LruMap(3);
        lru.put(1, 10);
        lru.put(2, 20);
        lru.put(3, 30);
        mapToString(lru);

        lru.get(1);
        lru.get(1);
        lru.get(1);
        lru.get(2);
        mapToString(lru);
        lru.put(10, 1010);
        lru.put(20, 2020);
        mapToString(lru);

    }

}