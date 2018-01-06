package com.smn.http;

import org.apache.http.Header;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    private int httpCode;
    private String content;
    private Map<String, String> headers;


    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Header[] headers) {
        this.headers = new HashMap<String, String>();
        for (Header header : headers) {
            this.headers.put(header.getName(), header.getValue());
        }
    }
}
