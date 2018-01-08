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
import com.smn.response.topic.UpdateTopicResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

/**
 * the request of update topic
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class UpdateTopicRequest extends AbstractRequest<UpdateTopicResponse> {
    /**
     * topic's unique resource identifier
     */
    private String topicUrn;

    /**
     * topic's descriptions
     */
    private String displayName;


    public HttpMethod getHttpMethod() {
        return HttpMethod.PUT;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(topicUrn)) {
            throw new NullPointerException("topic urn is null");
        }

        if (StringUtil.isEmpty(displayName) || !ValidationUtil.validateDisplayName(displayName)) {
            throw new IllegalArgumentException("display name is null or invalid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.TOPICS)
                .append(Constants.URL_DELIMITER).append(topicUrn);
        return sb.toString();
    }

    public UpdateTopicRequest setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
        return this;
    }

    public UpdateTopicRequest setDisplayName(String displayName) {
        this.displayName = displayName;
        this.bodyMap.put("display_name", displayName);
        return this;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public String getDisplayName() {
        return displayName;
    }
}
