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
