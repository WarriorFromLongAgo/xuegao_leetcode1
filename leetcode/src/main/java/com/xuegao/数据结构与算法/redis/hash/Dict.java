package com.xuegao.数据结构与算法.redis.hash;

/**
 * <br/> @PackageName：com.xuegao.数据结构与算法.redis.hash
 * <br/> @ClassName：Dict
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2021/7/18 13:14
 */
public class Dict {
    /**
     * 字典类型的指针
     * dictType *type;
     */
    DictType dictType;
    /**
     * 携带的私有数据
     * void *privdata;
     */
    Object privdata;
    /**
     * hash字典的数组, 长度为2, 主要是用于渐进式hash时使用
     * dictht ht[2];
     */
    Object[] ht;
    /**
     * rehash下一个要迁移的桶索引, rehash 不进行时为 -1
     * long rehashidx; rehashing not in progress if rehashidx == -1
     */
    Long rehashidx;
    /**
     * pauserehash > 0 表示 rehash 是暂停的. 安全的迭代需要停止
     * int16_t pauserehash;  If >0 rehashing is paused (<0 indicates coding error)
     */
    Integer pauserehash;

    // public Dict dictCreate(DictType dictType, Object privdata){
    //
    // }
    //
    // public Dict dictCreate(DictType dictType, Object privdata){
    //
    // }
}