package com.fasid.tests.ui.saucedemo.pages;

import static org.openqa.selenium.By.cssSelector;

import com.fasid.builders.Locator;
import lombok.Getter;

/**
 * Product details page.
 */
//@Getter
public class ProductDetailsPage extends BasePage{
    private static final ProductDetailsPage PRODUCT_DETAILS_PAGE = new ProductDetailsPage ();

    /**
     * Gets the product details page instance.
     *
     * @return {@link ProductDetailsPage} instance
     */
    public static ProductDetailsPage productDetailsPage () {
        return PRODUCT_DETAILS_PAGE;
    }

    private final Locator container = Locator.constructLocator ().web (
                    cssSelector ("div#inventory_item_container div.inventory_details"))
            .name ("Container")
            .build ();

    public Locator getContainer() {
        return container;
    }

    private ProductDetailsPage () {
        // Avoid explicit class initialisation.
    }
}

