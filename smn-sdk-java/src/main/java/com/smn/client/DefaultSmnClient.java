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
import com.smn.common.Constants;
import com.smn.config.ClientConfiguration;
import com.smn.config.SmnConfiguration;
import com.smn.http.HttpResponse;
import com.smn.http.HttpTool;
import com.smn.request.AbstractRequest;
import com.smn.request.IHttpRequest;
import com.smn.response.AbstractResponse;
import com.smn.util.StringUtil;
import com.smn.util.VersionUtil;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * default smn client for account and password
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class DefaultSmnClient implements SmnClient {
    /**
     * smn configuration
     */
    private SmnConfiguration smnConfiguration;

    /**
     * http tool
     */
    private HttpTool httpTool;

    /**
     * auth tool
     */
    private IamAuth iamAuth;

    /**
     * constructor for token authentication
     *
     * @param userName   the userName to set
     * @param password   the password to set
     * @param domainName the domainName to set
     * @param regionId   the regionId to set
     */
    public DefaultSmnClient(String userName, String domainName, String password, String regionId) {
        this(userName, domainName, password, regionId, null);
    }

    /**
     * constructor for token authentication
     *
     * @param userName            the userName to set
     * @param password            the password to set
     * @param domainName          the domainName to set
     * @param regionId            the regionId to set
     * @param clientConfiguration the client configuration
     */
    public DefaultSmnClient(String userName, String domainName, String password, String regionId, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = new SmnConfiguration(userName, password, domainName, regionId);

        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }
        this.httpTool = new HttpTool(clientConfiguration);

        this.iamAuth = new IamAuth(smnConfiguration, httpTool);
    }

    /**
     * (non-doc)
     *
     * @see SmnClient#sendRequest(AbstractRequest)
     */
    public <T extends AbstractResponse> T sendRequest(AbstractRequest<T> request) {
        String[] arrays = this.iamAuth.getTokenAndProjectId();
        request.setSmnConfiguration(this.smnConfiguration);
        addHeader(request, arrays[1], arrays[0]);
        request.setProjectId(arrays[0]);
        HttpResponse httpResponse = httpTool.getHttpResponse(request);

        return request.getResponse(httpResponse);
    }

    public void addHeader(IHttpRequest request, String token, String projectId) {
        if (StringUtil.isEmpty(projectId)) {
            throw new NullPointerException("project id is null");
        }
        request.addHeader("region", smnConfiguration.getRegionName());
        request.addHeader("X-Auth-Token", token);
        request.addHeader("X-Project-Id", projectId);
        request.addHeader("X-Smn-sdk", VersionUtil.getSdkVersion());
        request.addHeader("Content-Type", Constants.DEFAULT_CONTENT_TYPE);
    }
}
