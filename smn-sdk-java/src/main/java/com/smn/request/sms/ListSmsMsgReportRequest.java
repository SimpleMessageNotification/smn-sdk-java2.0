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
package com.smn.request.sms;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.util.ValidationUtil;

/**
 * list sms msg report
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListSmsMsgReportRequest extends AbstractRequest {
    /**
     * query start time
     */
    private String startTime;

    /**
     * query end time
     */
    private String endTime;

    /**
     * signature id
     */
    private String signId;

    /**
     * mobile
     */
    private String mobile;

    /**
     * sms send status
     * 2 sent without state
     * 1 send success
     * 0 send fail
     */
    private String status;

    /**
     * the offset of the query
     */
    private int offset = 0;

    /**
     * per page size limit
     */
    private int limit = 100;

    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public String getUrl() {
        if (!ValidationUtil.validateOffset(offset)) {
            throw new RuntimeException("offset is invalid.");
        }

        if (!ValidationUtil.validateLimit(limit)) {
            throw new RuntimeException("limit is invalid.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMN_SUB_PROTOCOL_SMS)
                .append(Constants.URL_DELIMITER).append(Constants.REPORT);
        // set query parameters
        sb.append(getQueryString());
        return sb.toString();
    }

    public ListSmsMsgReportRequest setStartTime(String startTime) {
        this.startTime = startTime;
        this.queryMap.put("start_time", startTime);
        return this;
    }

    public ListSmsMsgReportRequest setEndTime(String endTime) {
        this.endTime = endTime;
        this.queryMap.put("end_time", endTime);
        return this;
    }

    public ListSmsMsgReportRequest setSignId(String signId) {
        this.signId = signId;
        this.queryMap.put("sign_id", signId);
        return this;
    }

    public ListSmsMsgReportRequest setMobile(String mobile) {
        this.mobile = mobile;
        this.queryMap.put("mobile", mobile);
        return this;
    }

    public ListSmsMsgReportRequest setStatus(String status) {
        this.status = status;
        this.queryMap.put("status", status);
        return this;
    }

    public ListSmsMsgReportRequest setOffset(int offset) {
        this.offset = offset;
        this.queryMap.put("offset", offset);
        return this;
    }

    public ListSmsMsgReportRequest setLimit(int limit) {
        this.limit = limit;
        this.queryMap.put("limit", limit);
        return this;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSignId() {
        return signId;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStatus() {
        return status;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
