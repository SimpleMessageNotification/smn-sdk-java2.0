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
package com.smn.request.template;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.template.UpdateMessageTemplateResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

/**
 * the request of update message template
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class UpdateMessageTemplateRequest extends AbstractRequest<UpdateMessageTemplateResponse> {

    /**
     * messageTemplateId
     */
    private String messageTemplateId;

    /**
     * content
     */
    private String content;

    public HttpMethod getHttpMethod() {
        return HttpMethod.PUT;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(messageTemplateId)) {
            throw new NullPointerException("message template id is null");
        }

        if (!ValidationUtil.validateTemplateMessageContent(content)) {
            throw new IllegalArgumentException("content is invalid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.MESSAGE_TEMPLATE)
                .append(Constants.URL_DELIMITER).append(messageTemplateId);
        return sb.toString();
    }

    public UpdateMessageTemplateRequest setMessageTemplateId(String messageTemplateId) {
        this.messageTemplateId = messageTemplateId;
        this.bodyMap.put("message_template_id", messageTemplateId);
        return this;
    }

    public UpdateMessageTemplateRequest setContent(String content) {
        this.content = content;
        this.bodyMap.put("content", content);
        return this;
    }

    public String getMessageTemplateId() {
        return messageTemplateId;
    }

    public String getContent() {
        return content;
    }
}
