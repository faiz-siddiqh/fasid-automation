package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.tagName;

/**
 * Drag drop page.
 */

public class DragDropPage {
    private static final DragDropPage DRAG_DROP_PAGE = new DragDropPage ();

    /**
     * Drag and drop page instance.
     *
     * @return {@link DragDropPage}
     */
    public static DragDropPage dragDropPage () {
        return DRAG_DROP_PAGE;
    }

    private final Locator draggable = Locator.constructLocator ().web (id ("draggable"))
        .name ("Draggable")
        .build ();
    private final Locator droppable = Locator.constructLocator ().web (id ("droppable"))
        .name ("Droppable")
        .build ();
    private final Locator header    = Locator.constructLocator ().web (tagName ("b"))
        .name ("Header")
        .parent (this.droppable)
        .build ();

    public Locator getDraggable() {
        return draggable;
    }

    public Locator getDroppable() {
        return droppable;
    }

    public Locator getHeader() {
        return header;
    }
}
