package com.fasid.tests.ui.theinternet.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.id;

/**
 * DropDown page.
 */
public class DropDownPage {
    private static final DropDownPage DROP_DOWN_PAGE = new DropDownPage ();

    /**
     * Drag and drop page instance.
     *
     * @return {@link DropDownPage}
     */
    public static DropDownPage dropDownPage () {
        return DROP_DOWN_PAGE;
    }

    private final Locator fruits      = Locator.constructLocator ().web (id ("fruits"))
        .name ("Fruits")
        .build ();
    private final Locator superHeroes = Locator.constructLocator ().web (id ("superheros"))
        .name ("Super Heroes")
        .build ();

    public Locator getFruits() {
        return fruits;
    }

    public Locator getSuperHeroes() {
        return superHeroes;
    }
}
