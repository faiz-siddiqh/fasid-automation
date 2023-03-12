package com.fasid.tests.ui.saucedemo.actions;

import com.fasid.assertions.Asserts;
import com.fasid.config.Config;

import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.ElementActions.*;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static com.fasid.tests.ui.saucedemo.pages.CartPage.cartPage;
import static com.fasid.tests.ui.saucedemo.pages.CheckoutPage.checkoutPage;
import static com.fasid.tests.ui.saucedemo.pages.ProductDetailsPage.productDetailsPage;
import static com.fasid.tests.ui.saucedemo.pages.SauceDemoHomePage.sauceDemoHomePage;
import static com.fasid.tests.ui.saucedemo.pages.SauceDemoLoginPage.sauceDemoLoginPage;
import static java.text.MessageFormat.format;

public class SauceDemoActions {
    private static final String URL = Config.getBaseUrl();

    public void verifyAddToCart() {

        Asserts
                .assertTrue(isDisplayed(sauceDemoHomePage().getProductTitle()), "Get Product Title is not displayed");
        clickOn(sauceDemoHomePage().getAddToCartButton());
        Asserts
                .assertEquals(textOf(sauceDemoHomePage().getProductPrice()), "$29.99", "Incorrect Product Price displayed");
        Asserts
                .assertEquals(textOf(sauceDemoHomePage().getShoppingCartCount()), "1", "Incorrect Cart Count displayed");

        verifyProductDetailPage();
        verifyProductCartPage();
    }

    public void verifyCheckoutStep1() {
        clickOn(cartPage().getCheckout());

        verifyBrowserUrl(format("{0}/checkout-step-one.html", URL));
        verifyTextOf(checkoutPage().getTitle(), "CHECKOUT: YOUR INFORMATION");

        clickAndTypeAndWait(checkoutPage().getFirstName(), "Fasid");
        clickAndTypeAndWait(checkoutPage().getLastName(), "Automation");
        clickAndTypeAndWait(checkoutPage().getZipCode(), "12345");

        clickOn(checkoutPage().getContinueButton());
        verifyTextOf(checkoutPage().getTitle(), "CHECKOUT: OVERVIEW");
    }

    public void verifyCheckoutStep2() {
        clickOn(checkoutPage().getFinish());

        verifyBrowserUrl(format("{0}/checkout-complete.html", URL));
        verifyTextOf(checkoutPage().getTitle(), "CHECKOUT: COMPLETE!");
        verifyTextOf(checkoutPage().getCompleteHeader(), "THANK YOU FOR YOUR ORDER!");

        verifyTextOf(checkoutPage().getCompleteText(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    public void verifyLogin(final String userName, final String password) {
        verifyNavigateToSite();
        clickAndTypeAndWait(sauceDemoLoginPage().getUsername(), userName);
        clickAndTypeAndWait(sauceDemoLoginPage().getPassword(), password);
        clickOn(sauceDemoLoginPage().getLoginButton());
        verifyLoggedIn();
    }

    public void verifySignOut() {
        clickOn(sauceDemoHomePage().getMenuButton());
        clickOn(sauceDemoHomePage().getLogout());
        isDisplayed(sauceDemoLoginPage().getUsername());
    }

    private void verifyLoggedIn() {
        verifyBrowserUrl(format("{0}/inventory.html", URL));
        navigate().getCurrentURL().equalsIgnoreCase("Swag Labs");

        isDisplayed(sauceDemoHomePage().getMenuButton());
        isDisplayed(sauceDemoHomePage().getMenuButton());
    }

    private void verifyNavigateToSite() {
        navigate().navigateTo(URL);
    }

    private void verifyProductCartPage() {
        clickOn(sauceDemoHomePage().getShoppingCart());
        isDisplayed(cartPage().getCheckout());
    }

    private void verifyProductDetailPage() {
        clickOn(sauceDemoHomePage().productItem("Sauce Labs Backpack"));
        isDisplayed(productDetailsPage().getContainer());
    }


}
