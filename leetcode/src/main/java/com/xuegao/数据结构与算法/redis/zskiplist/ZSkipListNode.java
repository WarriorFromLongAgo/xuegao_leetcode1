package com.xuegao.数据结构与算法.redis.zskiplist;

import java.util.Arrays;

/**
 * <br/> @ClassName：ZSkipNode
 * <br/> @Description：
 * <br/> @date：2021/7/12 14:23
 */
public class ZSkipListNode {
    /**
     * 后退指针
     * 用于从表尾向表头方向访问。
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
     * 层，也就是level[]字段，层的数量越多，访问节点速度越快。
     * (因为它相当于是索引，层数越多，它索引就越细，就能很快找到索引值)
     */
    private ZSkipListLevel[] zSkipListLevelArr;

    /**
     * 层
     */
    private class ZSkipListLevel {
        /**
         * 前进指针
         * 用于从表头向表尾方向访问
         */
        private ZSkipListNode forwardSkipNode;
        /**
         * 跨度
         * 用于记录两个节点之间的距离
         */
        private Integer span;

        public ZSkipListNode getForwardSkipNode() {
            return forwardSkipNode;
        }

        public void setForwardSkipNode(ZSkipListNode forwardSkipNode) {
            this.forwardSkipNode = forwardSkipNode;
        }

        public Integer getSpan() {
            return span;
        }

        public void setSpan(Integer span) {
            this.span = span;
        }

        @Override
        public String toString() {
            return "ZSkipListLevel{" +
                    "forwardSkipNode=" + forwardSkipNode +
                    ", span=" + span +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ZSkipListNode{" +
                "backSkipNode=" + backSkipNode +
                ", score=" + score +
                ", object=" + object +
                ", zSkipListLevelArr=" + Arrays.toString(zSkipListLevelArr) +
                '}';
    }

    public ZSkipListNode getBackSkipNode() {
        return backSkipNode;
    }

    public void setBackSkipNode(ZSkipListNode backSkipNode) {
        this.backSkipNode = backSkipNode;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public ZSkipListLevel[] getzSkipListLevelArr() {
        return zSkipListLevelArr;
    }

    public void setzSkipListLevelArr(ZSkipListLevel[] zSkipListLevelArr) {
        this.zSkipListLevelArr = zSkipListLevelArr;
    }
}