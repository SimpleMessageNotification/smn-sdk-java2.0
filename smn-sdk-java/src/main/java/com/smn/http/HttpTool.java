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
package com.smn.http;

import com.smn.config.ClientConfiguration;
import com.smn.request.IHttpRequest;
import com.smn.util.StringUtil;
import com.smn.util.VersionUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * http tool
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class HttpTool {

    /**
     * http client configuration
     */
    private ClientConfiguration clientConfiguration;

    /**
     * construct
     *
     * @param clientConfiguration the client configruation to set
     */
    public HttpTool(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    /**
     * send http request and get response
     *
     * @param httpRequest
     * @return HttpResponse
     * @throws Exception
     */
    public HttpResponse getHttpResponse(IHttpRequest httpRequest) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = getHttpClient();
            HttpRequestBase httpRequestBase = createHttpRequest(httpRequest);
            response = httpclient.execute(httpRequestBase);

            HttpResponse httpResponse = new HttpResponse();
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String responseMessage = entity != null ? EntityUtils.toString(entity) : null;
            httpResponse.setHttpCode(status);
            httpResponse.setContent(responseMessage);
            httpResponse.setHeaders(response.getAllHeaders());
            return httpResponse;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get response, ErrorMessage is " + e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                //
            }
        }
    }

    /**
     * to create http request instance
     *
     * @param httpRequest http request message
     * @return the request instance
     */
    private HttpRequestBase createHttpRequest(IHttpRequest httpRequest) {
        HttpRequestBase httpRequestBase = null;
        if (httpRequest.getHttpMethod() == HttpMethod.GET) {
            HttpGet httpGet = new HttpGet(httpRequest.getUrl());
            httpRequestBase = httpGet;
        } else if (httpRequest.getHttpMethod() == HttpMethod.DELETE) {
            HttpDelete httpDelete = new HttpDelete(httpRequest.getUrl());
            httpRequestBase = httpDelete;
        } else if (httpRequest.getHttpMethod() == HttpMethod.POST) {
            HttpPost httpPost = new HttpPost(httpRequest.getUrl());
            httpPost.setEntity(new StringEntity(httpRequest.getBodyParams(), ContentType.APPLICATION_JSON));
            httpRequestBase = httpPost;
        } else if (httpRequest.getHttpMethod() == HttpMethod.PUT) {
            HttpPut httpPut = new HttpPut(httpRequest.getUrl());
            httpPut.setEntity(new StringEntity(httpRequest.getBodyParams(), ContentType.APPLICATION_JSON));
            httpRequestBase = httpPut;
        } else if (httpRequest.getHttpMethod() == HttpMethod.HEAD) {
            HttpHead httpHead = new HttpHead(httpRequest.getUrl());
            httpRequestBase = httpHead;
        } else {
            throw new IllegalArgumentException(String.format(
                    "Unsupported HTTP method:%s .", httpRequest.getHttpMethod().getName()));
        }
        httpRequestBase.setConfig(getRequestConfig());
        buildHttpHeader(httpRequest.getHeaders(), httpRequestBase);
        return httpRequestBase;
    }

    /**
     * Construct http header
     *
     * @param headerMap
     * @param httpRequestBase
     */
    private void buildHttpHeader(Map<String, String> headerMap, HttpRequestBase httpRequestBase) {
        if (headerMap == null || headerMap.isEmpty()) {
            return;
        }
        for (String headerKey : headerMap.keySet()) {
            httpRequestBase.addHeader(headerKey, headerMap.get(headerKey));
        }
    }

    /**
     * Construct httpclient with SSL protocol
     *
     * @return {@code CloseableHttpClient}
     * @throws Exception
     */
    public CloseableHttpClient getHttpClient() throws Exception {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        SSLConnectionSocketFactory sslSocketFactory = createSslConnectionSocketFactory();
        HttpClientBuilder builder = HttpClients.custom();

        // set proxy
        String proxyHost = clientConfiguration.getProxyHost();
        int proxyPort = clientConfiguration.getProxyPort();

        if (!StringUtil.isEmpty(proxyHost) && proxyPort > 0) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
            builder.setProxy(proxy);

            String username = clientConfiguration.getProxyUserName();
            String password = clientConfiguration.getProxyPassword();

            if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                AuthScope authscope = new AuthScope(proxy);
                Credentials credentials = new UsernamePasswordCredentials(username,
                        password);
                credentialsProvider.setCredentials(authscope, credentials);
                builder.setDefaultCredentialsProvider(credentialsProvider);
            }
        }
        builder.setUserAgent(VersionUtil.getDefaultUserAgent());
        CloseableHttpClient httpclient = builder.setSSLSocketFactory(sslSocketFactory).build();
        return httpclient;
    }

    private SSLConnectionSocketFactory createSslConnectionSocketFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContext sslContext = SSLContexts.custom().useProtocol("TLSV1.1")
                .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();

        // is ignore certificate verification
        if (clientConfiguration.isIgnoreCertificate()) {
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(null, new TrustManager[]{tm}, null);
        }
        return new SSLConnectionSocketFactory(sslContext,
                new NoopHostnameVerifier());
    }

    /**
     * Config request timeout milliseconds ,socket timeout milliseconds
     *
     * @return {@code RequestConfig}
     */
    public RequestConfig getRequestConfig() {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(clientConfiguration.getConnectTimeOut())
                .setSocketTimeout(clientConfiguration.getSocketTimeOut())
                .build();
        return requestConfig;
    }
}
