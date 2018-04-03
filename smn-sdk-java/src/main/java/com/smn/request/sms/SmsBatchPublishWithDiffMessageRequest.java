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
import com.smn.response.sms.SmsBatchPublishWithDiffMessageResponse;
import com.smn.util.ValidationUtil;

import java.util.List;

/**
 * the request of batch publish Notifications and verification code SMS with different message
 *
 * @author zhangyx
 * @version 2.0.3
 */
public class SmsBatchPublishWithDiffMessageRequest extends AbstractRequest<SmsBatchPublishWithDiffMessageResponse> {
    /**
     * sms_message
     */
    private List<SmsPublishMessage> smsMessages;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUrl() {
        if (smsMessages == null || smsMessages.size() == 0) {
            throw new NullPointerException("smsMessages is empty.");
        }

        if (smsMessages.size() > ValidationUtil.MAX_SMS_BATCH_PUBLISH_SIZE) {
            throw new IllegalArgumentException("smsMessages size must be less than 1000");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.BATCH_SMS);
        return sb.toString();
    }

    public SmsBatchPublishWithDiffMessageRequest setSmsMessages(List<SmsPublishMessage> smsMessages) {
        this.smsMessages = smsMessages;
        this.bodyMap.put("sms_message", smsMessages);
        return this;
    }

    public List<SmsPublishMessage> getSmsMessages() {
        return smsMessages;
    }
}
