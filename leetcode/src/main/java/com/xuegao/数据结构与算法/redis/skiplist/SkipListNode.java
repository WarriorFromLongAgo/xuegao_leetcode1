package com.xuegao.数据结构与算法.redis.skiplist;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.skiplist
 * <br/> @ClassName：SkipListNode
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/7/21 23:17
 */
public class SkipListNode {
    private Integer value;
    private Integer level;
    private SkipListNode[] forwardArr;
}