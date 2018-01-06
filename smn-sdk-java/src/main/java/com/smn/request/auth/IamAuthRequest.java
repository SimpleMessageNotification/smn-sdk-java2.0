package com.smn.request.auth;

import com.smn.common.Constants;
import com.smn.config.SmnConfiguration;
import com.smn.http.HttpMethod;
import com.smn.request.AbstractRequest;
import com.smn.response.AbstractResponse;

public class IamAuthRequest extends AbstractRequest<AbstractResponse> {

    private SmnConfiguration smnConfiguration;

    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    public String getUrl() {
        return Constants.HTTPS + Constants.IAM + "."
                + smnConfiguration.getRegionName() + "."
                + Constants.ENDPOINT + Constants.IAM_TOKEN_URI;

    }

    public String getBodyParams() {
        return "{" +
                "    \"auth\": {" +
                "        \"identity\": {" +
                "            \"methods\": [" +
                "                \"password\"" +
                "            ]," +
                "            \"password\": {" +
                "                \"user\": {" +
                "                    \"name\": \"" + smnConfiguration.getUserName() + "\"," +
                "                    \"password\": \"" + smnConfiguration.getPassword() + "\"," +
                "                    \"domain\": {" +
                "                        \"name\": \"" + smnConfiguration.getDomainName() + "\"" +
                "                    }" +
                "                }" +
                "            }" +
                "        }," +
                "        \"scope\": {" +
                "            \"project\": {" +
                "                \"name\": \"" + smnConfiguration.getRegionName() + "\"" +
                "            }" +
                "        }" +
                "    }" +
                "}";
    }

    public void setSmnConfiguration(SmnConfiguration smnConfiguration) {
        this.smnConfiguration = smnConfiguration;
    }
}
