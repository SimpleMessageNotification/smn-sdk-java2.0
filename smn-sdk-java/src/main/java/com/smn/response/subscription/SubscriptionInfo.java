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
package com.smn.response.subscription;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * subscription info
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class SubscriptionInfo {

    @JsonProperty("topic_urn")
    private String topicUrn;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("subscription_urn")
    private String subscriptionUrn;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("remark")
    private String remark;

    @JsonProperty("status")
    private int status;

    public String getTopicUrn() {
        return topicUrn;
    }

    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSubscriptionUrn() {
        return subscriptionUrn;
    }

    public void setSubscriptionUrn(String subscriptionUrn) {
        this.subscriptionUrn = subscriptionUrn;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}