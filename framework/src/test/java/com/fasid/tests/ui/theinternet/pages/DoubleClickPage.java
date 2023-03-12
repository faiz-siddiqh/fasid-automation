package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;

/**
 * Double click page.
 */
public class DoubleClickPage {
    private static final DoubleClickPage DOUBLE_CLICK_PAGE = new DoubleClickPage();

    /**
     * Double click page instance.
     *
     * @return Double click menu
     */
    public static DoubleClickPage doubleClickPage() {
        return DOUBLE_CLICK_PAGE;
    }

    private final Locator clickHold = Locator.constructLocator().web(id("click-box"))
            .name("Click Hold")
            .build();
    private final Locator doubleClick = Locator.constructLocator().web(id("double-click"))
            .name("Double Click")
            .build();
    private final Locator hoverButton = Locator.constructLocator().web(className("dropbtn"))
            .name("Hover Button")
            .build();
    private final Locator hoverMenu = Locator.constructLocator().web(className("dropdown-content"))
            .name("Hover Menu")
            .build();

    public Locator getClickHold() {
        return clickHold;
    }

    public Locator getDoubleClick() {
        return doubleClick;
    }

    public Locator getHoverButton() {
        return hoverButton;
    }

    public Locator getHoverMenu() {
        return hoverMenu;
    }
}
