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
import lombok.Getter;
import static org.openqa.selenium.By.id;

/**
 * Cart page.
 *
 */
@Getter
public class CartPage  extends BasePage {
    private static final CartPage CART_PAGE = new CartPage ();

    /**
     * Gets Cart page instance.
     *
     * @return Cart page instance
     */
    public static CartPage cartPage () {
        return CART_PAGE;
    }

    private final Locator checkout = Locator.constructLocator ().web (id ("checkout"))
        .name ("Checkout")
        .build ();

    public Locator getCheckout() {
        return checkout;
    }

    private CartPage() {
        // Avoid explicit class initialisation
    }
}
