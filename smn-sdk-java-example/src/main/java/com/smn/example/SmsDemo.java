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
import com.smn.common.SmsCallbackEventType;
import com.smn.request.sms.DeleteSmsSignRequest;
import com.smn.request.sms.GetSmsMessageRequest;
import com.smn.request.sms.ListSmsEventRequest;
import com.smn.request.sms.ListSmsMsgReportRequest;
import com.smn.request.sms.ListSmsSignsRequest;
import com.smn.request.sms.SmsCallback;
import com.smn.request.sms.SmsPublishRequest;
import com.smn.request.sms.UpdateSmsEventRequest;
import com.smn.response.sms.DeleteSmsSignResponse;
import com.smn.response.sms.GetSmsMessageResponse;
import com.smn.response.sms.ListSmsEventResponse;
import com.smn.response.sms.ListSmsMsgReportResponse;
import com.smn.response.sms.ListSmsSignsResponse;
import com.smn.response.sms.SmsCallbackInfo;
import com.smn.response.sms.SmsPublishResponse;
import com.smn.response.sms.SmsSignInfo;
import com.smn.response.sms.UpdateSmsEventResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * sms demo
 *
 * @author zhangyx
 * @version 2.0.0
 */
public class SmsDemo {
    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountPassword",
                "YourAccountDomainName",
                "YourRegionName");

        // 发送短信，send sms
        smsPublish(smnClient);

        // 查询签名 list sms signs
        listSmsSigns(smnClient);

        // delete sms sign
        deleteSmsSign(smnClient);

        //list sms msg report
        listSmsMsgReport(smnClient);

        //get sms message content
        getSmsMessage(smnClient);

        //ListSmsEvent
        listSmsEvent(smnClient);

        // update sms event
        updateSmsEvent(smnClient);
    }

    /**
     * 发送短信
     */
    public static void smsPublish(SmnClient smnClient) {

        // 构造请求对象
        SmsPublishRequest smnRequest = new SmsPublishRequest();

        // 设置参数
        smnRequest.setEndpoint("+861368***587")
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

    /**
     * 查询短信签名
     */
    public static void listSmsSigns(SmnClient smnClient) {

        // 构造请求对象
        ListSmsSignsRequest smnRequest = new ListSmsSignsRequest();

        // 发送短信
        try {
            ListSmsSignsResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ",smsSignCount:" + res.getSmsSignCount() +
                    ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());

            List<SmsSignInfo> infos = res.getSmsSigns();
            for(SmsSignInfo info : infos) {
                System.out.println("SmsSignInfo{" +
                        "signName='" + info.getSignName() + '\'' +
                        ", createTime='" + info.getCreateTime() + '\'' +
                        ", signId='" + info.getSignId() + '\'' +
                        ", reply='" + info.getReply() + '\'' +
                        ", status=" + info.getStatus() +
                        '}');
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 删除短信签名
     */
    public static void deleteSmsSign(SmnClient smnClient) {
        // 构造请求对象
        DeleteSmsSignRequest smnRequest = new DeleteSmsSignRequest();

        //设置参数
        smnRequest.setSignId("f492dc25626641a6bcc3d0bcb3fde280");

        // 发送短信
        try {
            DeleteSmsSignResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询短信的发送状态
     */
    public static void listSmsMsgReport(SmnClient smnClient) {
        // 构造请求对象
        ListSmsMsgReportRequest smnRequest = new ListSmsMsgReportRequest();

        //设置参数
        smnRequest.setStartTime("1515154713850")
                .setEndTime("1515327513000")
                .setLimit(10)
                .setMobile("13688807597");

        // 发送短信
        try {
            ListSmsMsgReportResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询已发送短信的内容
     *
     * @param smnClient
     */
    public static void getSmsMessage(SmnClient smnClient) {
        // 构造请求对象
        GetSmsMessageRequest smnRequest = new GetSmsMessageRequest();

        //设置参数
        smnRequest.setMessageId("58222f25aa974c1887c1e08c2f412806");

        // 发送短信
        try {
            GetSmsMessageResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", message" + res.getMessage()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 查询短信回调事件
     */
    public static void listSmsEvent(SmnClient smnClient) {
        // 构造请求对象
        ListSmsEventRequest smnRequest = new ListSmsEventRequest();

        //设置参数
        //smnRequest.setEventType("sms_reply_event");

        // 发送短信
        try {
            ListSmsEventResponse res = smnClient.sendRequest(smnRequest);
            List<SmsCallbackInfo> infos = res.getCallback();
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", message" + res.getMessage()
                    + ", errormessage:" + res.getMessage());

            for (SmsCallbackInfo info : infos) {
                System.out.println("{" +
                        "eventType='" + info.getEventType() + '\'' +
                        ", topicUrn='" + info.getTopicUrn() + '\'' +
                        '}');
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 更新短信回调事件
     */
    public static void updateSmsEvent(SmnClient smnClient) {
        // 构造请求对象
        UpdateSmsEventRequest smnRequest = new UpdateSmsEventRequest();

        //设置参数, 添加回调时间参数
        List<SmsCallback> callbacks = new ArrayList<SmsCallback>();
        SmsCallback smsCallback = new SmsCallback();
        smsCallback.setEventType(SmsCallbackEventType.SMS_CALLBACK_SUCCESS);
        smsCallback.setTopicUrn("urn:smn:cn-north-1:cffe4fc4c9a54219b60dbaf7b586e132:topic_test_v1");

        callbacks.add(smsCallback);
        smnRequest.setCallbacks(callbacks);

        // 发送短信
        try {
            UpdateSmsEventResponse res = smnClient.sendRequest(smnRequest);
            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errormessage:" + res.getMessage());
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }
}
