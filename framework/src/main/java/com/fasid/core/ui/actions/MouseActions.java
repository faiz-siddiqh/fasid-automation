package com.fasid.core.ui.actions;

import com.fasid.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static com.fasid.core.ui.actions.BaseActions.performElementAction;
import static com.fasid.core.ui.actions.ElementFinderActions.find;
import static com.fasid.enums.WaitStrategy.VISIBLE;
import static org.apache.logging.log4j.LogManager.getLogger;

public class MouseActions {

    private static final Logger LOGGER = getLogger();

    private MouseActions() {
        //Utility class should be private and should not be instantiated
    }

    /**
     * This method is used to click and Hold a Webelement
     *
     * @param locator The locator of the element
     */
    public static void clickAndHold(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Click And hold on element:{}", locator.getName());
        performElementAction((webDriver, element) -> {
            final var actions = new Actions(webDriver);
            actions
                    .clickAndHold(element)
                    .perform();
        }, locator);

        LOGGER.traceExit();
    }

    /**
     * This method is used to click on a element
     *
     * @param locator - The locator of the element
     */
    public static void clickOn(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Clicking on the element {}:", locator.getName());
        performElementAction(WebElement::click, locator);
        LOGGER.traceExit();
    }

    /**
     * This method is used to double click on a element
     *
     * @param locator - The locator of the element
     */
    public static void doubleClickOn(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Double Clicking on the element {}:", locator.getName());
        performElementAction((webDriver, element) -> {
            final var actions = new Actions(webDriver);
            actions
                    .doubleClick(element)
                    .perform();
        }, locator);
        LOGGER.traceExit();
    }

    /**
     * This method is used to drag and Drop from a source element to a offset element
     *
     * @param source - The source locator  of the element
     * @param offset - The offset locator of the element
     */
    public static void dragAndDrop(final Locator source, final Locator offset) {
        LOGGER.traceEntry();
        LOGGER.info("Deagging and dropping element from {} :to the element {}:", source.getName(), offset.getName());
        performElementAction((webDriver, element) -> {
            final var actions = new Actions(webDriver);
            actions
                    .dragAndDrop(element, find(offset, VISIBLE))
                    .perform();
        }, source);
        LOGGER.traceExit();
    }

    /**
     * This method is used to drag and Drop from a source element to a offset element
     *
     * @param source  - The source locator  of the element
     * @param xOffset - The offset of the element in x-axis
     * @param yOffset - The offset of the element in y-axis
     */
    public static void dragAndDropByOffset(final Locator source, final int xOffset, final int yOffset) {
        LOGGER.traceEntry();
        LOGGER.info("Deagging and dropping element from {} to xoffset{} and yOffset {}:", source.getName(), xOffset, yOffset);
        performElementAction((webDriver, element) -> {
            final var actions = new Actions(webDriver);
            actions
                    .dragAndDropBy(element, xOffset, yOffset)
                    .perform();
        }, source);
        LOGGER.traceExit();
    }

    /**
     * This method is used to hover over a element
     *
     * @param locator - The locator of the element
     */
    public static void hoverOver(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Hovering over element :{}", locator.getName());
        performElementAction((webDriver, element) -> {
            final var actions = new Actions(webDriver);
            actions
                    .moveToElement(element)
                    .pause(Duration.ofSeconds(1))
                    .perform();
        }, locator);

        LOGGER.traceExit();

    }

    /**
     * This method is used to perform a contextual click or right-click on a webelement
     *
     * @param locator - The locator on which the action should be performed on
     */
    public static void rightClickOn(final Locator locator) {
        LOGGER.traceEntry();
        performElementAction((webdriver, element) -> {
            final var actions = new Actions(webdriver);
            actions
                    .contextClick(element)
                    .perform();
        }, locator);

        LOGGER.traceExit();
    }

}
