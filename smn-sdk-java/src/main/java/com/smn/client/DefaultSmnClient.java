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

/**
 * default smn client use account and password
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class DefaultSmnClient extends AbstractSmnClient {

    /**
     * constructor for token authentication
     *
     * @param userName   the userName to set
     * @param password   the password to set
     * @param domainName the domainName to set
     * @param regionName the regionId to set
     */
    public DefaultSmnClient(String userName, String domainName, String password, String regionName) {
        this(userName, domainName, password, regionName, null);
    }

    /**
     * constructor for token authentication
     *
     * @param userName            the userName to set
     * @param password            the password to set
     * @param domainName          the domainName to set
     * @param regionName          the regionId to set
     * @param clientConfiguration the client configuration
     */
    public DefaultSmnClient(String userName, String domainName, String password, String regionName, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = new SmnConfiguration(userName, password, domainName, regionName);

        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        this.clientConfiguration = clientConfiguration;

        this.httpTool = new HttpTool(clientConfiguration);

        this.iamAuth = new IamAuth(smnConfiguration, clientConfiguration, httpTool);
    }

    /**
     * (non-doc)
     *
     * @param request
     * @see AbstractSmnClient#addRequestParamAndHeader(IHttpRequest)
     */
    @Override
    public void addRequestParamAndHeader(IHttpRequest request) {
        String[] arrays = this.iamAuth.getTokenAndProjectId();
        if (StringUtil.isEmpty(arrays[0])) {
            throw new NullPointerException("project id is null");
        }

        request.setProjectId(arrays[0]);
        request.setClientConfiguration(this.clientConfiguration);
        request.setSmnConfiguration(this.smnConfiguration);

        request.addHeader("region", smnConfiguration.getRegionName());
        request.addHeader("X-Auth-Token", arrays[1]);
        request.addHeader("X-Project-Id", arrays[0]);
        request.addHeader("X-Smn-sdk", VersionUtil.getSdkVersion());
        request.addHeader("Content-Type", Constants.DEFAULT_CONTENT_TYPE);
    }
}
