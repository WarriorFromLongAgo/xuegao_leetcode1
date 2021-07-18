package com.xuegao.数据结构与算法.redis.hash;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.hash
 * <br/> @ClassName：Dict
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/7/18 13:14
 */
public interface DictType {
    /**
     * 键的hash函数
     * uint64_t (*hashFunction)(const void *key);
     */
    Long hashFunction();

    /**
     * 复制键的函数
     * void *(*keyDup)(void *privdata, const void *key);
     */
    void keyDup();

    /**
     * 值的复制函数
     * void *(*valDup)(void *privdata, const void *obj);
     */
    void valDup();

    /**
     * 键的比较函数
     * int (*keyCompare)(void *privdata, const void *key1, const void *key2);
     */
    void keyCompare();

    /**
     * 键的销毁函数
     * void (*keyDestructor)(void *privdata, void *key);
     */
    void keyDestructor();

    /**
     * 值的销毁函数
     * void (*valDestructor)(void *privdata, void *obj);
     */
    void valDestructor();

    /**
     * 根据扩容后的数组的内存和负载因子判断是否可以扩容
     * int (*expandAllowed)(size_t moreMem, double usedRatio);
     */
    void expandAllowed();

}