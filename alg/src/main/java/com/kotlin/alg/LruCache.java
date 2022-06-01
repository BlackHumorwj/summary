package com.kotlin.alg;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <pre>
 *     author : ZW002
 *     e-mail : jinbao@cash360.cn
 *     time   : 2022/04/18
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class LruCache extends LinkedHashMap<Integer, Integer> {

    private int capacity;

    public LruCache(int capacity) {
        //accessOrder true //访问的顺序
        //accessOrder false //插入的顺序
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }


    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
