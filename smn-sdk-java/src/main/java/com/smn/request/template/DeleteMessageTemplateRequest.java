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
package com.smn.request.template;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.template.DeleteMessageTemplateResponse;
import com.smn.util.StringUtil;

/**
 * the request of delete message template
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class DeleteMessageTemplateRequest extends AbstractRequest<DeleteMessageTemplateResponse> {

    /**
     * message_template_id
     */
    private String messageTemplateId;

    public HttpMethod getHttpMethod() {
        return HttpMethod.DELETE;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(messageTemplateId)) {
            throw new NullPointerException("message template id is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.MESSAGE_TEMPLATE)
                .append(Constants.URL_DELIMITER).append(messageTemplateId);
        return sb.toString();
    }

    public DeleteMessageTemplateRequest setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
        return this;
    }

    public String getMessageTemplateId() {
        return messageTemplateId;
    }
}
