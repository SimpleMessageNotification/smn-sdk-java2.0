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

/**
 * the response data of create topic
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class CreateTopicResponse extends AbstractResponse {

    @JsonProperty("topic_urn")
    private String topicUrn;

    public String getTopicUrn() {
        return topicUrn;
    }

    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }
}
