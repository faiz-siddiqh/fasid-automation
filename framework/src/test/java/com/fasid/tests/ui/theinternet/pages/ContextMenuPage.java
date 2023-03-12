package com.fasid.tests.ui.theinternet.pages;

import org.openqa.selenium.By;
import com.fasid.builders.Locator;

/**
 * Context menu page.
 *
 */
public class ContextMenuPage {
    private static final ContextMenuPage CONTEXT_MENU_PAGE = new ContextMenuPage ();

    /**
     * Context menu page instance.
     *
     * @return Context menu page instance
     */
    public static ContextMenuPage contextMenuPage () {
        return CONTEXT_MENU_PAGE;
    }

    private final Locator hotSpot = Locator.constructLocator ().web (By.id ("hot-spot"))
        .name ("Hot Spot")
        .build ();

    public Locator getHotSpot() {
        return hotSpot;
    }
}
