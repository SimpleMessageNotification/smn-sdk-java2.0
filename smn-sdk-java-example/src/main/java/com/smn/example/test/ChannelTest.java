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

package com.smn.example.test;

import com.smn.client.DefaultSmnClient;
import com.smn.client.SmnClient;
import com.smn.request.sms.SmsPublishRequest;
import com.smn.response.sms.SmsPublishResponse;
import com.smn.util.StringUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChannelTest {

    private final static String SMS_TEMPLATE = "亲爱的用户，您的验证码为：%s（90秒内有效），为了保证账户安全，请勿向任何人提供。%s";

    public static void main(String[] args) {
        SmnClient smnClient = new DefaultSmnClient(
                "YourAccountUserName",
                "YourAccountDomainName",
                "YourAccountPassword",
                "YourRegionName");

        List<String> phoneList = readPhones();
        int index =0;
        for (String phone : phoneList) {

            String content = String.format(SMS_TEMPLATE, getRandom(), getCurrenTime());
            smsPublish(smnClient, phone, content);
            System.out.println("index:" + index + ",start to push sms, phone:" + phone + ", content" + content);
            index ++;
        }
    }

    /**
     * 发送通知验证码类短信
     */
    public static void smsPublish(SmnClient smnClient, String phone, String content) {

        // 构造请求对象
        SmsPublishRequest smnRequest = new SmsPublishRequest();

        // 设置参数
        smnRequest.setEndpoint(phone)
                .setMessage(content)
                .setSignId("1d04631a00c84c24be917c3cfdc23575");

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

    private static String getCurrenTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        return sdf.format(date);
    }

    private static String getRandom() {
        return (int) ((Math.random() * 9 + 1) * 100000) + "";
    }

    private static List<String> readPhones() {
        List<String> phoneList = new ArrayList<String>();
        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = new FileInputStream("E:\\zhangyx\\telphone.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                if (StringUtil.isBlank(str)) continue;
                int index = str.indexOf("：");
                String phoneSubStr = str.substring(index + 1).trim();
                String[] phones = phoneSubStr.split("\\s+");
                if (phones.length == 0) continue;
                for (String phone : phones) {
                    phone = phone.trim();
                    if (StringUtil.isBlank(phone)) {
                        continue;
                    }
                    phoneList.add(phone);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {

                    inputStream.close();

                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return phoneList;
    }
}
