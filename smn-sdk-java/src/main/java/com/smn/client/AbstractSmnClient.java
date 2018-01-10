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

package com.smn.client;

import com.smn.auth.IamAuth;
import com.smn.config.ClientConfiguration;
import com.smn.config.SmnConfiguration;
import com.smn.http.HttpResponse;
import com.smn.http.HttpTool;
import com.smn.request.AbstractRequest;
import com.smn.request.IHttpRequest;
import com.smn.response.AbstractResponse;

/**
 * abstract smn client
 *
 * @author zhangyx
 * @version 2.0.2
 */
public abstract class AbstractSmnClient implements SmnClient {

    /**
     * smn configuration
     */
    protected SmnConfiguration smnConfiguration;

    /**
     * http tool
     */
    protected HttpTool httpTool;

    /**
     * auth tool
     */
    protected IamAuth iamAuth;

    /**
     * http client configuration
     */
    protected ClientConfiguration clientConfiguration;

    /**
     * (non-doc)
     *
     * @param request
     * @see SmnClient#sendRequest(AbstractRequest)
     */
    public <T extends AbstractResponse> T sendRequest(AbstractRequest<T> request) {
        addRequestParamAndHeader(request);
        HttpResponse httpResponse = httpTool.getHttpResponse(request);

        int retryTimes = 1;
        while (httpResponse.getHttpCode() >= 500 && this.clientConfiguration.isAutoFailRetry()
                && retryTimes < this.clientConfiguration.getMaxRetryNum()) {
            httpResponse = httpTool.getHttpResponse(request);
            retryTimes++;
        }

        return request.getResponse(httpResponse);
    }

    /**
     * Add parameters and header to the request body
     *
     * @param request
     */
    public abstract void addRequestParamAndHeader(IHttpRequest request);
}
