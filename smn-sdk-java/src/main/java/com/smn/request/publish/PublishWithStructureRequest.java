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
 * the request of publish with structure
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class PublishWithStructureRequest extends AbstractRequest<PublishResponse> {
    /// <summary>
    /// topic urn
    /// </summary>
    private String topicUrn;

    /// <summary>
    /// subject
    /// </summary>
    private String subject;

    /// <summary>
    /// message_structure
    /// </summary>
    private Map<String, Object> messageStructure;

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

        if (!ValidationUtil.validateMessageStructure(messageStructure)) {
            throw new IllegalArgumentException("message structure is oversize");
        }

        if (!ValidationUtil.validateMessageStructureDefault(messageStructure)) {
            throw new IllegalArgumentException("message structure not contain default message");
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

    public PublishWithStructureRequest setTopicUrn(String topicUrn) {
        this.topicUrn = topicUrn;
        return this;
    }

    public PublishWithStructureRequest setSubject(String subject) {
        this.subject = subject;
        this.bodyMap.put("subject", subject);
        return this;
    }

    public PublishWithStructureRequest setMessageStructure(Map<String, Object> messageStructure) {
        this.messageStructure = messageStructure;
        this.bodyMap.put("message_structure", messageStructure);
        return this;
    }

    public String getTopicUrn() {
        return topicUrn;
    }

    public String getSubject() {
        return subject;
    }

    public Map<String, Object> getMessageStructure() {
        return messageStructure;
    }
}
