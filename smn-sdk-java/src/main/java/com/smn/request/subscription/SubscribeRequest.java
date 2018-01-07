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
package com.smn.request.subscription;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.subscription.SubscribeResponse;
import com.smn.util.StringUtil;

/**
 * the request of subscribe
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class SubscribeRequest extends AbstractRequest<SubscribeResponse> {

    /**
     * topicUrn
     */
    private String topicUrn;

    /**
     * protocol
     */
    private String protocol;

    /**
     * endpoint
     */
    private String endpoint;

    /**
     * remark
     */
    private String remark;


    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(topicUrn)) {
            throw new NullPointerException("topic urn is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.TOPICS)
                .append(Constants.URL_DELIMITER).append(topicUrn)
                .append(Constants.URL_DELIMITER).append(Constants.SUBSCRIPTIONS);
        return sb.toString();

    }

    public SubscribeRequest setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
        return this;
    }

    public SubscribeRequest setProtocol(String protocol) {
        this.protocol = protocol;
        this.bodyMap.put("protocol", protocol);
        return this;
    }

    public SubscribeRequest setEndpoint(String endpoint) {
        this.endpoint = endpoint;
        this.bodyMap.put("endpoint", endpoint);
        return this;
    }

    public SubscribeRequest setRemark(String remark) {
        this.remark = remark;
        this.bodyMap.put("remark", remark);
        return this;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getRemark() {
        return remark;
    }
}
