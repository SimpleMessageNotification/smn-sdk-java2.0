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
package com.smn.request.sms;

import com.smn.common.Constants;
import com.smn.common.SmsCallbackEventType;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.sms.ListSmsEventResponse;
import com.smn.util.StringUtil;

/**
 * the request of list sms event
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListSmsEventRequest extends AbstractRequest<ListSmsEventResponse> {
    /**
     * the sms callback event type
     * {@link SmsCallbackEventType#SMS_CALLBACK_SUCCESS}
     * {@link SmsCallbackEventType#SMS_CALLBACK_SUCCESS}
     * {@link SmsCallbackEventType#SMS_CALLBACK_REPLY}
     */
    private String eventType;

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getUrl() {
        if (!StringUtil.isBlank(eventType)) {
            throw new IllegalArgumentException("event_type is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_SUB_PROTOCOL_SMS)
                .append(Constants.URL_DELIMITER).append(Constants.CALLBACK_REQUEST);

        // set the request parameter
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListSmsEventRequest setEventType(String eventType) {
        this.eventType = eventType;
        this.queryMap.put("event_type", eventType);
        return this;
    }

    public String getEventType() {
        return eventType;
    }
}
