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
 * mms publish data frame
 *
 * @author zhangyx
 * @version 2.0.5
 */
public class MmsFrame {
    /**
     * text
     */
    @JsonProperty("text")
    private String text;
    /**
     * image
     */
    @JsonProperty("image")
    private MmsFrameMessage image;
    /**
     * voice
     */
    @JsonProperty("voice")
    private MmsFrameMessage voice;

    public MmsFrame setText(String text) {
        this.text = text;
        return this;
    }

    public MmsFrame setImage(MmsFrameMessage image) {
        this.image = image;
        return this;
    }

    public MmsFrame setVoice(MmsFrameMessage voice) {
        this.voice = voice;
        return this;
    }

    public String getText() {
        return text;
    }

    public MmsFrameMessage getImage() {
        return image;
    }

    public MmsFrameMessage getVoice() {
        return voice;
    }
}
