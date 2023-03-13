package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.cssSelector;

/**
 * Checkbox page.
 **/
public class CheckboxPage {
    private static final CheckboxPage CHECKBOX_PAGE = new CheckboxPage();

    /**
     * Checkbox page instance.
     *
     * @return {@link CheckboxPage}
     */
    public static CheckboxPage checkboxPage() {
        return CHECKBOX_PAGE;
    }

    private final Locator option1 = Locator.constructLocator().web(cssSelector("form#checkboxes input"))
            .name("Option 1")
            .build();
    private final Locator option2 = Locator.constructLocator().web(cssSelector("form#checkboxes input"))
            .name("Option 2")
            .index(1)
            .build();

    public Locator getOption1() {
        return option1;
    }

    public Locator getOption2() {
        return option2;
    }
}
