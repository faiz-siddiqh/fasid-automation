package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

/**
 * Alert page object.
 */
public class AlertPage {
    private static final AlertPage ALERT_PAGE = new AlertPage();

    /**
     * Alert page instance.
     *
     * @return {@link AlertPage}
     */
    public static AlertPage alertPage() {
        return ALERT_PAGE;
    }

    private final Locator alertButton = Locator.constructLocator().web(cssSelector("ul > li > button"))
            .name("Alert button")
            .index(0)
            .build();
    private final Locator confirmButton = Locator.constructLocator().web(cssSelector("ul > li > button"))
            .name("Confirm Button")
            .index(1)
            .build();
    private final Locator promptButton = Locator.constructLocator().web(cssSelector("ul > li > button"))
            .name("Prompt Button")
            .index(2)
            .build();
    private final Locator result = Locator.constructLocator().web(id("result"))
            .name("Result")
            .build();

    public Locator getAlertButton() {
        return alertButton;
    }

    public Locator getConfirmButton() {
        return confirmButton;
    }

    public Locator getPromptButton() {
        return promptButton;
    }

    public Locator getResult() {
        return result;
    }
}
