package com.fasid.api.request;

import io.restassured.http.ContentType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FasidRequestSpecification {

    private ContentType contentType;

    private String csrfToken;
    private String baseUrl;
    @Getter
    private String content;

    @Getter
    private String filePath;
    @Getter
    private Map<String, String> cookies;
    @Getter
    private Map<String, String> headers;
    @Getter
    private Map<String, Object> queryParams;
    @Getter
    private Object contentObj;
    private boolean followRedirect = true;
    private boolean allowCircularRedirect = true;


    public FasidRequestSpecification setHeaderToNull() {
        this.headers = null;

        return this;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public FasidRequestSpecification setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public FasidRequestSpecification addBaseUrl(String baseUrl) {

        this.baseUrl = baseUrl;
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean shouldAllowRedirect() {
        return followRedirect;
    }

    public FasidRequestSpecification setFollowRedirect(boolean allowRedirection) {
        followRedirect = allowRedirection;
        return this;
    }

    public boolean shouldAllowCircularRedirect() {
        return allowCircularRedirect;
    }

    public FasidRequestSpecification addContent(String content) {

        this.content = content;
        return this;
    }

    public FasidRequestSpecification addContentObj(Object content) {

        this.contentObj = content;
        return this;
    }

    public FasidRequestSpecification setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
        return this;
    }

    public FasidRequestSpecification setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public FasidRequestSpecification build() {
        return this;
    }

    public FasidRequestSpecification addQueryParameter(String key, Object value) {
        if (!Objects.nonNull(queryParams)) {
            queryParams = new HashMap<>();
        }
        this.queryParams.put(key, value);
        return this;
    }

    public FasidRequestSpecification addHeader(String key, String value) {
        if (!Objects.nonNull(headers)) {
            headers = new HashMap<>();
        }
        headers.put(key, value);
        return this;
    }

    public FasidRequestSpecification addCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
        if (headers == null || headers.isEmpty()) {
            headers = new HashMap<>();
        }
        headers.put("X-CSRF-Token", csrfToken);

        return this;
    }


    public FasidRequestSpecification addQueryParameters(Map<String, Object> newQueryParams) {
        if (!Objects.nonNull(queryParams)) {
            this.queryParams = newQueryParams;
            return this;
        }

        //Using Streams to concat two maps
        Map<String, Object> results = Stream.concat(queryParams.entrySet().stream(), newQueryParams.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        queryParams = results;
        return this;
    }
}
