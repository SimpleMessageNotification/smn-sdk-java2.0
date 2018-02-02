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

package com.smn.request.sms;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.sms.ListSmsTemplatesResponse;
import com.smn.util.ValidationUtil;

/**
 * the request of list sms templates
 *
 * @author zhangyx
 * @version 2.0.1
 */
public class ListSmsTemplatesRequest extends AbstractRequest<ListSmsTemplatesResponse> {
    /**
     * offset
     */
    private int offset = 0;

    /**
     * limit
     */
    private int limit = 100;

    /**
     * sms template name
     */
    private String smsTemplateName;

    /**
     * sms template type
     */
    private int smsTemplateType;

    /**
     * status
     */
    private int status;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    public String getUrl() {
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
                .append(Constants.URL_DELIMITER).append(Constants.SMS_TEMPLATE);
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListSmsTemplatesRequest setOffset(int offset) {
        this.offset = offset;
        this.queryMap.put("offset", String.valueOf(offset));
        return this;
    }

    public ListSmsTemplatesRequest setLimit(int limit) {
        this.limit = limit;
        this.queryMap.put("limit", String.valueOf(limit));
        return this;
    }

    public ListSmsTemplatesRequest setSmsTemplateName(String smsTemplateName) {
        this.smsTemplateName = smsTemplateName;
        this.queryMap.put("sms_template_name", smsTemplateName);
        return this;
    }

    public ListSmsTemplatesRequest setSmsTemplateType(int smsTemplateType) {
        this.smsTemplateType = smsTemplateType;
        this.queryMap.put("sms_template_type", String.valueOf(smsTemplateType));
        return this;
    }

    public ListSmsTemplatesRequest setStatus(int status) {
        this.status = status;
        this.queryMap.put("status", String.valueOf(status));
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public String getSmsTemplateName() {
        return smsTemplateName;
    }

    public int getSmsTemplateType() {
        return smsTemplateType;
    }

    public int getStatus() {
        return status;
    }
}
