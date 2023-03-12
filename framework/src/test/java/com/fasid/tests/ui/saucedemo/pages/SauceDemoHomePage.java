package com.fasid.tests.ui.saucedemo.pages;

import com.fasid.builders.Locator;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.By.*;
public class SauceDemoHomePage extends BasePage {

    private static final SauceDemoHomePage SAUCE_DEMO_HOME_PAGE = new SauceDemoHomePage();

    public static SauceDemoHomePage sauceDemoHomePage() {
        return SAUCE_DEMO_HOME_PAGE;
    }

    private final Locator logout = Locator.constructLocator()
            .web(id("logout_sidebar_link"))
            .name("Logout")
            .build();

    private final Locator menuButton = Locator.constructLocator()
            .web(id("react-burger-menu-btn"))
            .name("Menu Button")
            .build();
    private final Locator productParent = Locator.constructLocator()
            .web(cssSelector("div#inventory_container"))
            .name("Product Parent")
            .build();
    private final Locator productDescription = Locator.constructLocator()
            .parent(this.productParent)
            .name("Product description")
            .web(cssSelector("div.inventory_item_desc"))
            .build();
    private final Locator addToCartButton = Locator.constructLocator()
            .parent(this.productParent)
            .name("Add to cart button")
            .web(id("add-to-cart-sauce-labs-backpack"))
            .build();
    private final Locator productPrice = Locator.constructLocator()
            .parent(this.productParent)
            .name("Product price")
            .web(cssSelector("div.inventory_item_price"))
            .build();

    private final Locator productTitle = Locator.constructLocator()
            .parent(this.productParent)
            .name("Product title")
            .web(cssSelector("div.inventory_item_name"))
            .build();
    private final Locator shoppingCart = Locator.constructLocator()
            .web(cssSelector("a.shopping_cart_link"))
            .name("Shopping Cart")
            .build();
    private final Locator shoppingCartCount = Locator.constructLocator()
            .web(cssSelector("span.shopping_cart_badge"))
            .parent(this.shoppingCart)
            .name("Shopping Cart count")
            .build();

    private SauceDemoHomePage() {
        // Avoid initialization
    }

    /**
     * Get product item title locator based on its text.
     *
     * @param productName Product name
     * @return {@link Locator} of product item title
     */
    public Locator productItem(final String productName) {
        return Locator.constructLocator()
                .web(xpath(format(".//*[text()=\"{0}\"]", productName)))
                .name(format("Product [{0}]", productName))
                .build();
    }

    public Locator getLogout() {
        return logout;
    }

    public Locator getMenuButton() {
        return menuButton;
    }

    public Locator getProductParent() {
        return productParent;
    }

    public Locator getProductDescription() {
        return productDescription;
    }

    public Locator getAddToCartButton() {
        return addToCartButton;
    }

    public Locator getProductPrice() {
        return productPrice;
    }

    public Locator getProductTitle() {
        return productTitle;
    }

    public Locator getShoppingCart() {
        return shoppingCart;
    }

    public Locator getShoppingCartCount() {
        return shoppingCartCount;
    }
}
