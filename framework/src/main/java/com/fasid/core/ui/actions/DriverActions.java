package com.fasid.core.ui.actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasid.core.ui.BrowserInit;
import com.fasid.core.ui.utils.ReportManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static com.fasid.core.ui.actions.BaseActions.getDriverAttribute;
import static com.fasid.core.ui.actions.BaseActions.performWebDriverAction;
import static com.fasid.core.ui.actions.NavigateActions.navigateActions;
import static com.fasid.core.ui.utils.ErrorUtils.handleAndThrow;
import static com.fasid.enums.Message.LOGS_CREATION_ERROR;
import static com.fasid.enums.Message.LOGS_WRITING_ERROR;
import static java.lang.System.getProperty;
import static java.lang.Thread.currentThread;
import static java.text.MessageFormat.format;
import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

public class DriverActions {

    private static Logger LOGGER = getLogger();

    private DriverActions() {
        //Utility class not to be initialized
    }


    /**
     * This method is used to accept the alert and return the message
     *
     * @return message in the alert
     */
    public static String acceptAlert() {
        LOGGER.traceEntry();
        return getDriverAttribute(webDriver -> {
            final var alert = webDriver.switchTo().alert();
            final var message = alert.getText();
            alert.accept();
            return message;
        }, EMPTY);
    }

    /**
     * This message is used to accept the alert and send the message to the alert and return the text on the alert
     *
     * @param text -text to be send in the textbox
     * @return the message on the alert
     */
    public static String acceptAlertAndEnterText(final String text) {
        LOGGER.traceEntry();

        return getDriverAttribute(webDriver -> {
            final var alert = webDriver.switchTo().alert();
            final var message = alert.getText();
            alert.sendKeys(text);
            alert.accept();
            return message;
        }, EMPTY);

    }

    /**
     * This method is used to dismiss the alert
     *
     * @return The message in the alert
     */
    public static String dismissAlert() {
        LOGGER.traceEntry();
        return getDriverAttribute(webDriver -> {
            final var alert = webDriver.switchTo().alert();
            final String message = alert.getText();
            alert.dismiss();
            return message;
        }, EMPTY);
    }

    /**
     * This method is used to fetch the current window handle of the browser
     *
     * @return The Current window handle
     */
    public static String getCurrentHandle() {
        LOGGER.traceEntry();
        return getDriverAttribute((webDriver -> webDriver.getWindowHandle()), EMPTY);

    }

    /**
     * This method is used to switch to a new window
     *
     * @param type - The Window Type - Tab or Window to be switched to
     */
    public static void switchToNewWindow(final WindowType type) {
        LOGGER.traceEntry();
        LOGGER.info("Switching to new Window handle");

        performWebDriverAction(webDriver -> webDriver.switchTo().newWindow(type));
        LOGGER.traceExit();

    }

    public static void switchToParentFrame() {
        LOGGER.traceEntry();
        LOGGER.info("Switching to new Parent Frame");

        performWebDriverAction(webDriver -> webDriver.switchTo().parentFrame());
        LOGGER.traceExit();

    }

    public static void switchToWindow(final String nameOfHandle) {

        LOGGER.traceEntry();
        LOGGER.info("Switching to new Window handle -" + nameOfHandle);
        performWebDriverAction(webDriver -> webDriver.switchTo().window(nameOfHandle));
        LOGGER.traceExit();
    }

    public static void switchToMainWindow() {

        LOGGER.traceEntry();
        LOGGER.info("Switching to Parent handle ");
        performWebDriverAction(webDriver -> webDriver.switchTo()
                .window(windowHandles().get(0)));
        LOGGER.traceExit();
    }

    /**
     * This method is used to retrieve a cookie by name
     *
     * @param name - Name of the cookie to be returned
     * @return The Cookie object
     */
    public static Cookie cookie(final String name) {
        LOGGER.traceEntry();
        return getDriverAttribute(webDriver -> webDriver.manage()
                .getCookieNamed(name), null);

    }

    /**
     * This method is used to switch to child window
     */
    public static void switchToChildWindow() {
        LOGGER.traceEntry();
        LOGGER.info("Switching to child window");

        performWebDriverAction(webDriver -> {
            final var currentWindowHandle = getCurrentHandle();
            windowHandles()
                    .stream()
                    .filter(windowHandle -> (!windowHandle.equalsIgnoreCase(currentWindowHandle)))
                    .findFirst()
                    .ifPresent(windowHandle -> switchToWindow(windowHandle));
        });
        LOGGER.traceExit();
    }

    /**
     * This method returns the list of the cookies of the session
     *
     * @return List of names of Cookies
     */
    public static List<String> getAllCookies() {
        LOGGER.traceEntry();
        return getDriverAttribute((driver) -> driver.manage()
                .getCookies()
                .stream()
                .map(Cookie::getName)
                .collect(Collectors.toList()), emptyList());
    }

    /**
     * This method is used to delete all the cookies
     */
    public static void deleteAllCookies() {
        LOGGER.traceEntry();
        performWebDriverAction((webDriver -> {
            webDriver.manage().deleteAllCookies();
        }));
        LOGGER.traceExit();
    }


    public static <T> T executeJsScript(final String script, final Object... args) {
        LOGGER.traceEntry();
        LOGGER.info("Executing script");

        return (T) getDriverAttribute((driver) -> ((JavascriptExecutor) driver).executeScript(script, args), null);
    }

    /**
     * This method is used to pause the execution for particular duration
     *
     * @param timeInMillSeconds - Duration of time to be paused
     */
    public static void pause(final Duration timeInMillSeconds) {
        LOGGER.traceEntry();
        performWebDriverAction(driver -> {
            final var action = new Actions(driver);
            action
                    .pause(timeInMillSeconds)
                    .build()
                    .perform();
        });
    }

    /**
     * Gets all open window handles.
     *
     * @return all open window handles
     */
    public static List<String> windowHandles() {
        LOGGER.traceEntry();
        final var handles = getDriverAttribute(WebDriver::getWindowHandles, new ArrayList<String>());
        LOGGER.traceExit();
        return new ArrayList<>(handles);

    }

    /**
     * Makes browser window minimized.
     */
    public static void minimize() {
        LOGGER.traceEntry();

        performWebDriverAction(driver -> driver.manage()
                .window()
                .minimize());
        LOGGER.traceExit();
    }

    /**
     * Title of the browser.
     *
     * @return title of the browser
     */
    public static String title() {
        LOGGER.traceEntry();
        return LOGGER.traceExit(getDriverAttribute(WebDriver::getTitle, EMPTY));
    }

    /**
     * This method switches the window to full screen
     */
    public static void fullScreen() {
        LOGGER.traceEntry();
        performWebDriverAction(webDriver -> webDriver.manage().window().fullscreen());
        LOGGER.traceExit();
    }

    /**
     * This method maximizes the window
     */
    public static void maximise() {
        LOGGER.traceEntry();
        performWebDriverAction(webDriver -> webDriver.manage().window().maximize());
        LOGGER.traceExit();
    }

    /**
     * Access to Navigation related actions
     *
     * @return Instance of Navigation Actions Class
     */
    public static NavigateActions navigate() {
        LOGGER.traceEntry();
        ReportManager.logInfo("Returning NavigateActions Instance");
        return LOGGER.traceExit(navigateActions());

    }

    /**
     * This method is used to close all the tabs other than current handle
     */
    public static void closeAllOtherTabs() {
        LOGGER.traceEntry();
        final var currentHandle = getCurrentHandle();
        performWebDriverAction(webDriver -> {
            windowHandles()
                    .stream()
                    .filter(windowHandle -> !windowHandle.equalsIgnoreCase(currentHandle))
                    .forEach(windowHandle -> {
                        switchToWindow(windowHandle);
                        webDriver.close();
                    });
        });
        LOGGER.traceExit();
    }

    /**
     * Close browser window.
     */
    public static void closeWindow() {
        LOGGER.traceEntry();
        LOGGER.info("Closing window");
        performWebDriverAction(WebDriver::close);
        switchToMainWindow();
        LOGGER.traceExit();
    }


    /**
     * This method is used to capture screenshot and return the path
     *
     * @return -
     */
    public static File takeScreenshot() {
        LOGGER.info("Capturing screenshot");
        return ((TakesScreenshot) BrowserInit.getDriver()).getScreenshotAs(OutputType.FILE);

    }


    public static void saveLogs() {
        LOGGER.traceEntry();
        final String basePath = getProperty("user.dir") + "/build/test-logs/";
        performWebDriverAction(webDriver -> {
            try {
                final var logTypes = webDriver
                        .manage()
                        .logs()
                        .getAvailableLogTypes();

                logTypes
                        .forEach(logType -> {
                            LOGGER.info("Saving [{}] logs to file", logType);
                            saveLogType(webDriver, logType, basePath);
                        });

            } catch (final WebDriverException e) {
                LOGGER.catching(e);
                LOGGER.warn("Error while saving logs", e.getMessage());
            }

        });
    }

    /**
     * This method is used to save logs based on Type in folder path specified
     *
     * @param driver   - The web-driver instance
     * @param logType  - The logType
     * @param basePath - Path where the logs to be stored
     */
    private static <D extends WebDriver> void saveLogType(final D driver, final String logType, final String basePath) {

        final var logEntries = driver
                .manage()
                .logs()
                .get(logType);
        final var logFileName = format("{0}/{1}-{2}.log", basePath, logType, currentThread().getId());
        try (final var writer = new BufferedWriter(new FileWriter(logFileName))) {
            logEntries
                    .forEach((logEntry -> {
                        try {
                            writer.write(logEntry.getMessage());
                            writer.write(getProperty("line.separator"));

                        } catch (IOException e) {
                            handleAndThrow(LOGS_WRITING_ERROR, e);
                        }

                    }));

        } catch (IOException e) {
            handleAndThrow(LOGS_CREATION_ERROR, e);
        }

    }


}
