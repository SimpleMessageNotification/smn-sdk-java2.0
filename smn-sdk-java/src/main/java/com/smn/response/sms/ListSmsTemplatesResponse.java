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
 * the response data for delete sms sign
 *
 * @author zhangyx
 * @version 2.0.1
 */
public class ListSmsTemplatesResponse extends AbstractResponse {
    @JsonProperty("count")
    private int count;

    @JsonProperty("sms_templates")
    private List<SmsTemplate> smsTemplates;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SmsTemplate> getSmsTemplates() {
        return smsTemplates;
    }

    public void setSmsTemplates(List<SmsTemplate> smsTemplates) {
        this.smsTemplates = smsTemplates;
    }
}
