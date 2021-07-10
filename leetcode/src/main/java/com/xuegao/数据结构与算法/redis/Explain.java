package com.xuegao.数据结构与算法.redis;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis
 * <br/> @ClassName：Explain
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/22 16:24
 */
public class Explain {
    // 1，简单动态字符串
    // 链表
    // 字典，hash表
    // 跳跃表，skipList
    // 整数集合 intset
    // 压缩列表 ziplist


// 127.0.0.1:6379> sadd number 1 2 3 4 5
// (integer) 3
// 127.0.0.1:6379> object encoding number
// "intset"

    public void string() {
        // 字符串对象的编码可以是int、raw或者embstr

        // 127.0.0.1:6379> set 1 1
        // OK
        // 127.0.0.1:6379> object encoding 1
        // "int"
        // 127.0.0.1:6379> set set 11111111111111111111111111111111111111111111
        // OK
        // 127.0.0.1:6379> strlen set
        // (integer) 44
        // 127.0.0.1:6379> set set 111111111111111111111111111111111111111111111
        // OK
        // 127.0.0.1:6379> strlen set
        // (integer) 45
        // 127.0.0.1:6379> object encoding set
        // "raw"
    }

    public void list() {
        // 列表对象的编码可以是quicklist, ziplist或者linkedlist。

        // 127.0.0.1:6379> rpush list 1
        // (integer) 1
        // 127.0.0.1:6379> object encoding list
        // "quicklist"
        // 127.0.0.1:6379> rpush list "a"
        // (integer) 2
        // 127.0.0.1:6379> object encoding list
        // "quicklist"
    }

    public void hash() {
        // 哈希对象的编码可以是ziplist或者hashtable。

        // 127.0.0.1:6379> hset hash a 1
        // (integer) 1
        // 127.0.0.1:6379> object encoding hash
        // "ziplist"

    }

    public void set() {
        // 集合对象的编码可以是intset或者hashtable。


    }

    public void sortSet() {
        // 有序集合的编码可以是ziplist或者skiplist
        // 为什么有序集合需要同时使用跳跃表和字典来实现？

        // sort set
        // sort set
        // sort set
        // 127.0.0.1:6379> zcard zset2
        // (integer) 127
        // 127.0.0.1:6379> object encoding zset2
        // "ziplist"
        // 127.0.0.1:6379> zadd zset2 127 127
        // (integer) 1
        // 127.0.0.1:6379> zcard zset2
        // (integer) 128
        // 127.0.0.1:6379> object encoding zset2
        // "ziplist"
        // 127.0.0.1:6379> zadd zset2 128 128
        // (integer) 1
        // 127.0.0.1:6379> object encoding zset2
        // "skiplist"

        // 64个1，64字节
        // 127.0.0.1:6379> zadd zset 1 1111111111111111111111111111111111111111111111111111111111111111
        // (integer) 1
        // 127.0.0.1:6379> object encoding zset
        // "ziplist"
        // 65个1，65字节
        // 127.0.0.1:6379> zadd zset 1 11111111111111111111111111111111111111111111111111111111111111111
        // (integer) 1
        // 127.0.0.1:6379> object encoding zset
        // "skiplist"
    }


    public static void main(String[] args) {
        pushNode64();
        pushRLen512();
    }

    private static void pushNode64() {
        StringBuilder cli = new StringBuilder("rpush list \"");
        int num = 0;
        for (int i = 0; i < 64; i++) {
            cli.append("a");
            num++;
        }
        System.out.println(num);
        System.out.println(cli);
        cli.append("a");
        System.out.println(cli);
    }

    private static void pushRLen512() {
        StringBuilder cli = new StringBuilder("rpush list ");
        int num = 0;
        for (int i = 0; i < 512; i++) {
            cli.append("\"").append(i).append("\"").append(" ");
            num++;
        }
        System.out.println(num);
        System.out.println(cli);
        cli.append("1").append(" ");
        System.out.println(cli);
    }

    private static void sadd64Byte() {
        StringBuilder cli = new StringBuilder("sadd list ");
        int num = 0;
        for (int i = 0; i < 65; i++) {
            cli.append(1);
            num++;
        }
    }

    private static void setRawEmbstr() {
        StringBuilder cli = new StringBuilder("set set ");
        int num = 0;
        for (int i = 0; i < 45; i++) {
            cli.append(1);
            num++;
        }
        System.out.println(cli);
        System.out.println(num);
    }

    private static void sortSet64Byte() {
        StringBuilder cli = new StringBuilder("zadd zset 1 ");
        int num = 0;
        for (int i = 0; i < 65; i++) {
            cli.append(1);
            num++;
        }
    }

    private static void sortSet128() {
        StringBuilder cli = new StringBuilder("zadd zset2 ");
        for (int i = 0; i < 127; i++) {
            cli.append(i)
                    .append(" ")
                    .append(i)
                    .append(" ");
        }
    }

}