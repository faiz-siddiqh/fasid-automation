package com.fasid.enums;

public enum ContentType {

    PLAIN_TEXT("text/plain"),
    FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON("application/json");

    final String contentType;

    ContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return this.contentType;
    }
}
