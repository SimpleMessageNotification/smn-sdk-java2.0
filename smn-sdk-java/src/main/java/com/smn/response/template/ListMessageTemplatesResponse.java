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
package com.smn.response.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

import java.util.List;

/**
 * the response data of list message template
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ListMessageTemplatesResponse extends AbstractResponse {

    @JsonProperty("message_template_count")
    private int messageTemplateCount;

    @JsonProperty("message_templates")
    private List<MessageTemplateListInfo> messageTemplates;

    public int getMessageTemplateCount() {
        return messageTemplateCount;
    }

    public void setMessageTemplateCount(int messageTemplateCount) {
        this.messageTemplateCount = messageTemplateCount;
    }

    public List<MessageTemplateListInfo> getMessageTemplates() {
        return messageTemplates;
    }

    public void setMessageTemplates(List<MessageTemplateListInfo> messageTemplates) {
        this.messageTemplates = messageTemplates;
    }
}