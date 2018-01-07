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
package com.smn.request.publish;

import com.smn.common.Constants;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.publish.PublishResponse;
import com.smn.util.StringUtil;
import com.smn.util.ValidationUtil;

import java.util.Map;

/**
 * the request of publish with template
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class PublishWithTemplateRequest extends AbstractRequest<PublishResponse> {

    /**
     * topic urn
     */
    private String topicUrn;

    /**
     * subject
     */
    private String subject;

    /**
     * message_template_name
     */
    private String messageTemplateName;

    /**
     * tags
     */
    private Map<String, String> tags;

    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    public String getUrl() {
        if (StringUtil.isEmpty(topicUrn)) {
            throw new NullPointerException("topic urn is null");
        }

        if (!ValidationUtil.validateSubject(subject)) {
            throw new IllegalArgumentException("subject is invalid");
        }

        if (StringUtil.isEmpty(messageTemplateName)) {
            throw new NullPointerException("message template name is null");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getSmnServiceUrl());
        sb.append(Constants.URL_DELIMITER).append(Constants.V2).append(Constants.URL_DELIMITER)
                .append(projectId).append(Constants.URL_DELIMITER).append(Constants.SMN_NOTIFICATIONS)
                .append(Constants.URL_DELIMITER).append(Constants.TOPICS)
                .append(Constants.URL_DELIMITER).append(topicUrn)
                .append(Constants.URL_DELIMITER).append(Constants.PUBLISH);
        return sb.toString();
    }

    public PublishWithTemplateRequest setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
        return this;
    }

    public PublishWithTemplateRequest setSubject(String subject) {
        this.subject = subject;
        this.bodyMap.put("subject", subject);
        return this;
    }

    public PublishWithTemplateRequest setMessageTemplateName(String messageTemplateName) {
        this.messageTemplateName = messageTemplateName;
        this.bodyMap.put("message_template_name", messageTemplateName);
        return this;
    }

    public PublishWithTemplateRequest setTags(Map<String, String> tags) {
        this.tags = tags;
        this.bodyMap.put("tags", tags);
        return this;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessageTemplateName() {
        return messageTemplateName;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}
