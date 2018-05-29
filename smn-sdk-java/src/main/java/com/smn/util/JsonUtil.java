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
package com.smn.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * json utils
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class JsonUtil {

    /**
     * Object mapper root
     */
    private static final ObjectMapper OBJMAPPER = new ObjectMapper();

    static {
        // ingore unkowned feild
        OBJMAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Parsing a JSON string into a map object
     * <p>
     * If the string is empty, an empty string is returned
     *
     * @param message message content in JSON format
     * @return {@code Map}
     */
    @SuppressWarnings("unchecked")
    public static final Map<String, Object> parseJsonMessage(String message) {

        if (message == null) {
            return new HashMap<String, Object>();
        }

        try {
            return OBJMAPPER.readValue(message, Map.class);
        } catch (Exception e) {
            return new HashMap<String, Object>();
        }
    }

    /**
     * Convert Map to String content
     *
     * @param messageMap Need to convert JSON format string map
     * @return {@code String} message content
     */
    public static final String getJsonStringByMap(Map<String, Object> messageMap) {

        if (messageMap == null) {
            return "{}";
        }

        try {
            return OBJMAPPER.writeValueAsString(messageMap);
        } catch (Exception e) {
        }

        return "{}";
    }

    /**
     * parse json string to object
     *
     * @param json   the json string to parse
     * @param tClass the Object class type
     * @param <T>
     * @return the Object
     */
    public static final <T> T parseJsonToObject(String json, Class<T> tClass) {
        try {
            return OBJMAPPER.readValue(json, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
