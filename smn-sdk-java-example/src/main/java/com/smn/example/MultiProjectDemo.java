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
 * 多project场景样例
 *
 * @author zhangyx
 * @version 2.0.4
 */
public class MultiProjectDemo {

    public static void main(String[] args) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();

        // 多子project场景下使用。
        // 这里设置smn和iam host地址，填主project的地址，如子project是在华北区创建的，
        // 华北区就是https://smn.cn-north-1.myhuaweicloud.com和https://iam.cn-north-1.myhuaweicloud.com
        // 主project的终端地址可以在http://developer.huaweicloud.com/endpoint.html查询
        clientConfiguration.setSmnHostUrl("https://smn.cn-north-1.myhuaweicloud.com");
        clientConfiguration.setIamHostUrl("https://iam.cn-north-1.myhuaweicloud.com");

        // 初始化
        // regionName（YourMultiSubProjectName）填自定义的子project名，
        // 如cn-north-1_smn为自己定义的子project名
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountDomainName",
                "YourAccountPassword",
                "YourMultiSubProjectName",
                clientConfiguration);

        // use aksk authentication
//        SmnClient smnClient = new AkskSmnClient(
//                "YourAccessKeyId",
//                "YourSecretAccessKey",
//                "YourRegionName",
//                clientConfiguration);

        // 以下以发送短信为例，send sms
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

