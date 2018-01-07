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
package com.smn.request;

import com.smn.common.Constants;
import com.smn.config.SmnConfiguration;
import com.smn.http.HttpMethod;
import com.smn.http.HttpResponse;
import com.smn.response.AbstractResponse;
import com.smn.util.JsonUtil;
import com.smn.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * abstract request mesasge
 *
 * @author zhangyx
 * @version 2.0.0
 */
public abstract class AbstractRequest<T extends AbstractResponse> implements IHttpRequest {

    private Map<String, String> headerMap;
    private SmnConfiguration smnConfiguration;
    protected Map<String, Object> bodyMap;
    protected Map<String, String> queryMap;
    protected String projectId;

    public AbstractRequest() {
        this.headerMap = new HashMap<String, String>();
        this.bodyMap = new HashMap<String, Object>();
        this.queryMap = new HashMap<String, String>();
    }

    public abstract HttpMethod getHttpMethod();

    public abstract String getUrl();

    public String getBodyParams() {
        return JsonUtil.getJsonStringByMap(bodyMap);
    }

    public Map<String, String> getHeaders() {
        return headerMap;
    }

    public void addHeader(String key, String value) {
        if (headerMap == null) {
            headerMap = new HashMap<String, String>();
        }
        headerMap.put(key, value);
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSmnServiceUrl() {
        return Constants.HTTPS + Constants.SMN + "." + smnConfiguration.getRegionName() + "." + Constants.ENDPOINT;
    }

    public void setSmnConfiguration(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }

    public T getResponse(HttpResponse httpResponse) {
        try {
            String responseMessage = httpResponse.getContent();
            Class<? super T> rawType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T t = (T) JsonUtil.parseJsonToObject(responseMessage, rawType);
            t.setHttpCode(httpResponse.getHttpCode());
            t.setContentString(responseMessage);

            return t;
        } catch (Exception e) {
            throw new RuntimeException("Fail to convert response, ErrorMessage is " + e.getCause().getMessage());
        }
    }

    public SmnConfiguration getSmnConfiguration() {
        return smnConfiguration;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getQueryString() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : queryMap.entrySet()) {
            String key = entry.getKey();
            String val = entry.getValue();
            if (val != null) {
                nameValuePairs.add(new BasicNameValuePair(key, val));
            }
        }
        String param = "";
        if (!nameValuePairs.isEmpty()) {
            try {
                param = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("UTF-8")));
            } catch (IOException e) {
                throw new RuntimeException("get request param error");
            }
        }

        if (!StringUtil.isEmpty(param)) {
            param = "?" + param;
        }
        return param;
    }
}
