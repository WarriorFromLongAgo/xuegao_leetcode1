package com.xuegao.数据结构与算法.redis.skiplist3;

public class SkipListDemo {
    public static void main(String[] args) {
        SkipList<String> list = new SkipList<String>();
        list.put(10, "sho");
        list.put(1, "sha");
        list.put(9, "na");
        list.put(2, "bing");
        list.put(8, "ling");
        list.put(7, "xiao");
        list.put(100, "你好skiplist");
        list.put(5, "冰");
        list.put(6, "灵");
        System.out.println("列表元素：\n" + list);
        // System.out.println("删除100：" + list.remove(100));
        System.out.println("列表元素：\n" + list);
        System.out.println("5对于的value：\n" + list.get(5).value);
        System.out.println("链表大小：" + list.size() + ",深度：" + list.getLevel());
        System.out.println("=================================");
        System.out.println("=================================");
        System.out.println("print");
        System.out.println("=================================");
        list.print();
        list.print2();
    }
}