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
import com.smn.response.template.CreateMessageTemplateResponse;
import com.smn.util.ValidationUtil;

/**
 * the request of create message template
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class CreateMessageTemplateRequest extends AbstractRequest<CreateMessageTemplateResponse> {
    /**
     * message_template_name
     */
    private String messageTemplateName;

    /**
     * content
     */
    private String content;

    /**
     * protocol
     */
    private String protocol;

    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    public String getUrl() {
        if (!ValidationUtil.validateTemplateName(messageTemplateName)) {
            throw new IllegalArgumentException("message template name is invalid");
        }

        if (!ValidationUtil.validateTemplateMessageContent(content)) {
            throw new IllegalArgumentException("content is invalid");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.MESSAGE_TEMPLATE);
        return sb.toString();
    }

    public CreateMessageTemplateRequest setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
        this.bodyMap.put("message_template_name", messageTemplateName);
        return this;
    }

    public CreateMessageTemplateRequest setContent(String content) {
        this.content = content;
        this.bodyMap.put("content", content);
        return this;
    }

    public CreateMessageTemplateRequest setProtocol(String protocol) {
        this.protocol = protocol;
        this.bodyMap.put("protocol", protocol);
        return this;
    }

    public String getMessageTemplateName() {
        return messageTemplateName;
    }

    public String getContent() {
        return content;
    }

    public String getProtocol() {
        return protocol;
    }
}
