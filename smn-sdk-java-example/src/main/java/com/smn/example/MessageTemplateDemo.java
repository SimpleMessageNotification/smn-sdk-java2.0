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
import com.smn.request.template.CreateMessageTemplateRequest;
import com.smn.request.template.DeleteMessageTemplateRequest;
import com.smn.request.template.ListMessageTemplatesRequest;
import com.smn.request.template.QueryMessageTemplateDetailRequest;
import com.smn.request.template.UpdateMessageTemplateRequest;
import com.smn.response.template.CreateMessageTemplateResponse;
import com.smn.response.template.DeleteMessageTemplateResponse;
import com.smn.response.template.ListMessageTemplatesResponse;
import com.smn.response.template.MessageTemplateListInfo;
import com.smn.response.template.QueryMessageTemplateDetailResponse;
import com.smn.response.template.UpdateMessageTemplateResponse;

import java.util.List;

/**
 * message template demo
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class MessageTemplateDemo {

    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        // create message template
        createMessageTemplate(smnClient);

        //update message template
        updateMessageTemplate(smnClient);

        //list message templates
        listMessageTemplates(smnClient);

        //query message template detail
        queryMessageTemplateDetail(smnClient);

        //delete message template
        deleteMessageTemplate(smnClient);
    }

    /**
     * 创建消息模板
     *
     * @param smnClient
     */
    public static void createMessageTemplate(SmnClient smnClient) {
        // 构造请求对象
        CreateMessageTemplateRequest smnRequest = new CreateMessageTemplateRequest();

        // 设置参数
        smnRequest.setContent("create message template, {name}")
                .setMessageTemplateName("CreateMessageDemo_zhangyx_java2")
                .setProtocol("sms");

        // 发送请求
        try {
            CreateMessageTemplateResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", messageTemplateId:" + res.getMessageTemplateId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 更新消息模板
     *
     * @param smnClient
     */
    public static void updateMessageTemplate(SmnClient smnClient) {
        // 构造请求对象
        UpdateMessageTemplateRequest smnRequest = new UpdateMessageTemplateRequest();

        // 设置参数
        smnRequest.setContent("update message template sdss, {name}, {jel}")
                .setMessageTemplateId("a03d5172ff7d454bbaf8661fb93de975");

        // 发送请求
        try {
            UpdateMessageTemplateResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询消息模板列表
     *
     * @param smnClient
     */
    public static void listMessageTemplates(SmnClient smnClient) {
        // 构造请求对象
        ListMessageTemplatesRequest smnRequest = new ListMessageTemplatesRequest();

        // 设置参数
        smnRequest.setOffset(0)
                .setLimit(100);

        // 发送请求
        try {
            ListMessageTemplatesResponse res = smnClient.sendRequest(smnRequest);
            List<MessageTemplateListInfo> listInfos = res.getMessageTemplates();
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", message_templates" + res.getMessageTemplates()
                    + ", message_template_count" + res.getMessageTemplateCount()
                    + ", errormessage:" + res.getMessage());

            for (MessageTemplateListInfo info : listInfos) {
                System.out.println("{" +
                        "messageTemplateName='" + info.getMessageTemplateName() + '\'' +
                        ", createTime='" + info.getCreateTime() + '\'' +
                        ", updateTime='" + info.getUpdateTime() + '\'' +
                        ", messageTemplateId='" + info.getMessageTemplateId() + '\'' +
                        ", tagNames=" + info.getTagNames() +
                        ", protocol='" + info.getProtocol() + '\'' +
                        '}');
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询消息模板详情
     *
     * @param smnClient
     */
    public static void queryMessageTemplateDetail(SmnClient smnClient) {
        // 构造请求对象
        QueryMessageTemplateDetailRequest smnRequest = new QueryMessageTemplateDetailRequest();

        // 设置参数
        smnRequest.setMessageTemplateId("a03d5172ff7d454bbaf8661fb93de975");

        // 发送请求
        try {
            QueryMessageTemplateDetailResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", message_template_name:" + res.getMessageTemplateName()
                    + ", create_time:" + res.getCreateTime()
                    + ", update_time:" + res.getUpdateTime()
                    + ", message_template_id:" + res.getMessageTemplateId()
                    + ", tag_names:" + res.getTagNames()
                    + ", content:" + res.getContent()
                    + ", protocol:" + res.getProtocol()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 删除消息模板
     *
     * @param smnClient
     */
    public static void deleteMessageTemplate(SmnClient smnClient) {
        // 构造请求对象
        DeleteMessageTemplateRequest smnRequest = new DeleteMessageTemplateRequest();

        // 设置参数
        smnRequest.setMessageTemplateId("a03d5172ff7d454bbaf8661fb93de975");

        // 发送请求
        try {
            DeleteMessageTemplateResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}
