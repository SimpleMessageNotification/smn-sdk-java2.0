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
package com.smn.client;

import com.smn.request.AbstractRequest;
import com.smn.response.AbstractResponse;

/**
 * smn client interface
 *
 * @author zhangyx
 * @version 2.0.0
 */
public interface SmnClient {

    /**
     * send the request and get response
     *
     * @param request the request to send
     * @param <T>
     * @return response data
     */
    <T extends AbstractResponse> T sendRequest(AbstractRequest<T> request);
}
