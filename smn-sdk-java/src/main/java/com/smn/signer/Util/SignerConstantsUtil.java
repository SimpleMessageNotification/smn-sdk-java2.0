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

package com.smn.signer.Util;

/**
 * aksk constants
 *
 * @author zhangyx
 * @version 2.0.4
 */
public class SignerConstantsUtil {
    private SignerConstantsUtil() {

    }

    public static final String LINE_SEPARATOR = "\n";
    public static final String SDK_NAME = "SDK";
    public static final String SDK_TERMINATOR = "sdk_request";
    public static final String SDK_SIGNING_ALGORITHM = "SDK-HMAC-SHA256";
    public static final String X_SDK_DATE = "X-Sdk-Date";
    public static final String AUTHORIZATION = "Authorization";
    public static final String HOST = "Host";
    public static final int MAX_CACHE_SIZE = 300;
    public static final String DEFAULT_ENCODING = "UTF-8";
}
