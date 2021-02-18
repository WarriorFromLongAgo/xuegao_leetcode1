package com.xuegao.数据结构与算法.redis;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis
 * <br/> @ClassName：Explain
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/22 16:24
 */
public class Explain {
// 127.0.0.1:6379> set 1 1
// OK
// 127.0.0.1:6379> object encoding 1
// "int"
// 127.0.0.1:6379> set 2 --5656+
// OK
// 127.0.0.1:6379> object encoding 2
// "embstr"

// 127.0.0.1:6379> lpush list 1 2 3
// (integer) 3
// 127.0.0.1:6379> object encoding list
// "quicklist"

    // 1，简单动态字符串
    // 链表
    // 字典，hash表
    // 跳跃表，skipList
    // 整数集合 intset
    // 压缩列表 ziplist

    /**
     * <br/> @Title: sort set 由两种数据结构构成
     * <br/> @Description:
     * <br/> @MethodName: sortSet
     * <br/>
     * <br/> @return: void
     * <br/> @author: xuegao
     * <br/> @date: 2021/01/22 17:26
     */
    public void sortSet() {
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
        // 127.0.0.1:6379> zcard zset2
        // (integer) 129

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
        StringBuilder cli = new StringBuilder("sadd set ");
        int num = 0;
        for (int i = 0; i < 512; i++) {
            cli.append(i).append(" ");
            num++;
        }
        System.out.println(num);
        System.out.println(cli);
    }

    private static void setRawEmbstr() {
        StringBuilder cli = new StringBuilder("set set ");
        int num = 0;
        for (int i = 0; i < 44; i++) {
            cli.append(1);
            num++;
        }
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