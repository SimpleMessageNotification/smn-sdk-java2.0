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
package com.smn.http;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;

/**
 * http response data
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class HttpResponse {

    private int httpCode;
    private String content;
    private Map<String, String> headers;


    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = new HashMap<String, String>();
        for (Header header : headers) {
            this.headers.put(header.getName(), header.getValue());
        }
    }
}
