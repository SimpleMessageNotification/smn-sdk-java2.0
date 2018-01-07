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
import com.smn.response.subscription.ListSubscriptionsResponse;
import com.smn.util.ValidationUtil;

/**
 * the request of list subscriptions
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListSubscriptionsRequest extends AbstractRequest<ListSubscriptionsResponse> {

    /**
     * offset
     */
    private int offset = 0;

    /**
     * limit
     */
    private int limit = 100;

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getUrl() {
        if (!ValidationUtil.validateOffset(offset)) {
            throw new NullPointerException("offset is null");
        }

        if (!ValidationUtil.validateLimit(limit)) {
            throw new NullPointerException("limit is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SUBSCRIPTIONS);

        // set query parameters
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListSubscriptionsRequest setOffset(int offset) {
        this.offset = offset;
        this.queryMap.put("offset", String.valueOf(offset));
        return this;
    }

    public ListSubscriptionsRequest setLimit(int limit) {
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
