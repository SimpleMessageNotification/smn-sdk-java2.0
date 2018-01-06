package com.smn.response.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

public class SmsPublishResponse extends AbstractResponse {
    @JsonProperty("message_id")
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
