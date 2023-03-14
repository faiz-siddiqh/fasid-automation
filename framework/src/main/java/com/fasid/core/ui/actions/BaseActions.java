package com.fasid.core.ui.actions;

import com.fasid.builders.Locator;
import com.fasid.config.Config;
import com.fasid.core.ui.BrowserInit;
import com.fasid.core.ui.WebDriverThread;
import com.fasid.core.ui.exception.FrameworkError;
import com.fasid.enums.Run;
import com.fasid.enums.TimeOutSetting;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import static com.fasid.core.ui.actions.DriverActions.executeJsScript;
import static com.fasid.core.ui.actions.DriverActions.pause;
import static com.fasid.core.ui.actions.ElementFinderActions.find;
import static com.fasid.core.ui.utils.ErrorUtils.handleAndThrow;
import static com.fasid.core.ui.utils.ReportManager.logInfo;
import static com.fasid.enums.Message.DRIVER_ERROR_OCCURED;
import static com.fasid.enums.WaitStrategy.CLICKABLE;
import static com.fasid.enums.WaitStrategy.VISIBLE;
import static java.text.MessageFormat.format;
import static org.apache.logging.log4j.LogManager.getLogger;

final class BaseActions {

    private static final Logger LOGGER = getLogger();

    private static final String HIGHLIGHT_STYLE = "highlight.style";

    private BaseActions() {
        //Utility class
    }

    /**
     * Gets driver specific attribute
     *
     * @param action
     * @param defaultValue default value to be returned in case of any error
     * @param <D>          driver type
     * @param <E>          attribute type
     * @return driver specific attribute
     */
    public static <D extends WebDriver, E> E getDriverAttribute(final Function<D, E> action, final E defaultValue) {
        LOGGER.traceEntry();

        try {
            return LOGGER.traceExit(action.apply((D) BrowserInit.getDriver()));
        } catch (final FrameworkError error) {
            return defaultValue;
        }
    }

    public static <D extends WebDriver> void performWebDriverAction(final Consumer<D> action) {
        LOGGER.traceEntry();
        try {
            action
                    .accept((D) BrowserInit.getDriver());
            logInfo("Action performed successfully : " + action);
        } catch (final WebDriverException e) {
            e.printStackTrace();
        }

        LOGGER.traceExit();
    }

    public static <E> E getElementAttribute(final Function<WebElement, E> action, final Locator locator, final E defaultValue) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator, CLICKABLE), "red");
            return LOGGER.traceExit(action.apply(find(locator, VISIBLE)));
        } catch (FrameworkError e) {
            return defaultValue;
        }
    }

    public static <D extends WebDriver> void performElementAction(final Consumer<D> action) {
        LOGGER.traceEntry();
        try {
            action.accept((D) BrowserInit.getDriver());
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURED, e, e.getMessage());
        }

        LOGGER.traceExit();
    }

    public static void performElementAction(final Consumer<WebElement> action, final Locator locator) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator, CLICKABLE), "red");
            action.accept(find(locator, CLICKABLE));
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURED, e, e.getMessage());
        }

        LOGGER.traceExit();
    }

    public static <D extends WebDriver> void performElementAction(final BiConsumer<D, WebElement> action, final Locator locator) {
        LOGGER.traceEntry();
        try {
            prepareElementAction(find(locator, CLICKABLE), "red");
            action.accept((D) BrowserInit.getDriver(), find(locator, CLICKABLE));
        } catch (final WebDriverException e) {
            handleAndThrow(DRIVER_ERROR_OCCURED, e, e.getMessage());
        }

        LOGGER.traceExit();
    }


    private static void prepareElementAction(final WebElement element, final String color) {
        highlight(element, color);
        unHighlight(element);
    }

    private static void highlight(final WebElement element, final String color) {
        LOGGER.traceEntry();
        if (Config.getTestRunType() == Run.LOCAL && Config.isHighlighted()) {
            final var style = element.getAttribute("style");
            WebDriverThread.setSharedData(HIGHLIGHT_STYLE, style);
            executeJsScript("arguments[0].setAttribute('style', arguments[1] + arguments[2]);", element, style, format("color: {0}; border: 3px solid {0};", color));
            pause(Duration.ofMillis(TimeOutSetting.HIGHLIGHT_DELAY.getDuration()));
        }
    }

    private static void unHighlight(final WebElement element) {
        LOGGER.traceEntry();
        if (Config.getTestRunType() == Run.LOCAL && Config.isHighlighted()) {
            final var style = WebDriverThread.getSharedData(HIGHLIGHT_STYLE);
            executeJsScript("arguments[0].setAttribute('style', arguments[1]);", element, style);
            pause(Duration.ofMillis(TimeOutSetting.HIGHLIGHT_DELAY.getDuration()));
        }
    }


    public static int getRandomNumber(final int min, final int max) {
        LOGGER.traceEntry();
        return LOGGER.traceExit((int) (Math.random() * (max - min + 1)) + min);
    }


}
