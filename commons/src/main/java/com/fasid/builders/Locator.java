package com.fasid.builders;


import com.fasid.config.Config;
import groovy.transform.ToString;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Predicate;

@ToString
@Getter
@Builder(builderMethodName = "constructLocator")
public class Locator {

    private By android;
    private int index;
    @NotNull
    private String name;
    private Locator parent;
    private By web;
    private Predicate<WebElement> filter;

    /**
     * Returns loctor based on the platform type or RunType
     *
     * @return Locator
     */
    public By getLocator() {
        switch (Config.getTestRunType()) {
            case MOBILE:
                return this.android;
            default:
                return this.web;

        }
    }

}
