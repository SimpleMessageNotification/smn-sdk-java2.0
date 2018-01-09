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

package com.smn.example;

import com.smn.client.DefaultSmnClient;
import com.smn.client.SmnClient;
import com.smn.config.ClientConfiguration;
import com.smn.request.sms.SmsPublishRequest;
import com.smn.response.sms.SmsPublishResponse;

/**
 * 设置客户端参数 demo 项目
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class ClientConfigurationDemo {

    public static void main(String[] args) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        // 设置连接超时时间，单位毫秒， 默认60000
        clientConfiguration.setConnectTimeOut(80000);
        // 设置socket超时时间，单位毫秒， 默认60000
        clientConfiguration.setSocketTimeOut(80000);

        // 设置最大http连接池
        clientConfiguration.setMaxConnections(1000);
        // 设置每个路由最大连接数
        clientConfiguration.setMaxConnectionsPerRoute(1000);

        // 忽略客户端证书校验
        clientConfiguration.setIgnoreCertificate(true);
        // 设置代理地址
        clientConfiguration.setProxyHost("127.0.0.1");
        // 设置代理端口
        clientConfiguration.setProxyPort(808);
        // 设置代理认证用户名
        clientConfiguration.setProxyUserName("break");
        // 设置代理认证密码
        clientConfiguration.setProxyPassword("123456");

        // 初始化
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        // use aksk authentication
//        SmnClient smnClient = new AkskSmnClient(
//                "YourAccessKeyId",
//                "YourSecretAccessKey",
//                "YourRegionName",
//        clientConfiguration);

        // 发送短信，send sms
        smsPublish(smnClient);
    }

    /**
     * 发送短信
     */
    public static void smsPublish(SmnClient smnClient) {

        // 构造请求对象
        SmsPublishRequest smnRequest = new SmsPublishRequest();

        // 设置参数
        smnRequest.setEndpoint("+8613688807587")
                .setMessage("您的验证码是:1234，请查收")
                .setSignId("6be340e91e5241e4b5d85837e6709104");

        // 发送短信
        try {
            SmsPublishResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ",message_id:" + res.getMessageId()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}
