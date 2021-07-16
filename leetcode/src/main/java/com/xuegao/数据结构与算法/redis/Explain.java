package com.xuegao.数据结构与算法.redis;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis
 * <br/> @ClassName：Explain
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/01/22 16:24
 */
public class Explain {
    // https://mp.weixin.qq.com/s?__biz=MzA4NTg1MjM0Mg==&mid=509777776&idx=1&sn=e56f24bdf2de7e25515fe9f25ef57557&mpshare=1&scene=1

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
        // 列表对象的编码可以是 quicklist, ziplist或者linkedlist。
        // 在低版本的Redis中，list采用的底层数据结构是ziplist+linkedList，高版本的Redis中，quicklist替换了ziplist+linkedList，
        // 而quicklist也用到了ziplist，所以可以说list间接使用了ziplist数据结构。
        // 因为ziplist是紧凑存储，没有冗余空间，意味着新插入元素，就需要扩展内存，这就分为两种情况：
        // 分配新的内存，将原数据拷贝到新内存；
        // 扩展原有内存。
        // 所以ziplist 不适合存储大型字符串，存储的元素也不宜过多。
        //
        // 作者：CoderBear
        // 链接：https://www.jianshu.com/p/2d276c3f7cff
        // 来源：简书
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

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
        // 127.0.0.1:6379> sadd set redis
        // (integer) 1
        // 127.0.0.1:6379> object encoding set
        // "hashtable"
        // 127.0.0.1:6379> sadd set 1
        // (integer) 1
        // 127.0.0.1:6379> object encoding set
        // "intset"

    }

    public void sortSet() {
        // 有序集合的编码可以是ziplist或者skiplist
        // 为什么有序集合需要同时使用跳跃表和字典来实现？

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