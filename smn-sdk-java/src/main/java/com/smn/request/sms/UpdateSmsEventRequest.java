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
package com.smn.request.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;

import java.util.List;

/**
 * the request of update sms event
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class UpdateSmsEventRequest extends AbstractRequest {
    /**
     * query result list
     */
    private List<SmsCallback> callbacks;

    public HttpMethod getHttpMethod() {
        return HttpMethod.PUT;
    }

    public String getUrl() {
        if (callbacks == null || callbacks.size() == 0) {
            throw new NullPointerException("callback is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_SUB_PROTOCOL_SMS)
                .append(Constants.URL_DELIMITER).append(Constants.CALLBACK_REQUEST);

        return sb.toString();
    }

    public UpdateSmsEventRequest setCallbacks(List<SmsCallback> callbacks) {
        this.callbacks = callbacks;
        this.bodyMap.put("callback", callbacks);
        return this;
    }

    public List<SmsCallback> getCallbacks() {
        return callbacks;
    }
}

/**
 * sms callback entity
 *
 * @author zhangyx
 * @version 0.8
 */
class SmsCallback {
    /**
     * callback event type
     */
    @JsonProperty("event_type")
    private String eventType;

    /**
     * topic urn
     */
    @JsonProperty("topic_urn")
    private String topicUrn;

    /**
     * no args construct
     */
    public SmsCallback() {

    }

    /**
     * construct
     *
     * @param eventType the eventType to set
     * @param topicUrn  the topicUrn to st
     */
    public SmsCallback(String eventType, String topicUrn) {
        this.eventType = eventType;
        this.topicUrn = topicUrn;
    }

    /**
     * @return the event_type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @param eventType the eventType to set
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * @return eht topic_urn
     */
    public String getTopicUrn() {
        return topicUrn;
    }

    /**
     * @param topicUrn the topic_urn to set
     */
    public void setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
    }
}
