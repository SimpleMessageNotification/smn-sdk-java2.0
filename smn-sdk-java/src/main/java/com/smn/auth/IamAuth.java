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
package com.smn.auth;

import com.smn.config.SmnConfiguration;
import com.smn.http.HttpResponse;
import com.smn.http.HttpTool;
import com.smn.request.auth.GetProjectIdRequest;
import com.smn.request.auth.IamAuthRequest;
import com.smn.signer.AkskSigner;
import com.smn.util.DateUtil;
import com.smn.util.JsonUtil;
import com.smn.util.StringUtil;
import com.smn.util.VersionUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.smn.common.Constants.EXPIRES_AT;
import static com.smn.common.Constants.ID;
import static com.smn.common.Constants.PROJECT;
import static com.smn.common.Constants.TOKEN;
import static com.smn.common.Constants.X_SUBJECT_TOKEN;

/**
 * auth and cache token and project
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class IamAuth {
    /**
     * Token expired 30 minutes in advance
     */
    private long expiredInterval = 30 * 60 * 1000;

    private SmnConfiguration smnConfiguration;
    private HttpTool httpTool;
    private String projectId;
    private String authToken;
    private long expiresTime;
    private AkskSigner signer;

    /**
     * construct
     *
     * @param smnConfiguration
     * @param httpTool
     */
    public IamAuth(SmnConfiguration smnConfiguration, HttpTool httpTool) {
        this.smnConfiguration = smnConfiguration;
        this.httpTool = httpTool;
    }

    /**
     * construct
     *
     * @param smnConfiguration
     * @param httpTool
     * @param signer
     */
    public IamAuth(SmnConfiguration smnConfiguration, HttpTool httpTool, AkskSigner signer) {
        this.smnConfiguration = smnConfiguration;
        this.httpTool = httpTool;
        this.signer = signer;
    }

    /**
     * get token and project id
     *
     * @return first project_id, second token
     */
    public String[] getTokenAndProjectId() {
        if (authToken == null || isExpired()) {
            synchronized (this) {
                if (authToken == null || isExpired()) {
                    postForIamToken();
                }
            }
        }
        return new String[]{projectId, authToken};
    }

    /**
     * get the project Id for aksk
     * @return the project id
     */
    public String getProjectId() {
        if (StringUtil.isEmpty(this.projectId)) {
            synchronized (this) {
                if (StringUtil.isEmpty(this.projectId)) {
                    postForPojectId();
                }
            }
        }

        return this.projectId;
    }

    private void postForIamToken() {
        IamAuthRequest iamAuthRequest = new IamAuthRequest();
        iamAuthRequest.setSmnConfiguration(smnConfiguration);
        iamAuthRequest.addHeader("X-Smn-sdk", VersionUtil.getSdkVersion());

        HttpResponse response = null;
        try {
            // execute HTTPS post
            response = httpTool.getHttpResponse(iamAuthRequest);

            if (response.isSuccess()) {
                // The length of the response will not be too long
                String responseMessage = response.getContent();
                // get IAM token
                this.authToken = response.getHeaders().get(X_SUBJECT_TOKEN);
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                // set projectId
                this.projectId = parseProjectId(messageMap);
                // set expires at
                String expirtAt = ((Map) messageMap.get(TOKEN)).get(EXPIRES_AT).toString();
                // parse time
                Date tempDate = DateUtil.parseUTCDate(expirtAt);
                this.expiresTime = tempDate.getTime() - expiredInterval;
            } else {
                throw new RuntimeException(
                        "Unexpected response status: " + response.getHttpCode() + ", ErrorMessage is " + response.getContent());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void postForPojectId() {
        GetProjectIdRequest request = new GetProjectIdRequest();
        request.setName(smnConfiguration.getRegionName());
        request.addHeader("X-Smn-sdk", VersionUtil.getSdkVersion());

        HttpResponse response = null;
        try {
            // 签名处理
            signer.get(request, new URL(request.getUrl()));

            // execute HTTPS post
            response = httpTool.getHttpResponse(request);
            // The length of the response will not be too long
            String responseMessage = response.getContent();
            if (response.isSuccess()) {
                Map<String, Object> messageMap = JsonUtil.parseJsonMessage(responseMessage);
                List projectList = (ArrayList) messageMap.get("projects");
                if (projectList == null || projectList.size() == 0) {
                    throw new RuntimeException("Fail to get project id by aksk auth. projects is empty.");
                }
                this.projectId = (String) ((HashMap) (projectList).get(0)).get("id");
            } else {
                throw new RuntimeException("Fail to get project id by aksk auth. http response status: " + response.getHttpCode());
            }

            if (StringUtil.isEmpty(this.projectId)) {
                throw new RuntimeException("Fail to get project id by aksk auth. project id is null");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String parseProjectId(Map<String, Object> messageMap) {
        try {
            return ((Map) ((Map) messageMap.get(TOKEN)).get(PROJECT)).get(ID).toString();
        } catch (Exception e) {
            return null;
        }
    }

    private boolean isExpired() {
        return expiresTime <= System.currentTimeMillis();
    }
}
