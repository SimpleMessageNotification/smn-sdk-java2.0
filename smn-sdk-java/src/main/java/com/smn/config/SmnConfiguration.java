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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.smn.common.Constants;
import com.smn.util.StringUtil;

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
     * SMN region and endpoint key-value
     */
    private HashMap<String, String> smnRegionAndEndpoints = new HashMap<String, String>();

    /**
     * IAM region and endpoint key-value
     */
    private HashMap<String, String> iamRegionAndEndpoints = new HashMap<String, String>();

    /**
     * new smnConfiguration
     *
     * @param userName
     *            the userName to set
     * @param password
     *            the password to set
     * @param domainName
     *            the domain name to set
     * @param regionId
     *            the region id to set
     */
    public SmnConfiguration(String userName, String password, String domainName, String regionId) {
        init();
        this.userName = userName;
        this.password = password;
        this.domainName = domainName;
        this.regionName = regionId;
        this.authType = TOKEN_AUTH_TYPE;
        this.smnHostUrl = Constants.HTTPS + getEndpointByRegionId(smnRegionAndEndpoints, regionName);
        this.iamHostUrl = Constants.HTTPS + getEndpointByRegionId(iamRegionAndEndpoints, regionName);
    }

    /**
     * new smnConfiguration
     *
     * @param secretAccessKey
     *            the secretAccessKey id to set
     * @param accessKeyId
     *            the accessKeyId id to set
     * @param regionId
     *            the region id to set
     */
    public SmnConfiguration(String accessKeyId, String secretAccessKey, String regionId) {
        init();
        this.regionName = regionId;
        this.accessKeyId = accessKeyId;
        this.secretAccessKey = secretAccessKey;
        this.authType = AKSK_AUTH_TYPE;
        this.smnHostUrl = Constants.HTTPS + getEndpointByRegionId(smnRegionAndEndpoints, regionName);
        this.iamHostUrl = Constants.HTTPS + getEndpointByRegionId(iamRegionAndEndpoints, regionName);
    }

    /**
     * init smn/iam region and endpoint key-value
     */
    private void init() {
        ClassLoader cls = this.getClass().getClassLoader();
        InputStream fileInput = cls.getResourceAsStream("region_endpoint_key_val");
        BufferedReader bufferedReader = null;
        String line = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(fileInput));
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(Constants.NOTE_SIGN) || StringUtil.isBlank(line)) {
                    continue;
                }
                String[] items = line.split(Constants.DOUBLE_COLON);
                if (items.length != Constants.ITEM_NUM) {
                    throw new RuntimeException("'" + line + "' is invalid format");
                }
                if (Constants.SMN.equals(items[0])) {
                    smnRegionAndEndpoints.put(items[1], items[2]);
                } else if (Constants.IAM.equals(items[0])) {
                    iamRegionAndEndpoints.put(items[1], items[2]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage()+",Failed to load region_endpoint_key_val !");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to close region_endpoint_key_val reader !");
            }

        }

    }

    /**
     * get smn/iam endpoint by main regionId
     */
    private String getEndpointByRegionId(Map<String, String> map, String regionId) {
        String mainRegionId = null;
        if (regionId != null) {
            if (regionId.contains(Constants.UNDERLINE)) {
                mainRegionId = regionId.split(Constants.UNDERLINE)[0];
            } else {
                mainRegionId = regionId;
            }
            if (!map.containsKey(mainRegionId)) {
                return "";
            }
            return map.get(mainRegionId);
        } else {
            throw new RuntimeException("regionId is null !");
        }
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
