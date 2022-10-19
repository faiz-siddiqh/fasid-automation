package com.fasid.urls;

import com.fasid.config.Config;

public enum GithubUrlMapper {

    GRAPHQL_API("graphql");

    String baseUrl;

    GithubUrlMapper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrlPath(String... args) {
        String result = String.format(this.baseUrl);
        return Config.getBaseUrl() + result;
    }
}
