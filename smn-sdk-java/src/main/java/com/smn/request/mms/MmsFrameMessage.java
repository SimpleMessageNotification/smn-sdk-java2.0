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

package com.smn.request.mms;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * mms publish message
 *
 * @author zhangyx
 * @version 2.0.5
 */
public class MmsFrameMessage {
    /**
     * file_type
     */
    @JsonProperty("file_type")
    private String fileType;
    /**
     * content_base63
     */
    @JsonProperty("content_base64")
    private String contentBase64;

    public MmsFrameMessage setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public MmsFrameMessage setContentBase64(String contentBase64) {
        this.contentBase64 = contentBase64;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public String getContentBase64() {
        return contentBase64;
    }
}
