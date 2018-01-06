package com.smn.client;

import com.smn.request.AbstractRequest;
import com.smn.request.IHttpRequest;
import com.smn.response.AbstractResponse;

public interface SmnClient {

    <T extends AbstractResponse> T sendRequest(AbstractRequest<T> request);
}
