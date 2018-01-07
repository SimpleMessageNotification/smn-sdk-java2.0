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
 * the type of protocol
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ProtocolType {
    private ProtocolType(){

    }

    public final static String EMAIL = "email";
    public final static String SMS = "sms";
    public final static String HTTP = "http";
    public final static String HTTPS = "https";
    public final static String DMS = "dms";
    public final static String FUNCTIONSTAGE = "functionstage";
}
