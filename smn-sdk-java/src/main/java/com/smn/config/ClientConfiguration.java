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

/**
 * the configuration for client, contain http configuration
 *
 * @author zhangyx
 * @version 0.9
 */
public class ClientConfiguration {

    /**
     * is auto failure retry
     */
    protected boolean autoFailRetry = false;

    /**
     * the max retry num
     */
    protected int maxRetryNum;

    /**
     * connect time out ,in millisecond
     */
    private int connectTimeOut = 60000;

    /**
     * read time out,in millisecond
     */
    private int socketTimeOut = 60000;

    /**
     * ignore certificate verification
     */
    private boolean ignoreCertificate = false;

    /**
     * Key library path
     */
    private String keyStorePath;

    /**
     * Key library cipher
     */
    private String keyStorePass;

    /**
     * 最大HTTP连接数。
     * <p>
     * The maximum number of HTTP connections.
     */
    private int maxConnections = 4000;

    /**
     * 每个路由默认最大HTTP连接数
     * <p>
     * The default maximum HTTP connection number for each route
     */
    private int maxConnectionsPerRoute = 4000;
    /**
     * proxy host
     */
    private String proxyHost;

    /**
     * proxy port
     */
    private int proxyPort;

    /**
     * proxy username
     */
    private String proxyUserName;

    /**
     * proxy password
     */
    private String proxyPassword;

    /**
     * proxy domain
     */
    private String proxyDomain;

    /**
     * proxy workstatiion
     */
    private String proxyWorkstation;

    /**
     * @return isAutoFailRetry
     */
    public boolean isAutoFailRetry() {
        return autoFailRetry;
    }

    /**
     * @param autoFailRetry the autoFailRetry to set
     */
    public void setAutoFailRetry(boolean autoFailRetry) {
        this.autoFailRetry = autoFailRetry;
    }

    /**
     * @return maxRetryNum
     */
    public int getMaxRetryNum() {
        return maxRetryNum;
    }

    /**
     * @param maxRetryNum the maxRetryNum to set
     */
    public void setMaxRetryNum(int maxRetryNum) {
        this.maxRetryNum = maxRetryNum;
    }

    /**
     * @return the connect timeout
     */
    public int getConnectTimeOut() {
        return connectTimeOut;
    }

    /**
     * @param connectTimeOut the connection timeout to set
     */
    public void setConnectTimeOut(int connectTimeOut) {
        this.connectTimeOut = connectTimeOut;
    }

    /**
     * @return the socket timeout
     */
    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    /**
     * @return the proxy host
     */
    public String getProxyHost() {
        return proxyHost;
    }

    /**
     * @param proxyHost the proxy host to set
     */
    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * @return the proxy port
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * @param proxyPort the proxy port to set
     */
    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * @return the proxy username
     */
    public String getProxyUserName() {
        return proxyUserName;
    }

    /**
     * @param proxyUserName the proxy username to set
     */
    public void setProxyUserName(String proxyUserName) {
        this.proxyUserName = proxyUserName;
    }

    /**
     * @return the proxy password
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    /**
     * @param proxyPassword the proxy password to set
     */
    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /**
     * @return the proxy domain
     */
    public String getProxyDomain() {
        return proxyDomain;
    }

    /**
     * @param proxyDomain the proxy domain to set
     */
    public void setProxyDomain(String proxyDomain) {
        this.proxyDomain = proxyDomain;
    }

    /**
     * @return the proxy workstation
     */
    public String getProxyWorkstation() {
        return proxyWorkstation;
    }

    /**
     * @param proxyWorkstation the proxy workstation to set
     */
    public void setProxyWorkstation(String proxyWorkstation) {
        this.proxyWorkstation = proxyWorkstation;
    }

    /**
     * @param socketTimeOut the socket timeout to set
     */
    public void setSocketTimeOut(int socketTimeOut) {
        this.socketTimeOut = socketTimeOut;
    }

    /**
     * @return is ignore certificate verification
     */
    public boolean isIgnoreCertificate() {
        return ignoreCertificate;
    }

    /**
     * @param ignoreCertificate the ignoreCertificate value to set
     */
    public void setIgnoreCertificate(boolean ignoreCertificate) {
        this.ignoreCertificate = ignoreCertificate;
    }

    /**
     * @return Key library path
     */
    public String getKeyStorePath() {
        return keyStorePath;
    }

    /**
     * @param keyStorePath Key library path
     */
    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }

    /**
     * @return Key library cipher
     */
    public String getKeyStorePass() {
        return keyStorePass;
    }

    /**
     * @param keyStorePass Key library cipher
     */
    public void setKeyStorePass(String keyStorePass) {
        this.keyStorePass = keyStorePass;
    }

    /**
     * @return The maximum number of HTTP connections.
     */
    public int getMaxConnections() {
        return maxConnections;
    }

    /**
     * @param maxConnections The maximum number of HTTP connections.
     */
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    /**
     * @return The default maximum HTTP connection number for each route
     */
    public int getMaxConnectionsPerRoute() {
        return maxConnectionsPerRoute;
    }

    /**
     * @param maxConnectionsPerRoute The default maximum HTTP connection number for each route
     */
    public void setMaxConnectionsPerRoute(int maxConnectionsPerRoute) {
        this.maxConnectionsPerRoute = maxConnectionsPerRoute;
    }
}
