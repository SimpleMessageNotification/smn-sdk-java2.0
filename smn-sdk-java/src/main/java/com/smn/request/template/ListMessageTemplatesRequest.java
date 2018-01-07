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
package com.smn.request.template;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.template.ListMessageTemplatesResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

/**
 * the request of list message template
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListMessageTemplatesRequest extends AbstractRequest<ListMessageTemplatesResponse> {

    /**
     * messageTemplateName
     */
    private String messageTemplateName;

    /**
     * protocol
     */
    private String protocol;

    /**
     * offset
     */
    private int offset = 0;

    /**
     * limit
     */
    private int limit = 100;

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getUrl() {
        if (!StringUtil.isEmpty(messageTemplateName) && !ValidationUtil.validateTemplateName(messageTemplateName)) {
            throw new IllegalArgumentException("message template name is invalid");
        }

        if (!ValidationUtil.validateOffset(offset)) {
            throw new IllegalArgumentException("offset is invalid");
        }

        if (!ValidationUtil.validateLimit(limit)) {
            throw new IllegalArgumentException("limit is invalid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.MESSAGE_TEMPLATE);

        // set query parameters
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListMessageTemplatesRequest setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
        this.queryMap.put("message_template_name", messageTemplateName);
        return this;
    }

    public ListMessageTemplatesRequest setProtocol(String protocol) {
        this.protocol = protocol;
        this.queryMap.put("protocol", protocol);
        return this;
    }

    public ListMessageTemplatesRequest setOffset(int offset) {
        this.offset = offset;
        this.queryMap.put("offset", String.valueOf(offset));
        return this;
    }

    public ListMessageTemplatesRequest setLimit(int limit) {
        this.limit = limit;
        this.queryMap.put("limit", String.valueOf(limit));
        return this;
    }

    public String getMessageTemplateName() {
        return messageTemplateName;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
