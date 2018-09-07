package com.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少使用缓存
 *
 * @author kun.wang
 * @date 2018/9/7
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_MAX_SIZE;

    public LRUCache(int cacheMaxSize) {
        /**
         * LinkedHashMap的一个构造函数，当
         * 参数accessOrder为true时，即会按照访问顺序排序，
         * 最近访问的放在链表尾部，最早访问的放在链表前面
         */
        super((int) Math.ceil(cacheMaxSize / 0.75) + 1, 0.75f, true);
        this.CACHE_MAX_SIZE = cacheMaxSize;
    }

    /**
     * 超过CACHE_MAX_SIZE会移除指定元素，在调用put或putAll方法时会触发此方法
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > CACHE_MAX_SIZE;
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(5);
        for (int i = 0; i < 10; i++) {
            lruCache.put("key" + i, "value" + i);
        }
        System.out.println(lruCache);
        System.out.println(lruCache.get("key7"));
        System.out.println(lruCache.get("key8"));
        System.out.println(lruCache);
    }
}
