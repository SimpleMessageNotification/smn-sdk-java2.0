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

import com.smn.config.ClientConfiguration;
import com.smn.config.SmnConfiguration;
import com.smn.http.HttpMethod;

import java.util.Map;

/**
 * the base interface of smn request message
 *
 * @author zhangyx
 * @version 2.0.0
 */
public interface IHttpRequest {

    /**
     * get http method {@link HttpMethod}
     *
     * @return {@link HttpMethod}
     */
    HttpMethod getHttpMethod();

    /**
     * get url of the request
     *
     * @return url string
     */
    String getUrl();

    /**
     * get json string of body parameters
     *
     * @return the json string
     */
    String getBodyParams();

    /**
     * get he headers
     *
     * @return the header map
     */
    Map<String, String> getHeaders();

    /**
     * add head to map
     *
     * @param key
     * @param value
     */
    void addHeader(String key, String value);

    /**
     * set project id
     *
     * @param projectId
     */
    void setProjectId(String projectId);

    /**
     * set smn configuration
     *
     * @param smnConfiguration
     */
    void setSmnConfiguration(SmnConfiguration smnConfiguration);

    /**
     * set client configuration
     *
     * @param clientConfiguration
     */
    void setClientConfiguration(ClientConfiguration clientConfiguration);
}
