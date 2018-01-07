/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of Apache License, Version 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Apache License, Version 2.0 for more details.
 */
package com.smn.response.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

import java.util.Dictionary;

/**
 * the response data of list topic attributes
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListTopicAttributesResponse extends AbstractResponse {

    @JsonProperty("attributes")
    private Dictionary<String, Object> attributes;

    public Dictionary<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Dictionary<String, Object> attributes) {
        this.attributes = attributes;
    }
}
