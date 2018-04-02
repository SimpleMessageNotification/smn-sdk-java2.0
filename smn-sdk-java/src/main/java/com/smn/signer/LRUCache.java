package com.smn.signer;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int maxSize;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
    }

    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() >= maxSize;
    }

    int getMaxSize() {
        return this.maxSize;
    }
}