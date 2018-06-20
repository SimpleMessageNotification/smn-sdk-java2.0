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
package com.smn.util;

import com.smn.common.Constants;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author zhangyx
 * @version 2.0.0
 */
public class ValidationUtil {

    /**
     * validate topic regex
     */
    final static Pattern PATTERN_TOPIC = Pattern.compile("^[a-zA-Z0-9]{1}[-_a-zA-Z0-9]{0,255}$");

    /**
     * validate telephone regex
     */
    final static Pattern PATTERN_TELEPHONE = Pattern.compile("^\\+?[0-9]+$");

    /**
     * validate templateName
     */
    final static Pattern PATTERN_TEMPLATE_NAME = Pattern.compile("^[a-zA-Z0-9]{1}([-_a-zA-Z0-9]){0,64}");

    /**
     * validate sms templateName
     */
    final static Pattern PATTERN_SMS_TEMPLATE_NAME = Pattern.compile("[-a-zA-Z0-9\u4e00-\u9fa5]{0,64}");

    /**
     * validate subjet regex
     */
    final static Pattern PATTERN_SUBJECT = Pattern.compile("^[^\\r\\n\\t\\f]+$");

    /**
     * validate is english
     */
    private static final Pattern PATTERN_EN = Pattern.compile("^\\p{ASCII}+$");

    private final static int MAX_TOPIC_DISPLAY_NAME = 192;
    private final static int MAX_TEMPLATE_MESSAGE_CONTENT_LENGTH = 256 * 1024;
    private final static int SIGN_MAX_LENGTH = 10;
    private final static char SIGNLABEL_CH_LEFT = '【';
    private final static char SIGNLABEL_CH_RIGHT = '】';
    private final static char SIGNLABEL_EN_LEFT = '[';
    private final static char SIGNLABEL_EN_RIGHT = ']';
    public final static int MAX_SMS_BATCH_PUBLISH_SIZE = 1000;

    /**
     * validate topic name if is conformed with specification
     *
     * @param topicName the topicName to validate
     * @return boolean <code>true</code> conform to rule will be true,or false
     */
    public static final boolean validateTopicName(String topicName) {

        if (StringUtil.isEmpty(topicName)) {
            return false;
        }

        return PATTERN_TOPIC.matcher(topicName).matches();
    }

    /**
     * validate telephone number if is conformed with specification
     * <p>
     *
     * @param telephone phone number to be validated
     * @return boolean <code>true</code> conform to rule will be true,or false
     */
    public static final boolean validateTelephone(String telephone) {

        if (StringUtil.isEmpty(telephone)) {
            return false;
        }

        return PATTERN_TELEPHONE.matcher(telephone).matches();
    }

    /**
     * Determine whether the topic meets the naming conventions, and the <code>true</code> indicates compliance with the specification, otherwise it does not conform to specifications
     * <p> need to meet the beginning must be self, numbers, punctuation ASCALL text service, cannot contain newline characters and control </p>
     *
     * @param subject the subject to validate
     * @return boolean  <code>true</code> conform to rule will be true,or false
     */
    public static boolean validateSubject(String subject) {
        if (StringUtil.isEmpty(subject)) {
            return true;
        }
        return PATTERN_SUBJECT.matcher(subject).matches();
    }

    /**
     * validate displayname
     *
     * @param displayName the displayName to validate
     * @return boolean <code>true</code> displayName is valid
     */
    public static boolean validateDisplayName(String displayName) {
        try {
            byte[] b = displayName.getBytes(Constants.DEFAULT_CHARSET);
            return b.length < MAX_TOPIC_DISPLAY_NAME;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("validate display name error");
        }
    }


    /**
     * validate template templateMessageContent
     *
     * @param content the content to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateTemplateMessageContent(String content) {
        if (StringUtil.isBlank(content)) {
            return false;
        }
        try {
            byte[] b = content.getBytes(Constants.DEFAULT_CHARSET);
            return b.length < MAX_TEMPLATE_MESSAGE_CONTENT_LENGTH;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("validate template message content error");
        }
    }

    /**
     * validate template name
     *
     * @param templateName the template name to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateTemplateName(String templateName) {
        if (StringUtil.isBlank(templateName)) {
            return false;
        }
        return PATTERN_TEMPLATE_NAME.matcher(templateName).matches();
    }

    /**
     * validate message structure message length
     *
     * @param structure the structure to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateMessageStructureLength(String structure) {
        if (StringUtil.isEmpty(structure)) {
            return false;
        }

        try {
            byte[] b = structure.getBytes(Constants.DEFAULT_CHARSET);
            return b.length < MAX_TEMPLATE_MESSAGE_CONTENT_LENGTH;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("validate template message content error");
        }
    }

    /**
     * validate structure contains default
     *
     * @param structure
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateMessageStructureDefault(Map<String, Object> structure) {
        if (structure == null || structure.isEmpty()) {
            return false;
        }
        return structure.containsKey(Constants.DEFAULT);
    }

    /**
     * validate offset
     *
     * @param offset the offset to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateOffset(int offset) {
        return offset >= 0;
    }

    /**
     * validate limit
     *
     * @param limit the limit to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateLimit(int limit) {
        return limit > 0 && limit <= 100;
    }

    /**
     * validate sms template name
     *
     * @param templateName the sms template name to validate
     * @return boolean
     * if valid return <code>true</code>, else return <code>false</code>
     */
    public static boolean validateSmsTemplateName(String templateName) {
        if (StringUtil.isBlank(templateName)) {
            return false;
        }
        return PATTERN_SMS_TEMPLATE_NAME.matcher(templateName).matches();
    }

    /**
     * validate message contain sign name
     *
     * @param message sms content
     * @return if match return true, else return false
     */
    public static boolean containSignNameFromMessage(String message) {
        if (StringUtil.isEmpty(message) || message.length() < 4) {
            return false;
        }
        int scanLegth = message.length() > SIGN_MAX_LENGTH ? SIGN_MAX_LENGTH : message.length();

        // scan head
        if (message.charAt(0) == SIGNLABEL_CH_LEFT || message.charAt(0) == SIGNLABEL_EN_LEFT) {
            // 判断是否是中文标签
            boolean isChLabel = message.charAt(0) == SIGNLABEL_CH_LEFT;
            // 右闭合字符
            char signLabelRightChar = isChLabel ? SIGNLABEL_CH_RIGHT : SIGNLABEL_EN_RIGHT;

            for (int i = 1; i < scanLegth; i++) {
                if (message.charAt(i) == signLabelRightChar) {
                    String signName = message.substring(1, i);
                    return (!isChLabel && !PATTERN_EN.matcher(signName).matches()) ? false : true;
                }
            }
        }

        // scan tail
        if (message.charAt(message.length() - 1) == SIGNLABEL_CH_RIGHT
                || message.charAt(message.length() - 1) == SIGNLABEL_EN_RIGHT) {
            // 判断是否是中文标签
            boolean isChLabel = message.charAt(message.length() - 1) == SIGNLABEL_CH_RIGHT;
            // 左闭合字符
            char signLabelLeftChar = isChLabel ? SIGNLABEL_CH_LEFT : SIGNLABEL_EN_LEFT;

            for (int i = message.length() - 2; i > message.length() - scanLegth - 1; i--) {
                if (message.charAt(i) == signLabelLeftChar) {
                    String signName = message.substring(i + 1, message.length() - 1);
                    return (!isChLabel && !PATTERN_EN.matcher(signName).matches()) ? false : true;
                }
            }
        }
        return false;

    }
}
