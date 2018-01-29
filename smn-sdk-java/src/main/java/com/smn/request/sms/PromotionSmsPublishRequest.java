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
import com.smn.response.sms.PromotionSmsPublishResponse;

import java.util.List;

/**
 * the request of publish promotion sms
 *
 * @author zhangyx
 * @version 2.0.1
 */
public class PromotionSmsPublishRequest extends AbstractRequest<PromotionSmsPublishResponse> {
    /**
     * sms template id
     */
    private String smsTemplateId;
    /**
     * sign id
     */
    private String signId;
    /**
     * endpoints
     */
    private List<String> endpoints;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUrl() {
        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMS_PROMOTION);
        return sb.toString();
    }

    public PromotionSmsPublishRequest setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
        this.bodyMap.put("sms_template_id", smsTemplateId);
        return this;
    }

    public PromotionSmsPublishRequest setSignId(String signId) {
        this.signId = signId;
        this.bodyMap.put("sign_id", signId);
        return this;
    }

    public PromotionSmsPublishRequest setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
        this.bodyMap.put("endpoints", endpoints);
        return this;
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public String getSignId() {
        return signId;
    }

    public List<String> getEndpoints() {
        return endpoints;
    }
}
