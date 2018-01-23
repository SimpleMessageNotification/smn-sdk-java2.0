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
package com.smn.config;

import com.smn.common.Constants;

/**
 * property loading configuration
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class SmnConfiguration {

    /**
     * token auth type
     */
    public final static String TOKEN_AUTH_TYPE = "token";

    /**
     * ak/sk auth type
     */
    public final static String AKSK_AUTH_TYPE = "aksk";

    /**
     * 认证方式
     */
    private String authType;

    /**
     * user name
     */
    private String userName;

    /**
     * user's password
     */
    private String password;

    /**
     * domain name ,is same with user name mostly,but sometimes may differ
     */
    private String domainName;

    /**
     * region id
     */
    private String regionName;

    /**
     * access key id
     */
    private String accessKeyId;

    /**
     * secret access key
     */
    private String secretAccessKey;

    /**
     * smn service host url
     */
    private String smnHostUrl;

    /**
     * identity and access host url
     */
    private String iamHostUrl;

    /**
     * new smnConfiguration
     *
     * @param userName   the userName to set
     * @param password   the password to set
     * @param domainName the domain name to set
     * @param regionId   the region id to set
     */
    public SmnConfiguration(String userName, String password, String domainName, String regionId) {
        this.userName = userName;
        this.password = password;
        this.domainName = domainName;
        this.regionName = regionId;
        this.authType = TOKEN_AUTH_TYPE;
        this.smnHostUrl = Constants.HTTPS + Constants.SMN + "." + regionName + "." + Constants.ENDPOINT;
        this.iamHostUrl  = Constants.HTTPS + Constants.IAM + "." + regionName + "." + Constants.ENDPOINT;
    }

    /**
     * new smnConfiguration
     *
     * @param secretAccessKey the secretAccessKey id to set
     * @param accessKeyId     the accessKeyId id to set
     * @param regionId        the region id to set
     */
    public SmnConfiguration(String accessKeyId, String secretAccessKey, String regionId) {
        this.regionName = regionId;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.authType = AKSK_AUTH_TYPE;
        this.smnHostUrl = Constants.HTTPS + Constants.SMN + "." + regionName + "." + Constants.ENDPOINT;
        this.iamHostUrl  = Constants.HTTPS + Constants.IAM + "." + regionName + "." + Constants.ENDPOINT;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }


    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * @return the domainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @return the accessKeyId
     */
    public String getAccessKeyId() {
        return accessKeyId;
    }

    /**
     * @return the secretAccessKey
     */
    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @return the smnHostUrl
     */
    public String getSmnHostUrl() {
        return smnHostUrl;
    }

    /**
     * @return the iamHostUrl
     */
    public String getIamHostUrl() {
        return iamHostUrl;
    }
}
