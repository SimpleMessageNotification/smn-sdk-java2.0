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

import com.smn.http.HttpMethod;

import java.util.Map;

/**
 * the base interface of smn request message
 *
 * @author zhangyx
 * @version 2.0.0
 */
public interface IHttpRequest {

    HttpMethod getHttpMethod();

    String getUrl();

    String getBodyParams();

    Map<String, String> getHeaders();

    void addHeader(String key, String value);

    void setProjectId(String projectId);
}
