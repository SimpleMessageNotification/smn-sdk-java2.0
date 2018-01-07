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
import com.smn.response.subscription.UnsubscribeResponse;
import com.smn.util.StringUtil;

/**
 * the request of unsubscribe
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class UnsubscribeRequest extends AbstractRequest<UnsubscribeResponse> {

    /**
     * subscriptionUrn
     */
    private String subscriptionUrn;

    public HttpMethod getHttpMethod() {
        return HttpMethod.DELETE;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(subscriptionUrn)) {
            throw new NullPointerException("subscription urn is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SUBSCRIPTIONS)
                .append(Constants.URL_DELIMITER).append(subscriptionUrn);

        return sb.toString();
    }

    public UnsubscribeRequest setSubscriptionUrn(String subscriptionUrn) {
        this.subscriptionUrn = subscriptionUrn;
        return this;
    }

    public String getSubscriptionUrn() {
        return subscriptionUrn;
    }
}
