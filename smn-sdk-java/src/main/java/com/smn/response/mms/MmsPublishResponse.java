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

package com.smn.response.mms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smn.response.AbstractResponse;

import java.util.List;

/**
 * batch publish mms result entity
 *
 * @author zhangyx
 * @version 2.0.5
 */
public class MmsPublishResponse extends AbstractResponse {
    @JsonProperty("result")
    private List<MmsPublishResult> result;

    public List<MmsPublishResult> getResult() {
        return result;
    }

    public void setResult(List<MmsPublishResult> result) {
        this.result = result;
    }
}