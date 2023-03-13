package com.fasid.core.ui.actions;

import com.fasid.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.fasid.core.ui.actions.BaseActions.*;
import static com.fasid.core.ui.utils.ErrorUtils.throwError;
import static com.fasid.enums.Message.DROPDOWN_DESELECT_ERROR;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

public class DropDownActions {

    private static Logger LOGGER = getLogger();

    private DropDownActions() {
        //Utility class should not be instantiated
    }

    /**
     * This method selects the option in a dropdown by its visible text
     *
     * @param locator - The link of the locator of the dropdown
     * @param text    - The visible text of the option to be selected
     */
    public static void selectByText(final Locator locator, final String text) {
        LOGGER.traceEntry();
        LOGGER.info("Selecting element located by :{} by text {}", locator.getName(), text);
        performElementAction(element -> {
            final var select = new Select(element);
            select.selectByVisibleText(text);
        }, locator);

        LOGGER.traceExit();
    }

    /**
     * This method selects the value in the dropdown by the value
     *
     * @param locator - The locator of the dropdown
     * @param value   - The value of the option to select
     */
    public static void selectByValue(final Locator locator, final String value) {
        LOGGER.traceEntry();
        LOGGER.info("Selecting element located by :{} by text {}", locator.getName(), value);
        performElementAction(element -> {
            final var select = new Select(element);
            select.selectByValue(value);
        }, locator);

        LOGGER.traceExit();
    }

    /**
     * This method is used to get all the selected values from the dropdown
     *
     * @param locator - The locator of the dropdown
     * @return -The List of all the selected items in the dropdown
     */
    public static List<String> getSelectedItems(final Locator locator) {
        LOGGER.traceEntry();
        return getElementAttribute(element -> {
            final var select = new Select(element);
            return select
                    .getAllSelectedOptions()
                    .stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList());
        }, locator, emptyList());

    }

    /**
     * Selects the value from dropdown based on index.
     *
     * @param locator locator of the dropdown
     * @param index   index to be selected
     */
    public static void selectByIndex(final Locator locator, final int index) {
        LOGGER.traceEntry();
        LOGGER.info("Selecting element located by: {} by index: {}", locator.getName(), index);
        performElementAction(e -> {
            final var select = new Select(e);
            select.selectByIndex(index);
        }, locator);
        LOGGER.traceExit();
    }

    /**
     * This method return the selected item from the dropdown
     *
     * @param locator - The locator of the dropdown
     * @return The string value of the selected Item - if exits else empty
     */
    public static String getSelectedItem(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Getting selected option from the element located by {}:", locator.getName());
        return getElementAttribute(element -> {
            final var select = new Select(element);
            try {
                return select.getFirstSelectedOption().getText();
            } catch (final NoSuchElementException e) {
                return EMPTY;
            }
        }, locator, EMPTY);
    }

    /**
     * This method is used to deselect all the selected options in the dropdown
     *
     * @param locator - The link of the locator of the dropdown
     */
    public static void deselectAll(final Locator locator) {
        LOGGER.traceEntry();
        LOGGER.info("Deselecting element located by {}", locator.getName());
        performElementAction(element -> {
            final var select = new Select(element);
            if (!select.isMultiple()) {
                throwError(DROPDOWN_DESELECT_ERROR);
            }
            select.deselectAll();
        }, locator);

        LOGGER.traceExit();
    }

    /**
     * Deselects the option from the dropdown based on its index.
     *
     * @param locator link of dropdown
     * @param index   index of the option to deselect
     */
    public static void deselectByIndex(final Locator locator, final int index) {
        LOGGER.traceEntry();
        LOGGER.info("Deselecting element located by: {} by index: {}", locator.getName(), index);
        performElementAction(e -> {
            final var select = new Select(e);
            if (!select.isMultiple()) {
                throwError(DROPDOWN_DESELECT_ERROR);
            }
            select.deselectByIndex(index);
        }, locator);
        LOGGER.traceExit();
    }

    /**
     * Deselects the option from the dropdown based on its visible text.
     *
     * @param locator link of dropdown
     * @param text    visible text of the option to deselect
     */
    public static void deselectByText(final Locator locator, final String text) {
        LOGGER.traceEntry();
        LOGGER.info("Deselecting element located by: {} by visible text: {}", locator.getName(), text);
        performElementAction(e -> {
            final var select = new Select(e);
            if (!select.isMultiple()) {
                throwError(DROPDOWN_DESELECT_ERROR);
            }
            select.deselectByVisibleText(text);
        }, locator);
        LOGGER.traceExit();
    }


    /**
     * Deselects the option from the dropdown based on its index.
     *
     * @param locator {@link Locator} of dropdown
     * @param value   value of the option to deselect
     */
    public static void deselectByValue(final Locator locator, final String value) {
        LOGGER.traceEntry();
        LOGGER.info("Deselecting element located by: {} by index: {}", locator.getName(), value);
        performElementAction(e -> {
            final var select = new Select(e);
            if (!select.isMultiple()) {
                throwError(DROPDOWN_DESELECT_ERROR);
            }
            select.deselectByValue(value);
        }, locator);
        LOGGER.traceExit();
    }

}
