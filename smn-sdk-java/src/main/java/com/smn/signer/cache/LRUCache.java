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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU cache
 *
 * @param <K> key
 * @param <V> value
 * @author zhangyx
 * @version 2.0.4
 */
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