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
import com.smn.common.MmsFileType;
import com.smn.request.mms.MmsFrame;
import com.smn.request.mms.MmsFrameMessage;
import com.smn.request.mms.MmsPublishRequest;
import com.smn.response.mms.MmsPublishResponse;
import com.smn.response.mms.MmsPublishResult;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * mms demo
 *
 * @author zhangyx
 * @version 2.0.5
 */
public class MmsDemo {
    public static void main(String[] args) throws Exception {
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

        mmsPublish(smnClient);
    }

    /**
     * 发送彩信
     */
    public static void mmsPublish(SmnClient smnClient) throws Exception {
        // 构造请求对象
        MmsPublishRequest smnRequest = new MmsPublishRequest();
        MmsFrame mmsFrame = new MmsFrame();

        // 设置彩信数据，--图片
        MmsFrameMessage imageMessage = new MmsFrameMessage();
        imageMessage.setFileType(MmsFileType.MMS_IAMGE_JPG);
        // 需要将图片文件base64编码
        imageMessage.setContentBase64(encodeBase64File("E:/zhangyx/mms/timg.jpg"));

        // 设置彩信数据，--音频
        MmsFrameMessage voiceMessage = new MmsFrameMessage();
        voiceMessage.setFileType(MmsFileType.MMS_VOICE_MID);
        // 需要将音频文件base64编码
        voiceMessage.setContentBase64(encodeBase64File("E:/zhangyx/mms/town.mid"));

        // mms mesage, 将voiceMessage和imageMessage设置到mmsMessage中
        mmsFrame.setVoice(voiceMessage).setImage(imageMessage).setText("彩信测试123");

        smnRequest.setSignId("3e863eac748**********7f2887c")
                .setEndpoints(Arrays.asList("136*****587", "177*****31", "159*****83"))
                .setTitle("彩信测试")
                .setMmsMessage(Arrays.asList(mmsFrame));

        // 发送彩信
        try {
            MmsPublishResponse res = smnClient.sendRequest(smnRequest);

            System.out.println("httpCode:" + res.getHttpCode()
                    + ", request_id:" + res.getRequestId()
                    + ", errorMessage:" + res.getMessage());
            if (res.isSuccess()) {
                for (MmsPublishResult result : res.getResult()) {
                    StringBuilder sb = new StringBuilder("MmsPublishResult{");
                    sb.append("messageId='").append(result.getMessageId()).append('\'');
                    sb.append(", endpoint='").append(result.getEndpoint()).append('\'');
                    sb.append(", code='").append(result.getCode()).append('\'');
                    sb.append(", message='").append(result.getMessage()).append('\'');
                    sb.append('}');
                    System.out.println(sb.toString());
                }
            }
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
        }
    }

    /**
     * 将文件转换为base64
     *
     * @param path file path
     * @return base64 content
     * @throws Exception base64 fail
     */
    private static String encodeBase64File(String path) throws Exception {
        FileInputStream inputFile = null;
        byte[] buffer;
        try {
            File file = new File(path);
            inputFile = new FileInputStream(file);
            buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
        } catch (Exception e) {
            throw e;
        } finally {
            if (inputFile != null) {
                inputFile.close();
            }
        }

        return new String(Base64.encodeBase64(buffer));
    }
}
