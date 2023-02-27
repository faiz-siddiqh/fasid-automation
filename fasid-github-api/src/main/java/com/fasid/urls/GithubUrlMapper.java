package com.fasid.urls;

import com.fasid.config.Config;

public enum GithubUrlMapper {

    GRAPHQL_API("graphql");

    String baseUrl;

    GithubUrlMapper(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrlPath(final String... args) {
        final String result = String.format(this.baseUrl);
        return Config.getBaseUrl() + result;
    }
}
