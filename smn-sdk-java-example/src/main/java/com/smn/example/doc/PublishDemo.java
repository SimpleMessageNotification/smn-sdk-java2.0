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

package com.smn.example.doc;

import com.smn.client.DefaultSmnClient;
import com.smn.client.SmnClient;
import com.smn.request.publish.PublishRequest;
import com.smn.response.publish.PublishResponse;

public class PublishDemo {
    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        // 构造发布消息请求对象
        PublishRequest smnRequest = new PublishRequest();

        // 设置参数,主题，消息内容，topicUrn
        smnRequest.setSubject("message subject")
                .setMessage("message test content")
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4*****86e132:testTopicUrn");

        // 发送请求
        try {
            PublishResponse res = smnClient.sendRequest(smnRequest);
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
