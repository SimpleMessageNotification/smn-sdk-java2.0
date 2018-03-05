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
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * http tool
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class HttpTool {
    private static final int DEFAULT_MAX_CONNECTIONS = 4000;
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 4000;
    private static final int DEFAULT_CONNECT_TIMEOUT = 60000;
    private static final int DEFAULT_SOCKET_TIMEOUT = 60000;

    // protocol order map
    private static final HashMap<String, String> PROTOCOL_MAP = new HashMap<String, String>();

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

        // 循环校验http协议是否支持，依次顺序是TLSv1.2->TLSv1.1->TLSv1.0->TLS,映射关系
        PROTOCOL_MAP.put("TLSv1.2", "TLSv1.1");
        PROTOCOL_MAP.put("TLSv1.1", "TLSv1.0");
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

        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
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
    public CloseableHttpClient getHttpClient()
            throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException, UnrecoverableKeyException {
        if (clientConfiguration == null) {
            clientConfiguration = new ClientConfiguration();
        }

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", createSslConnectionSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        connManager.setMaxTotal(clientConfiguration.getMaxConnections() <= 0
                ? DEFAULT_MAX_CONNECTIONS
                : clientConfiguration.getMaxConnections());
        connManager.setDefaultMaxPerRoute(clientConfiguration.getMaxConnectionsPerRoute() <= 0
                ? DEFAULT_MAX_CONNECTIONS_PER_ROUTE
                : clientConfiguration.getMaxConnectionsPerRoute());

        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(connManager);

        // set proxy
        setProxy(httpClientBuilder);

        // set user agent
        httpClientBuilder.setUserAgent(VersionUtil.getDefaultUserAgent());
        return httpClientBuilder.build();
    }

    /**
     * set http proxy
     *
     * @param httpClientBuilder
     */
    private void setProxy(HttpClientBuilder httpClientBuilder) {
        String proxyHost = clientConfiguration.getProxyHost();
        int proxyPort = clientConfiguration.getProxyPort();

        if (proxyHost != null && proxyPort > 0) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);

            httpClientBuilder.setProxy(proxy);

            // add proxy credentials
            addProxyCredentials(httpClientBuilder, proxy);
        }
    }

    /**
     * add proxy credentials
     *
     * @param httpClientBuilder http client builder
     * @param proxy             http proxy
     */
    private void addProxyCredentials(HttpClientBuilder httpClientBuilder, HttpHost proxy) {
        String proxyUsername = clientConfiguration.getProxyUserName();
        String proxyPassword = clientConfiguration.getProxyPassword();

        if (proxyUsername != null && proxyPassword != null) {
            String proxyDomain = clientConfiguration.getProxyDomain();
            String proxyWorkstation = clientConfiguration.getProxyWorkstation();

            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

            credentialsProvider.setCredentials(new AuthScope(proxy),
                    new NTCredentials(proxyUsername, proxyPassword,
                            proxyWorkstation, proxyDomain)
            );

            httpClientBuilder
                    .setDefaultCredentialsProvider(credentialsProvider);

        }
    }

    private SSLConnectionSocketFactory createSslConnectionSocketFactory()
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException {

        // default
        SSLContext sslContext = createSSLContext("TLSv1.2");

        return new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
    }


    /**
     * 循环校验http协议是否支持，依次顺序是TLSv1.2->TLSv1.1->TLSv1.0->TLS
     * <p>
     * Loop check whether the HTTP protocol is supported
     * in sequence is TLSv1.2->TLSv1.1->TLSv1.0->TLS
     *
     * @param protocol current protocol to check
     * @return SSLContext
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws CertificateException
     * @throws IOException
     */
    private SSLContext createSSLContext(String protocol)
            throws KeyStoreException, KeyManagementException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
        SSLContextBuilder builder = new SSLContextBuilder().useProtocol(protocol);

        try {
            if (!StringUtil.isBlank(clientConfiguration.getKeyStorePath())
                    && !StringUtil.isBlank(clientConfiguration.getKeyStorePass())
                    && !StringUtil.isBlank(clientConfiguration.getKeyPass())) {
                builder.loadKeyMaterial(
                        new File(clientConfiguration.getKeyStorePath()),
                        clientConfiguration.getKeyStorePass().toCharArray(),
                        clientConfiguration.getKeyPass().toCharArray());
            }

            //忽略服务器证书验证
            if (clientConfiguration.isIgnoreCertificate()) {
                builder.loadTrustMaterial(new TrustStrategy() {
                    public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        return true;
                    }
                });
            } else {
                if (!StringUtil.isBlank(clientConfiguration.getTrustStorePass())
                        && !StringUtil.isBlank(clientConfiguration.getTrustStorePath())) {
                    builder.loadTrustMaterial(
                            new File(clientConfiguration.getKeyStorePath()),
                            clientConfiguration.getTrustStorePass().toCharArray(),
                            new TrustSelfSignedStrategy());
                }
            }

            return builder.build();
        } catch (NoSuchAlgorithmException e) {
            if (PROTOCOL_MAP.get(protocol) != null) {
                // 继续重试下一个协议是否支持.
                // Continue to retry the next protocol to support.
                createSSLContext(PROTOCOL_MAP.get(protocol));
            } else {
                // 如果所有的协议都重试了，则抛出异常.
                // If all the protocols are retried, the exception is thrown.
                throw e;
            }
        }
        return builder.build();
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
                .setConnectTimeout(clientConfiguration.getConnectTimeOut() <= 0
                        ? DEFAULT_CONNECT_TIMEOUT : clientConfiguration.getConnectTimeOut())
                .setSocketTimeout(clientConfiguration.getSocketTimeOut() <= 0
                        ? DEFAULT_SOCKET_TIMEOUT : clientConfiguration.getSocketTimeOut())
                .build();
        return requestConfig;
    }
}
