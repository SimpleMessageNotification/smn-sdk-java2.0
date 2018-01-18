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
import com.smn.request.publish.PublishRequest;
import com.smn.request.publish.PublishWithStructureRequest;
import com.smn.request.publish.PublishWithTemplateRequest;
import com.smn.response.publish.PublishResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * publish demo
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class PublishDemo {
    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountDomainName",
                "YourAccountPassword",
                "YourRegionName");

        // use aksk authentication
//        SmnClient smnClient = new AkskSmnClient(
//                "YourAccessKeyId",
//                "YourSecretAccessKey",
//                "YourRegionName"
//        );

        // publish with text message
        publishWithMessage(smnClient);

        // publish with message structure
        publishWithMessageStructure(smnClient);

        // publish with message template
        publishWithMessageTemplate(smnClient);
    }

    /**
     * 消息发布
     *
     * @param smnClient
     */
    public static void publishWithMessage(SmnClient smnClient) {
        // 构造请求对象
        PublishRequest smnRequest = new PublishRequest();

        // 设置参数
        smnRequest.setSubject("test by zhangyx java2.0")
                .setMessage("test by zhangyx java2.0 hahahah")
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_test_csharp");

        // 发送请求
        try {
            PublishResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode() +
                    ",message_id:" + res.getMessageId() + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 使用消息结构体方式的消息发布
     *
     * @param smnClient
     */
    public static void publishWithMessageStructure(SmnClient smnClient) {
        // 构造请求对象
        PublishWithStructureRequest smnRequest = new PublishWithStructureRequest();

        // 设置参数
        Map<String, Object> messageStructure = new HashMap<String, Object>();
        messageStructure.put("default", "test by zhangyx structure");
        messageStructure.put("email", "test by zhangyx structure email");
        messageStructure.put("sms", "test by zhangyx structure sms");

        smnRequest.setSubject("test by zhangyx java2.0")
                .setMessageStructure(messageStructure)
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_test_csharp");

        // 发送请求
        try {
            PublishResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode() +
                    ",message_id:" + res.getMessageId() + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 使用消息模板方式的消息发布
     *
     * @param smnClient
     */
    public static void publishWithMessageTemplate(SmnClient smnClient) {
        // 构造请求对象
        PublishWithTemplateRequest smnRequest = new PublishWithTemplateRequest();

        // 设置参数
        Map<String, String> tags = new HashMap<String, String>();
        tags.put("year", "2018");
        tags.put("company", "hellokitty");

        smnRequest.setSubject("test by zhangyx java2.0")
                .setTags(tags)
                .setMessageTemplateName("createMessageTemplate")
                .setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:create_by_zhangyx_test_csharp");

        // 发送请求
        try {
            PublishResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode() +
                    ",message_id:" + res.getMessageId() + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}
