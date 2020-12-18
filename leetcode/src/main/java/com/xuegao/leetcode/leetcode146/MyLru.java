package com.xuegao.leetcode.leetcode146;

import java.util.HashMap;
import java.util.Map;

/**
 * <br/> @PackageName：com.xuegao.leetcode.leetcode146
 * <br/> @ClassName：MyLru
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/12/18 17:30
 */
public class MyLru {
    private Map<Integer, Node> map;
    private LinkedList cache;
    // 最大容量
    private final int capacity;

    public MyLru(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new LinkedList();
    }

    /**
     * <br/> @Title:
     * <br/> @MethodName:  get
     * <br/> @param key:
     * <br/> @Return int
     * <br/> @Description:
     * <br/> @author: 80004960
     * <br/> @date:  2020/9/1 16:10
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            return -1;
        }
        int value = map.get(key).getValue();
        // 将 get 的数据，挪到 Lru 的最前面
        put(key, value);
        return value;
    }

    /**
     * <br/> @Title: 处理节点数据
     * <br/> @MethodName:  put
     * <br/> @param key:
     * <br/> @param value:
     * <br/> @Return void
     * <br/> @Description:
     * <br/> @author: 80004960
     * <br/> @date:  2020/9/1 16:04
     */
    public void put(int key, int value) {
        Node newNode = new Node(key, value);

        // 如果 map 缓存中存在要插入的数据
        if (map.containsKey(key)) {
            // Lru 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            // 将 节点数据 加入到 Lru 的头部
            cache.addFirst(newNode);
        } else {
            // 如果 map 缓存中 不存在要插入的数据，从新插入
            // 已经满了，删除 Lru 的最后一个数据
            if (capacity == cache.size()) {
                Node lastNode = cache.removeLast();
                map.remove(lastNode.getKey());
            }
            // 将 节点数据 加入到 Lru 的头部
            cache.addFirst(newNode);
        }
        // 更新map中的数据
        map.put(key, newNode);
    }
}

class LinkedList {
    private Node headNode;
    private Node lastNode;
    private int size;

    // 在链表头部添加节点 x，时间 O(1)
    public void addFirst(Node newNode) {
        if (headNode == null) {
            headNode = lastNode = newNode;
        } else {
            Node headNode = this.headNode;
            headNode.setLeftNode(newNode);
            newNode.setRightNode(headNode);
            this.headNode = newNode;
        }
        size++;
    }

    // 删除链表中的 x 节点（x 一定存在）
    // 由于是双链表且给的是目标 Node 节点，时间 O(1)
    public void remove(Node newNode) {
        if (headNode == newNode && lastNode == newNode) {
            headNode = null;
            lastNode = null;
        } else if (lastNode == newNode) {
            newNode.getLeftNode().setRightNode(null);
            lastNode = newNode.getLeftNode();
        } else if (headNode == newNode) {
            newNode.getRightNode().setLeftNode(null);
            headNode = newNode.getRightNode();
        } else {
            newNode.getLeftNode().setRightNode(newNode.getRightNode());
            newNode.getRightNode().setLeftNode(newNode.getLeftNode());
        }
        size--;
    }

    // 删除链表中最后一个节点，并返回该节点，时间 O(1)
    public Node removeLast() {
        Node tempNode = lastNode;
        Node leftNode = tempNode.getLeftNode();
        tempNode = null;
        leftNode.setRightNode(null);
        lastNode = leftNode;
        size--;
        return tempNode;
    }

    // 返回链表长度，时间 O(1)
    public int size() {
        return size;
    }

}

class Node {
    private int key;
    private int value;
    private Node leftNode;
    private Node rightNode;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
