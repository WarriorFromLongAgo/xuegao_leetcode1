package com.xuegao.数据结构与算法.redis.zskiplist;

/**
 * <br/> @ClassName：ZSkipNode
 * <br/> @Description：
 * <br/> @date：2021/7/12 14:23
 */
public class ZSkipListNode {
    /**
     * 后退指针
     */
    private ZSkipListNode backSkipNode;
    /**
     * 分值
     */
    private Double score;
    /**
     * 成员对象
     */
    private Object object;

    /**
     * 层
     */
    private class ZSkipListLevel {
        /**
         * 前进指针
         */
        private ZSkipListNode forwardSkipNode;
        /**
         * 跨度
         */
        private Integer span;
    }


}