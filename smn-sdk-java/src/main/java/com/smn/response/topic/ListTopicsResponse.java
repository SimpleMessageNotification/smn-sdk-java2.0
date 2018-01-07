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

/**
 * topic info
 *
 * @author zhangyx
 * @version 2.0.0
 */
class TopicInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("topic_urn")
    private String topicUrn;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("push_policy")
    private String pushPolicy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPushPolicy() {
        return pushPolicy;
    }

    public void setPushPolicy(String pushPolicy) {
        this.pushPolicy = pushPolicy;
    }
}