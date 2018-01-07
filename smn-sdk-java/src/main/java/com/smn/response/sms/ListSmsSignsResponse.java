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

import java.util.List;

/**
 * the response data for list sms signs
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListSmsSignsResponse extends AbstractResponse {
    @JsonProperty("sms_sign_count")
    private int smsSignCount;

    @JsonProperty("sms_signs")
    private List<SmsSignInfo> smsSigns;

    public int getSmsSignCount() {
        return smsSignCount;
    }

    public void setSmsSignCount(int smsSignCount) {
        this.smsSignCount = smsSignCount;
    }

    public List<SmsSignInfo> getSmsSigns() {
        return smsSigns;
    }

    public void setSmsSigns(List<SmsSignInfo> smsSigns) {
        this.smsSigns = smsSigns;
    }
}

/**
 * sms sign info data
 *
 * @author zhangyx
 * @version 2.0.0
 */
class SmsSignInfo {
    @JsonProperty("sign_name")
    private String signName;

    @JsonProperty("create_time")
    private String createTime;

    @JsonProperty("sign_id")
    private String signId;

    @JsonProperty("reply")
    private String reply;

    @JsonProperty("status")
    private int status;

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
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
}
