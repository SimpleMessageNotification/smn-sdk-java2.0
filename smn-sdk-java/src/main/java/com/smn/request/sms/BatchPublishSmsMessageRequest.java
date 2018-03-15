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

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.sms.BatchPublishSmsMessageResponse;
import com.smn.util.StringUtil;

import java.util.List;

/**
 * the request of publish Notifications and verification code SMS
 *
 * @author zhangyx
 * @version 2.0.2
 */
public class BatchPublishSmsMessageRequest extends AbstractRequest<BatchPublishSmsMessageResponse> {
    /**
     * sms template id
     */
    private String message;
    /**
     * sign id
     */
    private String signId;
    /**
     * endpoints
     */
    private List<String> endpoints;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUrl() {
        if (StringUtil.isEmpty(message)) {
            throw new NullPointerException("message is null.");
        }

        if (endpoints == null || endpoints.size() == 0) {
            throw new NullPointerException("endpoints is null or empty.");
        }

        if (StringUtil.isEmpty(signId)) {
            throw new NullPointerException("sign id is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_SUB_PROTOCOL_SMS);
        return sb.toString();
    }

    public BatchPublishSmsMessageRequest setMessage(String message) {
        this.message = message;
        this.bodyMap.put("message", message);
        return this;
    }

    public BatchPublishSmsMessageRequest setSignId(String signId) {
        this.signId = signId;
        this.bodyMap.put("sign_id", signId);
        return this;
    }

    public BatchPublishSmsMessageRequest setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
        this.bodyMap.put("endpoints", endpoints);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public String getSignId() {
        return signId;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }
}
