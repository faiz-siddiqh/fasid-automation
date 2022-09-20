package com.fasid.greenkart.pages;

import org.openqa.selenium.By;

/**
 * This is Basepage and will be extended by all child module pages
 */
public class GrenkartBasePage {

    public static By greenkartLabel = By.xpath("//span[@class=\"redLogo\"]");
    public static By productSearchBar = By.cssSelector("input.search-keyword");
    public static By searchSubmit = By.cssSelector("button.search-button");
    public static By productName = By.xpath("//h4[@class=\"product-name\"]");


}
