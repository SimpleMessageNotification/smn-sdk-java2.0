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
import com.smn.response.topic.CreateTopicResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

/**
 * the request of create topic
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class CreateTopicRequest extends AbstractRequest<CreateTopicResponse> {
    /**
     * topic name
     */
    private String name;

    /**
     * topic's descriptions
     */
    private String displayName;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUrl() {
        if (!ValidationUtil.validateTopicName(name))
        {
            throw new IllegalArgumentException("name is invalid");
        }

        if (!StringUtil.isEmpty(displayName) && !ValidationUtil.validateDisplayName(displayName))
        {
            throw new IllegalArgumentException("display name is invalid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.TOPICS);

        return sb.toString();
    }

    public CreateTopicRequest setName(String name) {
        this.name = name;
        this.bodyMap.put("name", name);
        return this;
    }

    public CreateTopicRequest setDisplayName(String displayName) {
        this.displayName = displayName;
        this.bodyMap.put("display_name", displayName);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
