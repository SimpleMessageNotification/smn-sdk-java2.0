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
import com.smn.request.auth.IamAuthRequest;
import com.smn.util.DateUtil;
import com.smn.util.JsonUtil;

import java.util.Date;
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

    private void postForIamToken() {
        IamAuthRequest iamAuthRequest = new IamAuthRequest();
        iamAuthRequest.setSmnConfiguration(smnConfiguration);
        HttpResponse response = null;
        try {
            // execute HTTPS post
            response = httpTool.getHttpResponse(iamAuthRequest);

            int status = response.getHttpCode();
            // The length of the response will not be too long

            String responseMessage = response.getContent();
            if (status >= 200 && status < 300) {
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
                        "Unexpected response status: " + status + ", ErrorMessage is " + responseMessage);
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
