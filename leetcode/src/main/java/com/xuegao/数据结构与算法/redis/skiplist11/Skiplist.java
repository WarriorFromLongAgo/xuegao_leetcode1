package com.xuegao.数据结构与算法.redis.skiplist11;

public class Skiplist {
    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;

    // 头节点
    Node head;

    // 节点对象
    class Node {
        int val;
        Node bw;  // 后退指针
        Node[] fw; // 前进指针

        // 构造函数
        public Node(int val) {
            this.val = val;
            fw = new Node[randomLevel()];
        }

        public Node(int val, int size) {
            this.val = val;
            fw = new Node[size + 1];
        }

        // 生成随机 level
        public int randomLevel() {
            int level = 1;
            while (Math.random() < SKIPLIST_P && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }
    }

    // 生成默认的头节点
    public Skiplist() {
        head = new Node(-1, MAX_LEVEL);
    }

    // 查询
    public boolean search(int target) {
        Node p = searchNode(target);
        boolean b = p.val == target;
        //System.out.println(b);
        return b;
    }

    // 添加
    public void add(int num) {
        Node p = searchNode(num);
        Node n = new Node(num);
        n.bw = p;
        for (int i = 0; i < n.fw.length; i++) {
            Node f = p;
            while (f.bw != null && f.fw.length < i + 1) {
                f = f.bw;
            }
            if (i == 0 && f.fw[i] != null) {
                f.fw[i].bw = n;
            }
            n.fw[i] = f.fw[i];
            f.fw[i] = n;
        }
    }

    // 移除
    public boolean erase(int num) {
        if (isEmpty()) {
            //System.out.println(false);
            return false;
        }
        Node p = searchNode(num);
        if (p.val != num) {
            //System.out.println(false);
            return false;
        }
        for (int i = 0; i < p.fw.length; i++) {
            Node f = p.bw;
            while (f.bw != null && f.fw.length < i + 1) {
                f = f.bw;
            }
            if (i == 0 && f.fw[i].fw[i] != null) {
                f.fw[i].fw[i].bw = f;
            }
            f.fw[i] = f.fw[i].fw[i];
        }
        //System.out.println(true);
        return true;
    }

    // 查询节点
    private Node searchNode(int target) {
        if (isEmpty()) {
            return head;
        }
        Node p = head;
        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (p.fw[i] != null && p.fw[i].val <= target) {
                p = p.fw[i];
            }
        }
        return p;
    }

    // 是否为空
    private boolean isEmpty() {
        return head.fw[0] == null;
    }
}

// 作者：心城以北
// 链接：https://juejin.cn/post/7065743209527246884
// 来源：稀土掘金
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。