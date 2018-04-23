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

package com.smn.request.mms;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.mms.MmsPublishResponse;
import com.smn.util.StringUtil;

import java.util.List;

/**
 * publish mms direct
 *
 * @author zhangyx
 * @version 2.0.5
 */
public class MmsPublishRequest extends AbstractRequest<MmsPublishResponse> {
    /**
     * title
     */
    private String title;

    /**
     * endpoints
     */
    private List<String> endpoints;

    /**
     * sign_id
     */
    private String signId;

    /**
     * mms_message
     */
    private List<MmsFrame> mmsMessage;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUrl() {
        if (StringUtil.isEmpty(title)) {
            throw new NullPointerException("title is null.");
        }

        if (endpoints == null || endpoints.size() == 0) {
            throw new NullPointerException("endpoints is null or empty.");
        }

        if (mmsMessage == null || mmsMessage.size() == 0) {
            throw new NullPointerException("mms_message is null.");
        }

        if (StringUtil.isEmpty(signId)) {
            throw new NullPointerException("sign_id is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_MMS);
        return sb.toString();
    }

    public String getTitle() {
        return title;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }

    public String getSignId() {
        return signId;
    }

    public List<MmsFrame> getMmsMessage() {
        return mmsMessage;
    }

    public MmsPublishRequest setTitle(String title) {
        this.title = title;
        this.bodyMap.put("title", title);
        return this;
    }

    public MmsPublishRequest setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
        this.bodyMap.put("endpoints", endpoints);
        return this;
    }

    public MmsPublishRequest setSignId(String signId) {
        this.signId = signId;
        this.bodyMap.put("sign_id", signId);
        return this;
    }

    public MmsPublishRequest setMmsMessage(List<MmsFrame> mmsMessage) {
        this.mmsMessage = mmsMessage;
        this.bodyMap.put("mms_message", mmsMessage);
        return this;
    }
}
