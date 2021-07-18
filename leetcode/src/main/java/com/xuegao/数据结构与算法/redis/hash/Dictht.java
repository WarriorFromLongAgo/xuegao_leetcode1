package com.xuegao.数据结构与算法.redis.hash;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.hash
 * <br/> @ClassName：Dictht
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/7/18 13:09
 */
public class Dictht {
    /**
     * hash表的指针数组, dictEntry * 表示hash节点的指针,
     * 再加一个 *, 也就是dictEntry ** 表示数组的首地址
     * dictEntry **table;
     */
    DictEntry table;
    /**
     * hash数组大小, 一般为 2^n
     * unsigned long size;
     */
    Long size;
    /**
     * hash数组长度掩码, sizemask =  size - 1
     * unsigned long sizemask;
     */
    Long sizemask;
    /**
     * hash表的kv对的个数
     * unsigned long used;
     */
    Long used;
}