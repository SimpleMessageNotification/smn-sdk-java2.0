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
import com.smn.response.sms.CreateSmsTemplateResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

/**
 * the request of create sms template
 *
 * @author zhangyx
 * @version 2.0.1
 */
public class CreateSmsTemplateRequest extends AbstractRequest<CreateSmsTemplateResponse> {
    /**
     * sms template name
     */
    private String smsTemplateName;

    /**
     * sms template content
     */
    private String smsTemplateContent;

    /**
     * remark
     */
    private String remark;

    /**
     * sms template type
     */
    private int smsTemplateType;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public String getUrl() {

        if(!ValidationUtil.validateSmsTemplateName(smsTemplateName)) {
            throw new IllegalArgumentException("sms template name is invalid");
        }

        if(StringUtil.isEmpty(smsTemplateContent)) {
            throw new NullPointerException("sms template content is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMS_TEMPLATE);
        return sb.toString();
    }

    public CreateSmsTemplateRequest setSmsTemplateName(String smsTemplateName) {
        this.smsTemplateName = smsTemplateName;
        this.bodyMap.put("sms_template_name", smsTemplateName);
        return this;
    }

    public CreateSmsTemplateRequest setSmsTemplateContent(String smsTemplateContent) {
        this.smsTemplateContent = smsTemplateContent;
        this.bodyMap.put("sms_template_content", smsTemplateContent);
        return this;
    }

    public CreateSmsTemplateRequest setRemark(String remark) {
        this.remark = remark;
        this.bodyMap.put("remark", remark);
        return this;
    }

    public CreateSmsTemplateRequest setSmsTemplateType(int smsTemplateType) {
        this.smsTemplateType = smsTemplateType;
        this.bodyMap.put("sms_template_type", smsTemplateType);
        return this;
    }

    public String getSmsTemplateName() {
        return smsTemplateName;
    }

    public String getSmsTemplateContent() {
        return smsTemplateContent;
    }

    public String getRemark() {
        return remark;
    }

    public int getSmsTemplateType() {
        return smsTemplateType;
    }
}
