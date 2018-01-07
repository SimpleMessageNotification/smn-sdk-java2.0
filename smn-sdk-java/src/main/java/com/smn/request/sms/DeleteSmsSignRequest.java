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
import com.smn.response.sms.DeleteSmsSignResponse;
import com.smn.util.StringUtil;

/**
 * delete sms sign request message
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class DeleteSmsSignRequest extends AbstractRequest<DeleteSmsSignResponse> {
    /**
     * signature identitier
     */
    private String signId;

    public HttpMethod getHttpMethod() {
        return HttpMethod.DELETE;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(signId)) {
            throw new NullPointerException("sign id is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMS_SIGNATURE)
                .append(Constants.URL_DELIMITER).append(signId);

        return sb.toString();
    }

    /**
     * @return the signId
     */
    public String getSignId() {
        return signId;
    }

    /**
     * @param signId the signId to set
     */
    public DeleteSmsSignRequest setSignId(String signId) {
        this.signId = signId;
        return this;
    }
}
