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

        // 设置手机号码
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
