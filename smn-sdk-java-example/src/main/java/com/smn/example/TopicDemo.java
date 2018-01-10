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
import com.smn.request.topic.CreateTopicRequest;
import com.smn.request.topic.DeleteTopicAttributeByNameRequest;
import com.smn.request.topic.DeleteTopicAttributesRequest;
import com.smn.request.topic.DeleteTopicRequest;
import com.smn.request.topic.ListTopicAttributesRequest;
import com.smn.request.topic.ListTopicsRequest;
import com.smn.request.topic.QueryTopicDetailRequest;
import com.smn.request.topic.UpdateTopicAttributeRequest;
import com.smn.request.topic.UpdateTopicRequest;
import com.smn.response.topic.CreateTopicResponse;
import com.smn.response.topic.DeleteTopicAttributeByNameResponse;
import com.smn.response.topic.DeleteTopicAttributesResponse;
import com.smn.response.topic.DeleteTopicResponse;
import com.smn.response.topic.ListTopicAttributesResponse;
import com.smn.response.topic.ListTopicsResponse;
import com.smn.response.topic.QueryTopicDetailResponse;
import com.smn.response.topic.TopicInfo;
import com.smn.response.topic.UpdateTopicAttributeResponse;
import com.smn.response.topic.UpdateTopicResponse;

import java.util.List;

/**
 * topic demo
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class TopicDemo {
    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        // use aksk authentication
//        SmnClient smnClient = new AkskSmnClient(
//                "YourAccessKeyId",
//                "YourSecretAccessKey",
//                "YourRegionName"
//        );

        // create topic
        createTopic(smnClient);

        // update topic
        updateTopic(smnClient);

        //query topic detail
        queryTopicDetail(smnClient);

        //list topics
        listTopics(smnClient);

        //update topic attribute
        updateTopicAttribute(smnClient);

        // list topic attributes
        listTopicAttributes(smnClient);

        //delete topic attribute by name
        deleteTopicAttributeByName(smnClient);

        //delete topic attributes
        deleteTopicAttributes(smnClient);

        // delete topic
        deleteTopic(smnClient);
    }

    /**
     * 创建topic
     */
    public static void createTopic(SmnClient smnClient) {
        // 构造请求对象
        CreateTopicRequest smnRequest = new CreateTopicRequest();

        // 设置参数
        smnRequest.setDisplayName("helloworld")
                .setName("create_by_zhangyx_java2");

        // 发送短信
        try {
            CreateTopicResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode() +
                    ",topic_urn:" + res.getTopicUrn() + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 更新topic
     */
    public static void updateTopic(SmnClient smnClient) {
        // 构造请求对象
        UpdateTopicRequest smnRequest = new UpdateTopicRequest();

        // 设置参数
        smnRequest.setDisplayName("helloworld_222")
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2");

        // 发送短信
        try {
            UpdateTopicResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode() +
                    ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询topic详情
     */
    public static void queryTopicDetail(SmnClient smnClient) {
        // 构造请求对象
        QueryTopicDetailRequest smnRequest = new QueryTopicDetailRequest();

        // 设置参数
        smnRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2");

        // 发送短信
        try {
            QueryTopicDetailResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ",displayName:" + res.getDisplayName()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询topics列表
     */
    public static void listTopics(SmnClient smnClient) {
        // 构造请求对象
        ListTopicsRequest smnRequest = new ListTopicsRequest();

        // 设置参数
        smnRequest.setLimit(10).setOffset(0);

        // 发送短信
        try {
            ListTopicsResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ",topicCount:" + res.getTopicCount()
                    + ", errorMessage:" + res.getMessage());

            List<TopicInfo> topics = res.getTopics();
            for (TopicInfo topicInfo : topics) {
                System.out.println("TopicInfo{" +
                        "name='" + topicInfo.getName() + '\'' +
                        ", topicUrn='" + topicInfo.getTopicUrn() + '\'' +
                        ", displayName='" + topicInfo.getDisplayName() + '\'' +
                        ", pushPolicy='" + topicInfo.getPushPolicy() + '\'' +
                        '}');
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 删除topic
     */
    public static void deleteTopic(SmnClient smnClient) {
        // 构造请求对象
        DeleteTopicRequest smnRequest = new DeleteTopicRequest();

        // 设置参数
        smnRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2");

        // 发送短信
        try {
            DeleteTopicResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 更新topic attribute
     */
    public static void updateTopicAttribute(SmnClient smnClient) {
        // 构造请求对象
        UpdateTopicAttributeRequest smnRequest = new UpdateTopicAttributeRequest();

        // 设置参数
        smnRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2")
                .setName("access_policy")
                .setValue("{" +
                        "\"Version\": \"2016-09-07\"," +
                        "\"Id\": \"__default_policy_ID\"," +
                        "\"Statement\": [" +
                        "{" +
                        "\"Sid\": \"__user_pub_0\"," +
                        "\"Effect\": \"Allow\"," +
                        "\"Principal\": {" +
                        "\"CSP\": [" +
                        "\"urn:csp:iam::1040774eae344b78b14f2939863d4ede:root\"" +
                        "]" +
                        "}," +
                        "\"Action\": [\"SMN:Publish\",\"SMN:QueryTopicDetail\"]," +
                        "\"Resource\": \"urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2\"" +
                        "}" +
                        "]" +
                        "}");

        // 发送短信
        try {
            UpdateTopicAttributeResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询topics attribute列表
     *
     * @param smnClient
     */
    public static void listTopicAttributes(SmnClient smnClient) {
        // 构造请求对象
        ListTopicAttributesRequest smnRequest = new ListTopicAttributesRequest();

        // 设置参数
        smnRequest.setName("access_policy")
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2");
        // 发送短信
        try {
            ListTopicAttributesResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 删除指定name的topic attribute
     */
    public static void deleteTopicAttributeByName(SmnClient smnClient) {
        // 构造请求对象
        DeleteTopicAttributeByNameRequest smnRequest = new DeleteTopicAttributeByNameRequest();

        // 设置参数
        smnRequest.setName("access_policy")
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2");
        // 发送短信
        try {
            DeleteTopicAttributeByNameResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 删除所有的topic attribute
     */
    public static void deleteTopicAttributes(SmnClient smnClient) {
        // 构造请求对象
        DeleteTopicAttributesRequest smnRequest = new DeleteTopicAttributesRequest();

        // 设置参数
        smnRequest.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_java2");
        // 发送短信
        try {
            DeleteTopicAttributesResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

}
