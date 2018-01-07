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
import com.smn.request.subscription.ListSubscriptionsByTopicRequest;
import com.smn.request.subscription.ListSubscriptionsRequest;
import com.smn.request.subscription.SubscribeRequest;
import com.smn.request.subscription.UnsubscribeRequest;
import com.smn.response.subscription.ListSubscriptionsByTopicResponse;
import com.smn.response.subscription.ListSubscriptionsResponse;
import com.smn.response.subscription.SubscribeResponse;
import com.smn.response.subscription.SubscriptionInfo;
import com.smn.response.subscription.UnsubscribeResponse;

import java.util.List;

/**
 * subscription demo
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class SubscriptionDemo {
    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        //subscribe
        subscribe(smnClient);

        // list subscriptions
        listSubscriptions(smnClient);

        // list subscriptions by topic
        listSubscriptionsByTopic(smnClient);

        // unsubscribe
        unsubscribe(smnClient);
    }


    /**
     * 添加订阅
     *
     * @param smnClient
     */
    public static void subscribe(SmnClient smnClient) {
        // 构造请求对象
        SubscribeRequest smnRequest = new SubscribeRequest();

        // 设置参数
        smnRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_test_csharp")
                .setProtocol("sms")
                .setEndpoint("13688807587")
                .setRemark("hehe");

        // 发送请求
        try {
            SubscribeResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", subscription_urn:" + res.getSubscriptionUrn()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 分页返回请求者的所有的订阅列表
     *
     * @param smnClient
     */
    public static void listSubscriptions(SmnClient smnClient) {
        // 构造请求对象
        ListSubscriptionsRequest smnRequest = new ListSubscriptionsRequest();

        // 设置参数
        smnRequest.setLimit(10)
                .setOffset(0);

        // 发送请求
        try {
            ListSubscriptionsResponse res = smnClient.sendRequest(smnRequest);
            List<SubscriptionInfo> subscriptionInfoList = res.getSubscriptions();
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", subscription_count:" + res.getSubscriptionCount()
                    + ", firstCount: " + subscriptionInfoList.size()
                    + ", subscriptions:" + res.getSubscriptions()
                    + ", errormessage:" + res.getMessage());
            for (SubscriptionInfo info : subscriptionInfoList) {
                System.out.println("{" +
                        "topicUrn='" + info.getTopicUrn() + '\'' +
                        ", protocol='" + info.getProtocol() + '\'' +
                        ", subscriptionUrn='" + info.getSubscriptionUrn() + '\'' +
                        ", owner='" + info.getOwner() + '\'' +
                        ", endpoint='" + info.getEndpoint() + '\'' +
                        ", remark='" + info.getRemark() + '\'' +
                        ", status=" + info.getStatus() +
                        '}');
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 分页获取特定主题的订阅列表
     *
     * @param smnClient
     */
    public static void listSubscriptionsByTopic(SmnClient smnClient) {
        // 构造请求对象
        ListSubscriptionsByTopicRequest smnRequest = new ListSubscriptionsByTopicRequest();

        // 设置参数
        smnRequest.setLimit(10)
                .setOffset(0)
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_test_csharp");

        // 发送请求
        try {
            ListSubscriptionsByTopicResponse res = smnClient.sendRequest(smnRequest);
            List<SubscriptionInfo> subscriptionInfoList = res.getSubscriptions();
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", subscription_count:" + res.getSubscriptionCount()
                    + ", subscriptions:" + res.getSubscriptions()
                    + ", errormessage:" + res.getMessage());

            for (SubscriptionInfo info : subscriptionInfoList) {
                System.out.println("{" +
                        "topicUrn='" + info.getTopicUrn() + '\'' +
                        ", protocol='" + info.getProtocol() + '\'' +
                        ", subscriptionUrn='" + info.getSubscriptionUrn() + '\'' +
                        ", owner='" + info.getOwner() + '\'' +
                        ", endpoint='" + info.getEndpoint() + '\'' +
                        ", remark='" + info.getRemark() + '\'' +
                        ", status=" + info.getStatus() +
                        '}');
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 取消订阅
     *
     * @param smnClient
     */
    public static void unsubscribe(SmnClient smnClient) {
        // 构造请求对象
        UnsubscribeRequest smnRequest = new UnsubscribeRequest();

        // 设置参数
        smnRequest.setSubscriptionUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_test_csharp:ad5ba40ded04400bbe5e116138cdb4fb");

        // 发送请求
        try {
            UnsubscribeResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

}
