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

import com.smn.http.HttpMethod;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * default aksk signature tool
 */
public class DefaultSigner {
    protected boolean doubleUrlEncode = true;
    private static final FIFOCache<SignerKey> signerCache = new FIFOCache(300);

    public void sign(String regionName, String serviceName, SignerRequest request, String secretKey, String accessKeyId) {
        SignerParams signerParams = new SignerParams(regionName, serviceName, "SDK-HMAC-SHA256");
        addHostHeader(request);
        addSdkDateHeader(request, signerParams);
        String contentSha256 = this.calculateContentHash(request);
        String canonicalRequest = this.createCanonicalRequest(request, contentSha256);
        String stringToSign = this.createStringToSign(canonicalRequest, signerParams);
        byte[] signingKey = this.deriveSigningKey(signerParams, secretKey);
        byte[] signature = this.computeSignature(stringToSign, signingKey);
        request.addHeader("Authorization", this.buildAuthorizationHeader(request, signature, accessKeyId, signerParams));
    }

    private void addHostHeader(SignerRequest request) {
        StringBuilder hostHeaderBuilder = new StringBuilder(request.getEndpoint().getHost());
        if (SignerUtil.isUsingNonDefaultPort(request.getEndpoint())) {
            hostHeaderBuilder.append(":").append(request.getEndpoint().getPort());
        }

        request.addHeader("Host", hostHeaderBuilder.toString());
    }

    private void addSdkDateHeader(SignerRequest request, SignerParams signerParams) {
        request.addHeader("X-Sdk-Date", signerParams.getFormattedSigningDateTime());
    }

    private String buildAuthorizationHeader(SignerRequest request, byte[] signature, String accessKeyId, SignerParams signerParams) {
        String signingCredentials = accessKeyId + "/" + signerParams.getScope();
        String credential = "Credential=" + signingCredentials;
        String signerHeaders = "SignedHeaders=" + this.getSignedHeadersString(request);
        String signatureHeader = "Signature=" + BinaryUtils.toHex(signature);
        StringBuilder authHeaderBuilder = new StringBuilder();
        authHeaderBuilder.append("SDK-HMAC-SHA256").append(" ").append(credential).append(", ").append(signerHeaders).append(", ").append(signatureHeader);
        return authHeaderBuilder.toString();
    }

    private byte[] computeSignature(String stringToSign, byte[] signingKey) {
        return SignerUtil.sign(stringToSign.getBytes(SignerUtil.UTF8), signingKey, SigningAlgorithm.HmacSHA256);
    }

    private String createStringToSign(String canonicalRequest, SignerParams signerParams) {
        StringBuilder stringToSignBuilder = new StringBuilder();
        stringToSignBuilder.append(signerParams.getSigningAlgorithm()).append("\n")
                .append(signerParams.getFormattedSigningDateTime()).append("\n")
                .append(signerParams.getScope()).append("\n")
                .append(BinaryUtils.toHex(SignerUtil.hash(canonicalRequest)));
        return stringToSignBuilder.toString();
    }

    private final byte[] deriveSigningKey(SignerParams signerRequestParams, String secretKey) {
        String cacheKey = this.computeSigningCacheKeyName(secretKey, signerRequestParams);
        long daysSinceEpochSigningDate = SignerUtil.numberOfDaysSinceEpoch(signerRequestParams.getSigningDateTimeMilli());
        SignerKey signerKey = signerCache.get(cacheKey);
        if (signerKey != null && daysSinceEpochSigningDate == signerKey.getNumberOfDaysSinceEpoch()) {
            return signerKey.getSigningKey();
        } else {
            byte[] signingKey = this.newSigningKey(secretKey, signerRequestParams.getFormattedSigningDate(), signerRequestParams.getRegionName(), signerRequestParams.getServiceName());
            signerCache.add(cacheKey, new SignerKey(daysSinceEpochSigningDate, signingKey));
            return signingKey;
        }
    }

    private byte[] newSigningKey(String secretKey, String dateStamp, String regionName, String serviceName) {
        byte[] kSecret = ("SDK" + secretKey).getBytes(SignerUtil.UTF8);
        byte[] kDate = SignerUtil.sign(dateStamp, kSecret, SigningAlgorithm.HmacSHA256);
        byte[] kRegion = SignerUtil.sign(regionName, kDate, SigningAlgorithm.HmacSHA256);
        byte[] kService = SignerUtil.sign(serviceName, kRegion, SigningAlgorithm.HmacSHA256);
        return SignerUtil.sign("sdk_request", kService, SigningAlgorithm.HmacSHA256);
    }

    private final String computeSigningCacheKeyName(String secretKey, SignerParams signerRequestParams) {
        StringBuilder hashKeyBuilder = new StringBuilder(secretKey);
        return hashKeyBuilder.append("-")
                .append(signerRequestParams.getRegionName())
                .append("-")
                .append(signerRequestParams.getServiceName())
                .toString();
    }

    private String createCanonicalRequest(SignerRequest request, String contentSha256) {
        String path = appendUri(request.getEndpoint().getPath(), request.getResourcePath());
        StringBuilder canonicalRequestBuilder = new StringBuilder();
        canonicalRequestBuilder.append(request.getHttpMethod().toString()).append("\n")
                .append(this.getCanonicalizedResourcePath(path, this.doubleUrlEncode)).append("\n")
                .append(this.getCanonicalizedQueryString(request)).append("\n")
                .append(this.getCanonicalizedHeaderString(request)).append("\n")
                .append(this.getSignedHeadersString(request)).append("\n")
                .append(contentSha256);
        return canonicalRequestBuilder.toString();
    }

    private String appendUri(String baseUri, String path) {
        return appendUri(baseUri, path, false);
    }

    private String appendUri(String baseUri, String path, boolean escapeDoubleSlash) {
        String resultUri = baseUri;
        if (path != null && path.length() > 0) {
            if (path.startsWith("/")) {
                if (baseUri.endsWith("/")) {
                    resultUri = baseUri.substring(0, baseUri.length() - 1);
                }
            } else if (!baseUri.endsWith("/")) {
                resultUri = baseUri + "/";
            }

            String encodedPath = urlEncode(path, true);
            if (escapeDoubleSlash) {
                encodedPath = encodedPath.replace("//", "/%2F");
            }

            resultUri = resultUri + encodedPath;
        } else if (!baseUri.endsWith("/")) {
            resultUri = baseUri + "/";
        }

        return resultUri;
    }

    private String getCanonicalizedHeaderString(SignerRequest request) {
        List<String> sortedHeaders = new ArrayList(request.getHeaders().keySet());
        Collections.sort(sortedHeaders, String.CASE_INSENSITIVE_ORDER);
        Map<String, String> requestHeaders = request.getHeaders();
        StringBuilder buffer = new StringBuilder();

        for (Iterator iterator = sortedHeaders.iterator(); iterator.hasNext(); buffer.append("\n")) {
            String header = (String) iterator.next();
            String key = header.toLowerCase().replaceAll("\\s+", " ");
            String value = requestHeaders.get(header);
            buffer.append(key).append(":");
            if (value != null) {
                buffer.append(value.replaceAll("\\s+", " "));
            }
        }

        return buffer.toString();
    }

    private String getSignedHeadersString(SignerRequest request) {
        List<String> sortedHeaders = new ArrayList(request.getHeaders().keySet());
        Collections.sort(sortedHeaders, String.CASE_INSENSITIVE_ORDER);
        StringBuilder buffer = new StringBuilder();

        String header;
        for (Iterator iterator = sortedHeaders.iterator(); iterator.hasNext(); buffer.append(header.toLowerCase())) {
            header = (String) iterator.next();
            if (buffer.length() > 0) {
                buffer.append(";");
            }
        }

        return buffer.toString();
    }

    private String getCanonicalizedQueryString(SignerRequest request) {
        return usePayloadForQueryParameters(request) ? "" : getCanonicalizedQueryString(request.getParameters());
    }

    private String getCanonicalizedQueryString(Map<String, String> parameters) {
        SortedMap<String, String> sorted = new TreeMap();
        Iterator pairs = parameters.entrySet().iterator();

        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry) pairs.next();
            String key = pair.getKey();
            String value = pair.getValue();
            sorted.put(urlEncode(key, false), urlEncode(value, false));
        }

        StringBuilder builder = new StringBuilder();
        pairs = sorted.entrySet().iterator();

        while (pairs.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry) pairs.next();
            builder.append(pair.getKey());
            builder.append("=");
            builder.append(pair.getValue());
            if (pairs.hasNext()) {
                builder.append("&");
            }
        }

        return builder.toString();
    }

    private String getCanonicalizedResourcePath(String resourcePath, boolean urlEncode) {
        if (resourcePath != null && !resourcePath.isEmpty()) {
            String value = urlEncode ? urlEncode(resourcePath, true) : resourcePath;
            return value.startsWith("/") ? value : "/".concat(value);
        } else {
            return "/";
        }
    }

    private String urlEncode(String value, boolean path) {
        if (value == null) {
            return "";
        } else {
            try {
                String encoded = URLEncoder.encode(value, "UTF-8");
                Matcher matcher = SignerUtil.ENCODED_CHARACTERS_PATTERN.matcher(encoded);

                StringBuffer buffer;
                String replacement;
                for (buffer = new StringBuffer(encoded.length()); matcher.find(); matcher.appendReplacement(buffer, replacement)) {
                    replacement = matcher.group(0);
                    if ("+".equals(replacement)) {
                        replacement = "%20";
                    } else if ("*".equals(replacement)) {
                        replacement = "%2A";
                    } else if ("%7E".equals(replacement)) {
                        replacement = "~";
                    } else if (path && "%2F".equals(replacement)) {
                        replacement = "/";
                    }
                }

                matcher.appendTail(buffer);
                return buffer.toString();
            } catch (UnsupportedEncodingException var6) {
                throw new RuntimeException(var6);
            }
        }
    }

    private String calculateContentHash(SignerRequest request) {
        InputStream payloadStream = this.getBinaryRequestPayloadStream(request);
        payloadStream.mark(request.getReadLimit());
        String contentSha256 = BinaryUtils.toHex(SignerUtil.hash(payloadStream));

        try {
            payloadStream.reset();
        } catch (IOException e) {
            throw new RuntimeException("Unable to reset stream after calculating signature", e);
        }
        return contentSha256;
    }

    private InputStream getBinaryRequestPayloadStream(SignerRequest request) {
        if (usePayloadForQueryParameters(request)) {
            String encodedParameters = encodeParameters(request);
            return encodedParameters == null ? new ByteArrayInputStream(new byte[0]) : new ByteArrayInputStream(encodedParameters.getBytes(SignerUtil.UTF8));
        } else {
            return getBinaryRequestPayloadStreamWithoutQueryParams(request);
        }
    }

    private InputStream getBinaryRequestPayloadStreamWithoutQueryParams(SignerRequest request) {
        try {
            InputStream is = request.getContent();
            if (is == null) {
                return new ByteArrayInputStream(new byte[0]);
            } else if (!is.markSupported()) {
                throw new RuntimeException("Unable to read request payload to sign request.");
            } else {
                return is;
            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to read request payload to sign request: " + e.getMessage(), e);
        }
    }

    private String encodeParameters(SignerRequest request) {
        List<NameValuePair> nameValuePairs = null;
        int size = request.getParameters().size();
        if (size > 0) {
            nameValuePairs = new ArrayList(size);
            List parameters = new ArrayList(request.getParameters().entrySet());
            Collections.sort(parameters, new Comparator() {
                public int compare(Object arg1, Object arg2) {
                    Map.Entry<String, String> obj1 = (Map.Entry) arg1;
                    Map.Entry<String, String> obj2 = (Map.Entry) arg2;
                    return (obj1.getKey()).compareTo(obj2.getKey());
                }
            });
            Iterator iterator = parameters.iterator();

            while (iterator.hasNext()) {
                Map.Entry<String, String> parameter = (Map.Entry) iterator.next();
                nameValuePairs.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue()));
            }
        }

        String encodedParams = null;
        if (nameValuePairs != null) {
            encodedParams = URLEncodedUtils.format(nameValuePairs, "UTF-8");
        }

        return encodedParams;
    }


    private boolean usePayloadForQueryParameters(SignerRequest request) {
        return HttpMethod.POST.equals(request.getHttpMethod()) && (request.getContent() == null);
    }
}



