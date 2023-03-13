package com.fasid.core.ui.actions;

import com.fasid.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import static com.fasid.core.ui.actions.BaseActions.performElementAction;
import static com.fasid.core.ui.actions.BaseActions.performWebDriverAction;
import static java.util.Arrays.stream;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.Keys.chord;

public class KeyboardActions {

    private static Logger LOGGER = getLogger();

    private KeyboardActions() {
        //utility class should not be instantiated
    }

    /**
     * This method is used to get Control key based on the device
     *
     * @return - The control key object
     */
    public static Keys getCtrlKeyStroke() {
        LOGGER.traceEntry();

        return LOGGER.traceExit(System.getProperty("os.name").toLowerCase().contains("mac")
                ? Keys.COMMAND
                : Keys.CONTROL);
    }

    /**
     * This method is used to perform key board action on a set of keys
     *
     * @param locator - The locator of the element
     * @param keys    - The sequence of keys to be pressed
     */
    public static void pressKey(final Locator locator, final CharSequence... keys) {
        LOGGER.traceEntry();
        stream(keys)
                .forEach(key -> LOGGER.info("Pressing key {} in element {}", key, locator.getName()));
        performElementAction(element -> element.sendKeys(chord(keys)), locator);
        LOGGER.traceExit();

    }

}
