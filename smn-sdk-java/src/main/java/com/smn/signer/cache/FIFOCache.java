/*
 * Copyright (C) 2018. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */

package com.smn.signer.cache;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * fifo cache
 *
 * @param <T> value type
 * @author zhangyx
 * @version 2.0.4
 */
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
