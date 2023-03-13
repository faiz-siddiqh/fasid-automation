package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;

/**
 * Hover mouse page.
 */
public class HoverPage {
    private static final HoverPage HOVER_PAGE = new HoverPage();

    /**
     * Hover mouse page instance.
     *
     * @return {@link HoverPage}
     */
    public static HoverPage hoverPage() {
        return HOVER_PAGE;
    }

    /**
     * User image locator.
     *
     * @param index Image index
     * @return User image locator
     */
    public Locator userImage(final int index) {
        return Locator.constructLocator().web(className("figure"))
                .index(index)
                .name("User Image")
                .build();
    }

    /**
     * Username locator.
     *
     * @param index Image index
     * @return Username locator
     */
    public Locator userName(final int index) {
        return Locator.constructLocator().web(cssSelector("div.figcaption h5"))
                .name("User Name")
                .parent(userImage(index))
                .build();
    }
}
