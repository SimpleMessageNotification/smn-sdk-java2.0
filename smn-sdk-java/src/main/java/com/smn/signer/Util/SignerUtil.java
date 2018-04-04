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

package com.smn.signer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class SignerUtil {
    public static final Charset UTF8 = Charset.forName("UTF-8");
    private static final SimpleDateFormat dateForamte1 = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat dateForamte2 = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
    public static final Pattern ENCODED_CHARACTERS_PATTERN;

    static {
        StringBuilder pattern = new StringBuilder();
        pattern.append(Pattern.quote("+")).append("|").append(Pattern.quote("*")).append("|").append(Pattern.quote("%7E")).append("|").append(Pattern.quote("%2F"));
        ENCODED_CHARACTERS_PATTERN = Pattern.compile(pattern.toString());
    }

    public static String formatDateStamp(long timeMilli) {
        dateForamte1.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateForamte1.format(timeMilli);
    }

    public static String formatTimestamp(long timeMilli) {
        dateForamte2.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateForamte2.format(timeMilli);
    }

    public static long numberOfDaysSinceEpoch(long milliSinceEpoch) {
        return TimeUnit.MILLISECONDS.toDays(milliSinceEpoch);
    }

    public static boolean isUsingNonDefaultPort(URI uri) {
        String scheme = uri.getScheme().toLowerCase();
        int port = uri.getPort();
        if (port <= 0) {
            return false;
        } else if (scheme.equals("http") && port == 80) {
            return false;
        } else {
            return !scheme.equals("https") || port != 443;
        }
    }

    /**
     * sha-256
     *
     * @param text
     * @return
     */
    public static byte[] hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes(UTF8));
            return md.digest();
        } catch (Exception e) {
            throw new RuntimeException("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    /**
     * sha-256
     *
     * @param input
     * @return
     */
    public static byte[] hash(InputStream input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            DigestInputStream digestInputStream = new SdkDigestInputStream(input, md);
            byte[] buffer = new byte[1024];

            while (digestInputStream.read(buffer) > -1) {

            }

            return digestInputStream.getMessageDigest().digest();
        } catch (Exception e) {
            throw new RuntimeException("Unable to compute hash while signing request: " + e.getMessage(), e);
        }
    }

    /**
     * mac
     *
     * @param stringData
     * @param key
     * @param algorithm
     * @return
     */
    public static byte[] sign(String stringData, byte[] key, SigningAlgorithm algorithm) {
        try {
            byte[] data = stringData.getBytes(SignerUtil.UTF8);
            return sign(data, key, algorithm);
        } catch (Exception var5) {
            throw new RuntimeException("Unable to calculate a request signature: " + var5.getMessage(), var5);
        }
    }

    /**
     * mac
     *
     * @param data
     * @param key
     * @param algorithm
     * @return
     */
    public static byte[] sign(byte[] data, byte[] key, SigningAlgorithm algorithm) {
        try {
            Mac mac = Mac.getInstance(algorithm.toString());
            mac.init(new SecretKeySpec(key, algorithm.toString()));
            return mac.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Unable to calculate a request signature: " + e.getMessage(), e);
        }
    }
}
