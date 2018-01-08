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
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.sms.GetSmsMessageResponse;
import com.smn.util.StringUtil;

/**
 * the request of get sms message
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class GetSmsMessageRequest extends AbstractRequest<GetSmsMessageResponse> {
    /**
     * the message id is send
     */
    private String messageId;

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getUrl() {
        if (StringUtil.isBlank(messageId)) {
            throw new NullPointerException("message id is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_SUB_PROTOCOL_SMS)
                .append(Constants.URL_DELIMITER).append(Constants.MESSAGE)
                .append(Constants.URL_DELIMITER).append(messageId);

        return sb.toString();
    }

    public GetSmsMessageRequest setMessageId(String messageId) {
        this.messageId = messageId;
        return this;
    }

    public String getMessageId() {
        return messageId;
    }
}
