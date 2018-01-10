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
import com.smn.request.subscription.SubscribeRequest;
import com.smn.request.topic.CreateTopicRequest;
import com.smn.response.subscription.SubscribeResponse;
import com.smn.response.topic.CreateTopicResponse;

public class TopicDemo {

    public static void main(String[] args) {
        // 初始化smn client实例
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        // 构造创建主题请求对象
        CreateTopicRequest createTopicRequest = new CreateTopicRequest();

        // 设置创建主题请求对象的参数,别名和主题名
        createTopicRequest.setDisplayName("topic display name")
                .setName("test_topic_name");

        CreateTopicResponse createTopicResponse;
        // 发送短信
        try {
            createTopicResponse = smnClient.sendRequest(createTopicRequest);
            if (!createTopicResponse.isSuccess()) {
                System.out.println("create topic fail.");
                return;
            }
        } catch (Exception e) {
            // 处理异常
            return;
        }

        String topicUrn = createTopicResponse.getTopicUrn();

        // 构造订阅请求对象
        SubscribeRequest subscribeRequest = new SubscribeRequest();

        // 设置参数, topicUrn,协议，终端，备注
        subscribeRequest.setTopicUrn(topicUrn)
                .setProtocol("sms")
                .setEndpoint("13688807587")
                .setRemark("hehe");

        // 发送请求
        try {
            SubscribeResponse subscribeResponse = smnClient.sendRequest(subscribeRequest);
            if (!subscribeResponse.isSuccess()) {
                System.out.println("subscribe fail.");
                return;
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }

    }
}

