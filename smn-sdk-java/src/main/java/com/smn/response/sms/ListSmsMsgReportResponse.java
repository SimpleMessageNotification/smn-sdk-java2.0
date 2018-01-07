/*
 * Copyright (C) 2017. Huawei Technologies Co., LTD. All rights reserved.
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
 * the response data of list Sms msg report
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListSmsMsgReportResponse extends AbstractResponse {

    @JsonProperty("data")
    private int count;

    @JsonProperty("count")
    private List<SmsReportData> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SmsReportData> getData() {
        return data;
    }

    public void setData(List<SmsReportData> data) {
        this.data = data;
    }
}

/**
 * Sms report data
 *
 * @author zhangyx
 * @version 2.0.0
 */
class SmsReportData {

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("status")
    private int status;

    @JsonProperty("sign_id")
    private String signId;

    @JsonProperty("status_desc")
    private String statusDesc;

    @JsonProperty("fee_num")
    private int feeNum;

    @JsonProperty("extend_code")
    private String extendCode;

    @JsonProperty("nation_code")
    private String nationCode;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("submit_time")
    private String submitTime;

    @JsonProperty("deliver_time")
    private String deliverTime;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public int getFeeNum() {
        return feeNum;
    }

    public void setFeeNum(int feeNum) {
        this.feeNum = feeNum;
    }

    public String getExtendCode() {
        return extendCode;
    }

    public void setExtendCode(String extendCode) {
        this.extendCode = extendCode;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(String deliverTime) {
        this.deliverTime = deliverTime;
    }
}