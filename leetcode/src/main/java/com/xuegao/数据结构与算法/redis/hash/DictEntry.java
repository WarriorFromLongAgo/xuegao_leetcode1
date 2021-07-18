package com.xuegao.数据结构与算法.redis.hash;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.hash
 * <br/> @ClassName：DictEntry
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/7/18 13:10
 */
public class DictEntry {
    /**
     * 节点的key
     * void *key;
     */
    Object key;
    /**
     * 节点的值 v , v 可以是指针, 也可以是uint64整数, 也可以是int64整数, 还可以是浮点数
     * union {
     * void *val;
     * uint64_t u64;
     * int64_t s64;
     * double d;
     * } v;
     */
    Object value;

    /**
     * 下一个节点
     * struct dictEntry *next;
     */
    DictEntry next;
}