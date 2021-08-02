package com.xuegao.数据结构与算法.redis.skiplist;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.skiplist
 * <br/> @ClassName：Demo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/8/1 19:03
 */
public class Demo {
    public static void main(String[] args) {
        SkipSet<Integer> skipSet = new SkipSet<>();
        skipSet.insert(100);
        skipSet.insert(30);
        skipSet.insert(80);
        skipSet.insert(20);
        skipSet.insert(90);
        skipSet.insert(40);
        skipSet.insert(60);
        skipSet.insert(10);
        System.out.println(skipSet.toString());
    }


}