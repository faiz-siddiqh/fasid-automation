package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.tagName;

/**
 * Multi window page.
 *
 */
public class MultiWindowPage {
    /**
     * Multi window page instance.
     *
     * @return {@link MultiWindowPage}
     */
    public static MultiWindowPage multiWindowPage() {
        return new MultiWindowPage();
    }

    private final Locator clickHere = Locator.constructLocator().web(linkText("Click Here"))
            .name("Click Here")
            .build();
    private final Locator title = Locator.constructLocator().web(tagName("h3"))
            .name("Title")
            .build();

    public Locator getClickHere() {
        return clickHere;
    }

    public Locator getTitle() {
        return title;
    }
}
