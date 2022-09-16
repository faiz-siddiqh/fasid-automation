package com.fasid.enums;

public enum ContentType {

    APPLICATION_JSON("application/json"),
    ;

    final String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return this.contentType;
    }
}
