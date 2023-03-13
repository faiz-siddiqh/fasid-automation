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
 * Login page object for Sauce demo application.
 */
//@Getter
public class SauceDemoLoginPage  extends BasePage {
    private static final SauceDemoLoginPage LOGIN_PAGE = new SauceDemoLoginPage();

    /**
     * Login page object.
     *
     * @return {@link SauceDemoLoginPage}
     */
    public static SauceDemoLoginPage sauceDemoLoginPage () {
        return LOGIN_PAGE;
    }

    private final Locator loginButton = Locator.constructLocator().web (id ("login-button"))
        .name ("Login Button")
        .build ();
    private final Locator password    = Locator.constructLocator().web (id ("password"))
        .name ("Password")
        .build ();
    private final Locator username    = Locator.constructLocator ().web (id ("user-name"))
        .name ("User Name")
        .build ();

    public Locator getLoginButton() {
        return loginButton;
    }

    public Locator getPassword() {
        return password;
    }

    public Locator getUsername() {
        return username;
    }

    private SauceDemoLoginPage() {
        // Avoid explicit class initialisation.
    }
}
