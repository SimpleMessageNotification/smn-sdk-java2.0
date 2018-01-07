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
import com.smn.request.sms.SmsPublishRequest;
import com.smn.response.sms.SmsPublishResponse;

public class SmsDemo {
    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionId");
        smsPublish(smnClient);
    }

    /**
     * 发送短信
     */
    public static void smsPublish(SmnClient smnClient) {

        // 构造请求对象
        SmsPublishRequest smsPublishRequest = new SmsPublishRequest();

        // 设置参数
        smsPublishRequest.setEndpoint("+861368***587")
                .setMessage("您的验证码是:1234，请查收")
                .setSignId("6be340e91e5241e4b5d85837e6709104");

        // 发送短信
        try {
            SmsPublishResponse res = smnClient.sendRequest(smsPublishRequest);
            System.out.println("httpCode:" + res.getHttpCode() +
                    ",message_id:" + res.getMessageId() + ", request_id:" + res.getRequestId());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}
