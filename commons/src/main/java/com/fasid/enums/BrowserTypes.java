package com.fasid.enums;

public enum BrowserTypes {

    CHROME("Chrome"),

    FIREFOX("Firefox");

    private final String browserType;

    BrowserTypes(final String browser) {
        this.browserType = browser;
    }

    public String getName() {
        return this.browserType;
    }
}
