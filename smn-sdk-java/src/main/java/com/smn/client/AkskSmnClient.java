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
import com.smn.http.HttpMethod;
import com.smn.http.HttpResponse;
import com.smn.http.HttpTool;
import com.smn.request.AbstractRequest;
import com.smn.request.IHttpRequest;
import com.smn.response.AbstractResponse;
import com.smn.signer.AkskSigner;
import com.smn.util.StringUtil;
import com.smn.util.VersionUtil;

import java.net.URL;

/**
 * aksk smn client for use aksk authentication method
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class AkskSmnClient implements SmnClient {
    /**
     * smn configuration
     */
    private SmnConfiguration smnConfiguration;

    /**
     * http tool
     */
    private HttpTool httpTool;

    /**
     * signer tool
     */
    private AkskSigner signer;

    /**
     * auth tool
     */
    private IamAuth iamAuth;

    /**
     * constructor for aksk authentication
     *
     * @param accessKeyId     the accessKeyId to set
     * @param secretAccessKey the secretAccessKey to set
     * @param regionName      the regionName to set
     */
    public AkskSmnClient(String accessKeyId, String secretAccessKey, String regionName) {
        this(accessKeyId, secretAccessKey, regionName, null);
    }

    /**
     * constructor for aksk authentication
     *
     * @param accessKeyId         the accessKeyId to set
     * @param secretAccessKey     the secretAccessKey to set
     * @param regionName          the regionId to set
     * @param clientConfiguration the client configuration
     */
    public AkskSmnClient(String accessKeyId, String secretAccessKey, String regionName, ClientConfiguration clientConfiguration) {
        this.smnConfiguration = new SmnConfiguration(accessKeyId, secretAccessKey, regionName);

        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }
        this.httpTool = new HttpTool(clientConfiguration);

        this.signer = new AkskSigner(smnConfiguration, Constants.SMN_SERVICE_NAME);

        this.iamAuth = new IamAuth(smnConfiguration, httpTool, signer);
    }

    public <T extends AbstractResponse> T sendRequest(AbstractRequest<T> request) {
        request.setSmnConfiguration(this.smnConfiguration);
        String projectId = iamAuth.getProjectId();
        addHeader(request, projectId);
        request.setProjectId(projectId);
        HttpResponse httpResponse = httpTool.getHttpResponse(request);
        return request.getResponse(httpResponse);
    }

    /**
     * add header for aksk
     *
     * @param request request message
     */
    private void addHeader(IHttpRequest request, String projectId) {
        if (StringUtil.isEmpty(projectId)) {
            throw new NullPointerException("project id is null");
        }
        request.addHeader("region", smnConfiguration.getRegionName());
        request.addHeader("X-Project-Id", projectId);
        request.addHeader("X-Smn-sdk", VersionUtil.getSdkVersion());
        request.addHeader("Content-Type", Constants.DEFAULT_CONTENT_TYPE);

        try {
            HttpMethod httpMethod = request.getHttpMethod();
            String url = request.getUrl();
            if (httpMethod == HttpMethod.GET) {
                signer.get(request, new URL(url));
            } else if (httpMethod == HttpMethod.DELETE) {
                signer.delete(request, new URL(url));
            } else if (httpMethod == HttpMethod.POST) {
                signer.post(request, new URL(url), request.getBodyParams());
            } else if (httpMethod == HttpMethod.PUT) {
                signer.put(request, new URL(url), request.getBodyParams());
            } else {
                throw new IllegalArgumentException(String.format(
                        "Unsupported HTTP method:%s .", httpMethod.getName()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to sign for aksk.");
        }
    }
}
