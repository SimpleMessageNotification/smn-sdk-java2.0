package com.smn.signer;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FIFOCache<T> {

    private final LRUCache<String, T> cache;
    private final ReentrantReadWriteLock.ReadLock rlock;
    private final ReentrantReadWriteLock.WriteLock wlock;

    public FIFOCache(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("maxSize " + maxSize + " must be at least 1");
        } else {
            this.cache = new LRUCache<String, T>(maxSize);
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            this.rlock = lock.readLock();
            this.wlock = lock.writeLock();
        }
    }

    public T add(String key, T value) {
        this.wlock.lock();

        try {
            return this.cache.put(key, value);
        } finally {
            this.wlock.unlock();
        }

    }

    public T get(String key) {
        this.rlock.lock();

        try {
            return this.cache.get(key);
        } finally {
            this.rlock.unlock();
        }
    }

    public int size() {
        this.rlock.lock();

        try {
            return this.cache.size();
        } finally {
            this.rlock.unlock();
        }
    }

    public int getMaxSize() {
        return this.cache.getMaxSize();
    }

    public String toString() {
        this.rlock.lock();

        try {
            return this.cache.toString();
        } finally {
            this.rlock.unlock();
        }
    }
}
