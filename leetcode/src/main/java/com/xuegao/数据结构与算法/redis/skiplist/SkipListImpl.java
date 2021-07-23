package com.xuegao.数据结构与算法.redis.skiplist;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.skiplist
 * <br/> @ClassName：SkipList
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/7/21 23:46
 */
public class SkipListImpl {
    private final static double probability = 0.5;
    private final static int maxLevel = 16;

    public static void main(String[] args) {
        ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
    }

}