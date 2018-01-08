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
import com.smn.response.topic.ListTopicsResponse;
import com.smn.util.ValidationUtil;

public class ListTopicsRequest extends AbstractRequest<ListTopicsResponse> {
    /**
     * paging list's starting page,default 0
     */
    private int offset = 0;

    /**
     * max returned items for a request,default 100
     */
    private int limit = 100;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getUrl() {
        if (!ValidationUtil.validateOffset(offset)) {
            throw new IllegalArgumentException("offset is invalid.");
        }

        if (!ValidationUtil.validateLimit(limit)) {
            throw new IllegalArgumentException("limit is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.TOPICS);

        // set query parameters
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListTopicsRequest setOffset(int offset) {
        this.offset = offset;
        this.queryMap.put("offset", String.valueOf(offset));
        return this;
    }

    public ListTopicsRequest setLimit(int limit) {
        this.limit = limit;
        this.queryMap.put("limit", String.valueOf(limit));
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
