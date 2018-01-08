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
package com.smn.request.topic;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.topic.ListTopicAttributesResponse;
import com.smn.util.StringUtil;

/**
 * the request of list topic attributes
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListTopicAttributesRequest extends AbstractRequest<ListTopicAttributesResponse> {
    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * attribute name
     */
    private String name;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
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
                .append(Constants.URL_DELIMITER).append(Constants.ATTRIBUTES);
        // set query parameters
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListTopicAttributesRequest setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
        return this;
    }

    public ListTopicAttributesRequest setName(String name) {
        this.name = name;
        this.queryMap.put("name", name);
        return this;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public String getName() {
        return name;
    }
}
