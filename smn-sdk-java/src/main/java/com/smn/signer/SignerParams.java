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

package com.smn.signer;

import com.smn.signer.Util.SignerConstantsUtil;
import com.smn.signer.Util.SignerUtil;

/**
 * the params for Signer
 *
 * @version 2.0.4
 */
public class SignerParams {
    private String formattedSigningDate;
    private String formattedSigningDateTime;
    private long signingDateTimeMilli;
    private String regionName;
    private String serviceName;
    private String signingAlgorithm;
    private String scope;

    public SignerParams(String regionName, String serviceName, String signingAlgorithm) {
        this.signingDateTimeMilli = getSigningDate();
        this.formattedSigningDate = SignerUtil.formatDateStamp(this.signingDateTimeMilli);
        this.formattedSigningDateTime = SignerUtil.formatTimestamp(this.signingDateTimeMilli);
        this.serviceName = serviceName;
        this.regionName = regionName;
        this.signingAlgorithm = signingAlgorithm;
        this.scope = generateScope(this.formattedSigningDate, serviceName, regionName);
    }

    private final long getSigningDate() {
        return System.currentTimeMillis();
    }

    private String generateScope(String dateStamp, String serviceName, String regionName) {
        StringBuilder scopeBuilder = new StringBuilder();
        return scopeBuilder.append(dateStamp).append("/")
                .append(regionName).append("/")
                .append(serviceName).append("/")
                .append(SignerConstantsUtil.SDK_TERMINATOR).toString();
    }

    public String getFormattedSigningDate() {
        return formattedSigningDate;
    }

    public String getFormattedSigningDateTime() {
        return formattedSigningDateTime;
    }

    public long getSigningDateTimeMilli() {
        return signingDateTimeMilli;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getSigningAlgorithm() {
        return signingAlgorithm;
    }

    public String getScope() {
        return scope;
    }
}
