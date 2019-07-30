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
import com.smn.config.ClientConfiguration;
import com.smn.config.SmnConfiguration;
import com.smn.http.HttpMethod;
import com.smn.http.HttpResponse;
import com.smn.response.AbstractResponse;
import com.smn.util.JsonUtil;
import com.smn.util.StringUtil;
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

    /**
     * request headers
     */
    private Map<String, String> headerMap;

    /**
     * smn configuration
     */
    protected SmnConfiguration smnConfiguration;

    /**
     * client configuration
     */
    private ClientConfiguration clientConfiguration;

    /**
     * body parameters
     * <p>
     * for post and put method
     */
    protected Map<String, Object> bodyMap;

    /**
     * query parameters
     * <p>
     * for get method
     */
    protected Map<String, String> queryMap;

    /**
     * user project id
     */
    protected String projectId;

    public AbstractRequest() {
        this.headerMap = new HashMap<String, String>();
        this.bodyMap = new HashMap<String, Object>();
        this.queryMap = new HashMap<String, String>();
    }

    public abstract HttpMethod getHttpMethod();

    public abstract String getUrl();

    /**
     * get json string of body parameters
     *
     * @return the json string
     */
    public String getBodyParams() {
        return JsonUtil.getJsonStringByMap(bodyMap);
    }

    /**
     * get he headers
     *
     * @return the header map
     */
    public Map<String, String> getHeaders() {
        return headerMap;
    }

    /**
     * add head to map
     *
     * @param key
     * @param value
     */
    public void addHeader(String key, String value) {
        if (headerMap == null) {
            headerMap = new HashMap<String, String>();
        }
        headerMap.put(key, value);
    }

    /**
     * parse httpResponse
     *
     * @param httpResponse
     *            the response data
     * @return the response entity of the request
     */
    public T getResponse(HttpResponse httpResponse) {
        try {
            String responseMessage = httpResponse.getContent();
            Class<? super T> rawType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            T t = (T) JsonUtil.parseJsonToObject(responseMessage, rawType);
            t.setHttpCode(httpResponse.getHttpCode());
            t.setContentString(responseMessage);

            return t;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * get query string for get method
     *
     * @return query string
     */
    protected String getQueryString() {
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

    /**
     * get sms service url
     *
     * @return smn service url
     */
    protected String getSmnServiceUrl() {
        if (clientConfiguration != null && !StringUtil.isBlank(clientConfiguration.getSmnHostUrl())) {
            return clientConfiguration.getSmnHostUrl();
        }

        String smnHostUrl = smnConfiguration.getSmnHostUrl();
        if (smnHostUrl == null || Constants.HTTPS.equals(smnHostUrl.trim())) {
            throw new RuntimeException("Not found smnHostUrl by regionId in the region_endpoint_key_val file !"
                                               + "please use setSmnHostUrl(String smnHostUrl) of com.smn.config.ClientConfiguration to set smnHostUrl "
                                               + "or add smnHostUrl entry in the region_endpoint_key_val file.");
        }
        return smnHostUrl;
    }

    /**
     * get iam service url
     *
     * @return iam service url
     */
    protected String getIamServiceUrl() {
        if (clientConfiguration != null && !StringUtil.isBlank(clientConfiguration.getIamHostUrl())) {
            return clientConfiguration.getIamHostUrl();
        }

        String iamHostUrl = smnConfiguration.getIamHostUrl();
        if (iamHostUrl == null || Constants.HTTPS.equals(iamHostUrl.trim())) {
            throw new RuntimeException("Not found iamHostUrl by regionId in the region_endpoint_key_val file !"
                                               + "please use setIamHostUrl(String iamHostUrl) of com.smn.config.ClientConfiguration to set iamHostUrl "
                                               + "or add iamHostUrl entry in the region_endpoint_key_val file.");
        }
        return iamHostUrl;
    }

    /**
     * get iam global url
     *
     * @return iam global url
     */
    protected String getIamGlobalUrl() {
        if (clientConfiguration != null && !StringUtil.isBlank(clientConfiguration.getIamGlobalUrl())) {
            return clientConfiguration.getIamGlobalUrl();
        }

        String iamGlobalUrl = smnConfiguration.getIamGlobalUrl();
        if (iamGlobalUrl == null || Constants.HTTPS.equals(iamGlobalUrl.trim())) {
            throw new RuntimeException("Not found iamGlobalUrl by global param in the region_endpoint_key_val file !"
                    + "please use setIamGlobalUrl(String iamGlobalUrl) of com.smn.config.ClientConfiguration to set" +
                    " iamGlobalUrl "
                    + "or add iamGlobalUrl entry in the region_endpoint_key_val file.");
        }
        return iamGlobalUrl;
    }

    /**
     * get project id for get method
     *
     * @return project id
     */
    public String getProjectId() {
        return projectId;
    }

    /**
     * set project id
     *
     * @param projectId
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    /**
     * set smn configuration
     *
     * @param smnConfiguration
     */
    public void setSmnConfiguration(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }

    /**
     * set client configuration
     *
     * @param clientConfiguration
     */
    public void setClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }
}
