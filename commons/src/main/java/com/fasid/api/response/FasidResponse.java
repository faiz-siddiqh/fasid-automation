package com.fasid.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class FasidResponse {

    private long responseTime;
    private String responseBody;
    private int statusCode;
    private String statusLine;
    private Map<String, String> cookies;
    private Map<String, String> headers;

    @Override
    public String toString() {
        return "Response{"
                + "responseTime=" + responseTime
                + ",responseBody=" + responseBody + '\''
                + ",statusCde=" + statusCode
                + ",statusLine=" + statusLine + '\''
                + ",cookies=" + cookies
                + ",headers=" + headers
                + '}';

    }

}
