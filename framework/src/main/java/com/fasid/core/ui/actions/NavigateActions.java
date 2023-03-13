package com.fasid.core.ui.actions;

import com.fasid.core.ui.utils.ReportManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static com.fasid.core.ui.actions.BaseActions.getDriverAttribute;
import static com.fasid.core.ui.actions.BaseActions.performWebDriverAction;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

public class NavigateActions {

    private static NavigateActions INSTANCE = new NavigateActions();
    private static Logger LOGGER = getLogger();

    static NavigateActions navigateActions() {
        return INSTANCE;
    }

    /**
     * This method is used to navigate to previous page in browser
     */
    public void back() {

        LOGGER.traceEntry();
        ReportManager.logInfo("Navigating to previous URL");
        performWebDriverAction(webDriver -> webDriver.navigate().back());
        LOGGER.traceExit();
    }

    /**
     * This method is used to navigate to next page in browser
     */
    public void forward() {

        LOGGER.traceEntry();
        performWebDriverAction(webDriver -> webDriver.navigate().forward());
        LOGGER.traceExit();
    }

    /**
     * This method is used to  refresh page in browser
     */
    public void refresh() {

        LOGGER.traceEntry();
        performWebDriverAction(webDriver -> webDriver.navigate().refresh());
        LOGGER.traceExit();
    }

    /**
     * This method is used to  navigate to URL in browser
     */
    public void navigateTo(final String URL) {

        LOGGER.traceEntry();
        ReportManager.logInfo(format("Navigating to {0}", URL));
        performWebDriverAction(webDriver -> webDriver.navigate().to(URL));
        LOGGER.traceExit();
    }

    /**
     * This method is used to  navigate to URL in browser
     */
    public String getCurrentURL() {

        LOGGER.traceEntry();
        ReportManager.logInfo("Returning Current URL ");
        return getDriverAttribute(WebDriver::getCurrentUrl, EMPTY);

    }


}
