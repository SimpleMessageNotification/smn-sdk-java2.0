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
import com.smn.response.sms.DeleteSmsTemplateResponse;
import com.smn.util.StringUtil;

/**
 * the request of delete sms templates
 *
 * @author zhangyx
 * @version 2.0.1
 */
public class DeleteSmsTemplateRequest extends AbstractRequest<DeleteSmsTemplateResponse> {
    /**
     * sms template id
     */
    private String smsTemplateId;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.DELETE;
    }

    @Override
    public String getUrl() {
        if (StringUtil.isBlank(smsTemplateId)) {
            throw new NullPointerException("sms template id is null.");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.SMS_TEMPLATE)
                .append(Constants.URL_DELIMITER).append(smsTemplateId);
        return sb.toString();
    }

    public String getSmsTemplateId() {
        return smsTemplateId;
    }

    public void setSmsTemplateId(String smsTemplateId) {
        this.smsTemplateId = smsTemplateId;
    }
}
