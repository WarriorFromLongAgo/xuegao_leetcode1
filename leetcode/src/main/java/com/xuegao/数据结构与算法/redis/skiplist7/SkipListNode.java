package com.xuegao.数据结构与算法.redis.skiplist7;

import java.util.*;

public class SkipListNode<E> {
    private E value;
    public List<SkipListNode<E>> nextNodes;

    public E getValue() {
        return value;
    }

    public SkipListNode(E value) {
        this.value = value;
        nextNodes = new ArrayList<SkipListNode<E>>();
    }

    public int level() {
        return nextNodes.size() - 1;
    }

    public String toString() {
        return "SLN: " + value;
    }
}