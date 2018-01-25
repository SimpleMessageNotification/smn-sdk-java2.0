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
package com.smn.request.auth;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;

/**
 * the request of get project Id
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class GetProjectIdRequest extends AbstractRequest {
    /**
     * region name
     */
    private String name;

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(getIamServiceUrl());
        sb.append(Constants.IAM_PROJECT_URL);

        // set query parameters
        sb.append(getQueryString());
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public GetProjectIdRequest setName(String name) {
        this.name = name;
        this.queryMap.put("name", name);
        return this;
    }
}
