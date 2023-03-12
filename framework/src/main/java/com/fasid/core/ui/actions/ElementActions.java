package com.fasid.core.ui.actions;

import com.fasid.assertions.Asserts;
import com.fasid.builders.Locator;
import com.fasid.core.ui.waits.Waits;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;
import java.util.stream.IntStream;

import static com.fasid.core.ui.actions.BaseActions.getElementAttribute;
import static com.fasid.core.ui.actions.BaseActions.performElementAction;
import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.DriverActions.pause;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

public class ElementActions {

    private static Logger LOGGER = getLogger();

    private ElementActions() {
        //Utils class should not be instantiated
    }

    /**
     * This method is used to clear the webelement text field
     *
     * @param locator - The locator of the element
     */
    public static void clear(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Clearing the element located by {}", locator.getName());
        performElementAction(WebElement::clear, locator);
        LOGGER.traceExit();
    }

    /**
     * This method is used to clear the webelement text field
     *
     * @param locator - The locator of the element
     */
    public static void clearUsingKeys(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Clearing the element located by {} using keys", locator.getName());
        performElementAction(element -> {
            IntStream.range(0, element.getAttribute("value").length())
                    .forEach(i -> element.sendKeys(Keys.BACK_SPACE));
        }, locator);
        LOGGER.traceExit();
    }

    /**
     * This method is used to get the attribute value from the element
     *
     * @param locator   - The locator of the element
     * @param attribute - The attribute to be returned
     * @return The value of the attribute
     */
    public static String getAttributeOf(final Locator locator, final String attribute) {
        LOGGER.traceEntry();
        LOGGER.info("Getting attribute :{} of element located by :", locator.getName());
        LOGGER.traceExit();
        return getElementAttribute(element -> element.getAttribute(attribute), locator, EMPTY);
    }

    /**
     * This method is used to check if a element is displayed
     *
     * @param locator - The loctor of the element to be checked
     * @return True if displayed else false
     */
    public static boolean isDisplayed(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Checking if the element is displayed with locator :", locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::isDisplayed, locator, false));
    }

    /**
     * Gets the value if the element is enabled.
     *
     * @param locator locator of the element
     * @return true if the element is enabled, false otherwise
     */
    public static boolean isEnabled(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Checking if element located by: {} is enabled", locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::isEnabled, locator, false));
    }

    /**
     * Gets the value if the element is selected.
     *
     * @param locator locator of the element
     * @return true if the element is selected, false otherwise
     */
    public static boolean isSelected(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Checking if element located by: {} is selected", locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::isSelected, locator, false));
    }

    /**
     * Submit the element.
     *
     * @param locator {@link Locator} of element
     */
    public static void submit(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Submitting element located by: {}", locator.getName());
        performElementAction(WebElement::submit, locator);
        LOGGER.traceExit();
    }

    /**
     * Gets the text of the element.
     *
     * @param locator locator of the element
     * @return text of the element
     */
    public static String textOf(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Getting text of element located by: {}", locator.getName());
        return LOGGER.traceExit(getElementAttribute(WebElement::getText, locator, EMPTY));
    }

    /**
     * This method is used to click and type and wait on the element
     *
     * @param locator    - The locator of the element
     * @param keysToSend - The value to be type on the field
     */
    public static void clickAndTypeAndWait(final Locator locator, final String keysToSend) {
        LOGGER.traceEntry();
        performElementAction((webDriver, element) -> {
            clickOn(locator);
            element.sendKeys(keysToSend);
            pause(Duration.ofSeconds(1));
        }, locator);

        LOGGER.traceExit();
    }

    /**
     * This method is used to click and clear the field and type and wait on the element
     *
     * @param locator    - The locator of the element
     * @param keysToSend - The value to be type on the field
     */
    public static void clearAndTypeAndWait(final Locator locator, final String keysToSend) {
        LOGGER.traceEntry();
        performElementAction((webDriver, element) -> {
            clickOn(locator);
            clear(locator);
            element.sendKeys(keysToSend);
            pause(Duration.ofSeconds(1));
        }, locator);

        LOGGER.traceExit();
    }


    public static void verifyBrowserUrl(final String expectedURL) {
        final String actualUrl = navigate().getCurrentURL();
        Assert.assertEquals(actualUrl, expectedURL, format("URLs are not equal . actual {0} : expected {1}", actualUrl, expectedURL));
    }

    /**
     * This method checks whether the locator with the text is equal to the expected text
     *
     * @param locator      - The locator of the element
     * @param expectedText - The expectedText to be verified
     */
    public static void verifyTextOf(final Locator locator, final String expectedText) {
        LOGGER.traceEntry();
        String actualText = textOf(locator);
        Asserts
                .assertTrue(actualText.equalsIgnoreCase(expectedText), format("Error !! Texts don't match, actual  :{0} , expected {1} .", actualText, expectedText));

        LOGGER.traceExit();

    }


}
