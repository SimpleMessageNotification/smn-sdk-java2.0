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
import com.smn.response.sms.SmsPublishResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

/**
 * publish sms direct
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class SmsPublishRequest extends AbstractRequest<SmsPublishResponse> {

    /**
     * message access point
     */
    private String endpoint;

    /**
     * message to send
     */
    private String message;

    /**
     * message signature id
     */
    private String signId;

    /**
     * message_include_sign_flag
     */
    private boolean messageIncludeSignFlag = false;

    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(endpoint)) {
            throw new NullPointerException("endpoint is null.");
        }

        if (StringUtil.isEmpty(message)) {
            throw new NullPointerException("message is null.");
        }

        if (!messageIncludeSignFlag && StringUtil.isEmpty(signId)) {
            throw new NullPointerException("sign id is null.");
        }

        if (StringUtil.isEmpty(signId) && messageIncludeSignFlag && !ValidationUtil.containSignNameFromMessage(message)) {
            throw new NullPointerException("message not include sign.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_SUB_PROTOCOL_SMS);

        return sb.toString();
    }


    public String getEndpoint() {
        return endpoint;
    }

    public String getMessage() {
        return message;
    }

    public String getSignId() {
        return signId;
    }

    public boolean isMessageIncludeSignFlag() {
        return messageIncludeSignFlag;
    }

    public SmsPublishRequest setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        this.bodyMap.put("endpoint", endpoint);
        return this;
    }

    public SmsPublishRequest setMessage(String message) {
        this.message = message;
        this.bodyMap.put("message", message);
        return this;
    }

    public SmsPublishRequest setSignId(String signId) {
        this.signId = signId;
        this.bodyMap.put("sign_id", signId);
        return this;
    }

    public SmsPublishRequest setMessageIncludeSignFlag(boolean messageIncludeSignFlag) {
        this.messageIncludeSignFlag = messageIncludeSignFlag;
        this.bodyMap.put("message_include_sign_flag", messageIncludeSignFlag);
        return this;
    }
}
