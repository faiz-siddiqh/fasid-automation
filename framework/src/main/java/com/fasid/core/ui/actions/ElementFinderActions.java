package com.fasid.core.ui.actions;

import java.util.List;

import com.fasid.builders.Locator;
import com.fasid.config.Config;
import com.fasid.core.ui.BrowserInit;
import com.fasid.core.ui.exception.FrameworkError;
import com.fasid.core.ui.waits.Waits;
import com.fasid.enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.fasid.core.ui.utils.ErrorUtils.handleAndThrow;
import static com.fasid.core.ui.utils.ErrorUtils.throwError;
import static com.fasid.enums.Message.ELEMENT_NOT_FOUND;
import static java.text.MessageFormat.format;
import static org.apache.logging.log4j.LogManager.getLogger;

public final class ElementFinderActions {
    private static Logger LOGGER = getLogger();

    private ElementFinderActions() {
        //Utility Class should be private
    }

    /**
     * Find an WebElement using Locator and wait strategy
     *
     * @param locator      - Custom Locator
     * @param waitStrategy - WaitStrategy to be applied
     * @return WebElement
     */
    public static WebElement find(final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry();
        final var elements = findElements(locator, waitStrategy);
        if (elements.isEmpty()) {
            throwError(ELEMENT_NOT_FOUND, locator.getLocator(), Config.getTestRunType());
        }

        if (locator.getFilter() != null) {
            return elements
                    .stream()
                    .filter(locator.getFilter())
                    .findFirst()
                    .orElseThrow(() -> new FrameworkError(format(ELEMENT_NOT_FOUND.getMessageText(), locator.getName(), Config.getTestRunType())));
        }

        return elements.get(locator.getIndex());
    }

    /**
     * This method is used to findElements using Locator and WaitStrategy
     *
     * @param locator      Locator of the WebElement
     * @param waitStrategy WaitStrategy to be applied
     * @return List Of all the WebElements
     */
    public static List<WebElement> findElements(final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry();
        final var driver = BrowserInit.getDriver();
        final List<WebElement> elements;
        if (locator.getParent() != null) {
            final var parent = find(locator.getParent(), waitStrategy);
            elements = findElements(driver, parent, locator);
        } else {
            waitForElement(locator, waitStrategy);
            elements = findElements(driver, locator);
        }

        return elements;
    }

    /**
     * This method is used to  find the list of the WebElements by handling parent locator
     *
     * @param driver  - The Webdriver Instance of current session
     * @param locator - The locator of the element
     * @param <D>     -The generic type of Driver
     * @return List of WebElements
     */
    private static <D extends WebDriver> List<WebElement> findElements(final D driver, final Locator locator) {
        LOGGER.traceEntry();

        return LOGGER.traceExit(findElements(driver, null, locator));
    }

    /**
     * This method is used to  find the list of the WebElements by handling parent locator
     *
     * @param driver        - The Webdriver Instance of current session
     * @param parentElement - The parent element of the webelement if any
     * @param locator       - The locator of the element
     * @param <D>           -The generic type of Driver
     * @return List of WebElements
     */
    private static <D extends WebDriver> List<WebElement> findElements(D driver, final WebElement parentElement, Locator locator) {
        LOGGER.traceEntry();
        final var platformLocator = locator.getLocator();
        if (platformLocator == null) {
            throwError(ELEMENT_NOT_FOUND, locator.getName(), Config.getTestRunType());
        }

        return (parentElement != null) ? parentElement.findElements(locator.getLocator()) : driver.findElements(locator.getLocator());
    }

    /**
     * This method is used to apply waits based on wait strategy
     *
     * @param locator      - the locator of the element
     * @param waitStrategy - The wait strategy to be applied for waits
     */
    private static void waitForElement(final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry();
        try {

            final var waits = new Waits();
            switch (waitStrategy) {
                case VISIBLE:
                    waits
                            .forVisibilityOfElementLocatedBy(locator.getLocator());
                case CLICKABLE:
                default:
                    waits
                            .forElementToBeClickable(locator.getLocator());

            }
        } catch (final TimeoutException e) {
            e.printStackTrace();
        }

    }


}
