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

package com.smn.response.sms;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

/**
 * the response data for get sms template detail
 *
 * @author zhangyx
 * @version 2.0.1
 */
public class GetSmsTemplateDetailResponse extends AbstractResponse {
    @JsonProperty("sms_template_name")
    private String smsTemplateName;
    @JsonProperty("sms_template_type")
    private int smsTemplateType;
    @JsonProperty("sms_template_content")
    private int smsTemplateContent;
    @JsonProperty("sms_template_id")
    private String smsTemplateId;
    @JsonProperty("reply")
    private String reply;
    @JsonProperty("status")
    private int status;
    @JsonProperty("create_time")
    private String createTime;
    @JsonProperty("validity_end_time")
    private String validityEndTime;

    public String getSmsTemplateName() {
        return smsTemplateName;
    }

    public void setSmsTemplateName(String smsTemplateName) {
        this.smsTemplateName = smsTemplateName;
    }

    public int getSmsTemplateType() {
        return smsTemplateType;
    }

    public void setSmsTemplateType(int smsTemplateType) {
        this.smsTemplateType = smsTemplateType;
    }

    public int getSmsTemplateContent() {
        return smsTemplateContent;
    }

    public void setSmsTemplateContent(int smsTemplateContent) {
        this.smsTemplateContent = smsTemplateContent;
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getValidityEndTime() {
        return validityEndTime;
    }

    public void setValidityEndTime(String validityEndTime) {
        this.validityEndTime = validityEndTime;
    }
}
