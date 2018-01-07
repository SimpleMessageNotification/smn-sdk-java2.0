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
package com.smn.common;

/**
 * String constants
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class Constants {

    private Constants() {
    }

    public final static String DEFAULT_CONTENT_TYPE = "application/json; charset=UTF-8";
    public final static String DEFAULT_CHARSET = "UTF-8";
    public static final String HTTPS = "https://";
    public static final String ENDPOINT = "myhwclouds.com";
    public static final String IAM = "iam";
    public static final String SMN = "smn";
    public static final String URL_DELIMITER = "/";
    public static final String V2 = "v2";
    public static final String IAM_TOKEN_URI = "/v3/auth/tokens";
    public static final String X_SUBJECT_TOKEN = "X-Subject-Token";
    public static final String TOKEN = "token";
    public static final String ID = "id";
    public static final String PROJECT = "project";
    public static final String EXPIRES_AT = "expires_at";
    public static final String SMN_NOTIFICATIONS = "notifications";

    public final static String SMS_SIGNATURE = "sms_sign";
    public static final String SMN_SUB_PROTOCOL_SMS = "sms";
    public final static String REPORT = "report";
    public final static String MESSAGE = "message";
    public final static String CALLBACK_REQUEST = "callback";

    public final static String MESSAGE_TEMPLATE = "message_template";


}
