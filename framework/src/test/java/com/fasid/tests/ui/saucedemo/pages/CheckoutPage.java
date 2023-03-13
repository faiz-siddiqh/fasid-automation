/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.fasid.tests.ui.saucedemo.pages;

import com.fasid.builders.Locator;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

/**
 * Checkout page.
 */
public class CheckoutPage {
    private static final CheckoutPage CHECKOUT_PAGE = new CheckoutPage();

    /**
     * Gets checkout page instance.
     *
     * @return Checkout page instance.
     */
    public static CheckoutPage checkoutPage() {
        return CHECKOUT_PAGE;
    }

    private final Locator completeHeader = Locator.constructLocator().web(cssSelector("h2.complete-header"))
            .name("Complete Header")
            .build();
    private final Locator completeText = Locator.constructLocator().web(cssSelector("div.complete-text"))
            .name("Complete Text")
            .build();
    private final Locator continueButton = Locator.constructLocator().web(id("continue"))
            .name("Continue Button")
            .build();
    private final Locator finish = Locator.constructLocator().web(id("finish"))
            .name("Finish")
            .build();
    private final Locator firstName = Locator.constructLocator().web(id("first-name"))
            .name("First Name")
            .build();
    private final Locator lastName = Locator.constructLocator().web(id("last-name"))
            .name("Last Name")
            .build();
    private final Locator title = Locator.constructLocator().web(cssSelector("span.title"))
            .name("Title")
            .build();
    private final Locator zipCode = Locator.constructLocator().web(id("postal-code"))
            .name("Zip Code")
            .build();

    public Locator getCompleteHeader() {
        return completeHeader;
    }

    public Locator getCompleteText() {
        return completeText;
    }

    public Locator getContinueButton() {
        return continueButton;
    }

    public Locator getFinish() {
        return finish;
    }

    public Locator getFirstName() {
        return firstName;
    }

    public Locator getLastName() {
        return lastName;
    }

    public Locator getTitle() {
        return title;
    }

    public Locator getZipCode() {
        return zipCode;
    }

    private CheckoutPage() {
        // Avoid explicit page initialization.
    }
}
