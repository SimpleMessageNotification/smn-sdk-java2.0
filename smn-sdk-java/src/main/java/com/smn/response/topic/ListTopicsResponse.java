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
package com.smn.response.topic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

import java.util.List;

/**
 * the response data of list topics
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListTopicsResponse extends AbstractResponse {

    @JsonProperty("topic_count")
    private int topicCount;

    @JsonProperty("topics")
    private List<TopicInfo> topics;

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public List<TopicInfo> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicInfo> topics) {
        this.topics = topics;
    }
}