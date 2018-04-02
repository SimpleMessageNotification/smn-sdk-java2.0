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

import com.fasterxml.jackson.annotation.JsonProperty;

public class SmsPublishMessage {
    /**
     * message access point
     */
    @JsonProperty("endpoint")
    private String endpoint;

    /**
     * message to send
     */
    @JsonProperty("message")
    private String message;

    /**
     * message signature id
     */
    @JsonProperty("sign_id")
    private String signId;

    /**
     * message_include_sign_flag
     */
    @JsonProperty("message_include_sign_flag")
    private boolean messageIncludeSignFlag = false;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public boolean isMessageIncludeSignFlag() {
        return messageIncludeSignFlag;
    }

    public void setMessageIncludeSignFlag(boolean messageIncludeSignFlag) {
        this.messageIncludeSignFlag = messageIncludeSignFlag;
    }
}
