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
package com.smn.response.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

import java.util.List;

/**
 * the response data of list Sms event
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListSmsEventResponse extends AbstractResponse {

    @JsonProperty("callback")
    private List<SmsCallback> callback;

    @JsonProperty("topic_urn")
    private String topicUrn;

    public List<SmsCallback> getCallback() {
        return callback;
    }

    public void setCallback(List<SmsCallback> callback) {
        this.callback = callback;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }
}

/**
 * Sms callback
 *
 * @author zhangyx
 * @version 2.0.0
 */
class SmsCallback {

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("topic_urn")
    private String topicUrn;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }
}
