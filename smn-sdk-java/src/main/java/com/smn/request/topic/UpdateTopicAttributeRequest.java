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
import com.smn.response.topic.UpdateTopicAttributeResponse;
import com.smn.util.StringUtil;

/**
 * the request of update topic attribute
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class UpdateTopicAttributeRequest extends AbstractRequest<UpdateTopicAttributeResponse> {
    /**
     * name
     */
    private String name;

    /**
     * topic_urn
     */
    private String topicUrn;

    /**
     * valud
     */
    private String value;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.PUT;
    }

    @Override
    public String getUrl() {
        if (StringUtil.isEmpty(topicUrn)) {
            throw new NullPointerException("topic urn is null");
        }

        if (StringUtil.isEmpty(name)) {
            throw new NullPointerException("name is null");
        }

        if (StringUtil.isEmpty(value)) {
            throw new NullPointerException("value is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.TOPICS)
                .append(Constants.URL_DELIMITER).append(topicUrn)
                .append(Constants.URL_DELIMITER).append(Constants.ATTRIBUTES)
                .append(Constants.URL_DELIMITER).append(name);
        return sb.toString();
    }

    public UpdateTopicAttributeRequest setName(String name) {
        this.name = name;
        return this;
    }

    public UpdateTopicAttributeRequest setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
        return this;
    }

    public UpdateTopicAttributeRequest setValue(String value) {
        this.value = value;
        this.bodyMap.put("value", value);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public String getValue() {
        return value;
    }
}
