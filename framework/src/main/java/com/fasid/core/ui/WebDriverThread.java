package com.fasid.core.ui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasid.config.Config;
import com.fasid.core.ui.browsers.Browser;
import com.fasid.core.ui.browsers.HasService;
import com.fasid.core.ui.browsers.desktop.Chrome;
import com.fasid.core.ui.browsers.desktop.Firefox;
import com.fasid.core.ui.exception.FrameworkError;
import com.fasid.enums.BrowserTypes;
import com.fasid.enums.Run;
import com.fasid.logger.TestLogger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

public class WebDriverThread {

    private WebDriver webDriver;
    private Browser<Capabilities> browsers;
    public static Map<String, Object> sharedData;

    public WebDriver getDriver() {
        if (Objects.isNull(webDriver)) {

            final Run runType = Config.getTestRunType();
            final BrowserTypes browserTypes = Config.determineEffectiveDriver();

            browsers = getBrowserObject(browserTypes);
            webDriver = setExecutionStyle(runType);
            Browser.setTimeOuts(webDriver);
            if (!runType.equals(Run.MOBILE) && !runType.equals(Run.DEVICE)) {
                Browser.setWindowSize(webDriver);
            }

            sharedData = new HashMap<>();
        }

        return webDriver;
    }

    private WebDriver setExecutionStyle(final Run runType) {

        final Capabilities options;
        final DriverService service;

        if (runType.equals(Run.MOBILE)) {
            options = browsers.getMobileCapabilities();
        } else {
            options = browsers.getOptions();
        }

        try {
            if (runType.equals(Run.GRID) || runType.equals(Run.MOBILE)) {
                webDriver = Browser.getRemoteWebDriver(getHubHost(), options);
            } else {
                if (browsers instanceof HasService) {
                    service = ((HasService) browsers).startService();
                    webDriver = ((HasService) browsers).getLocalDriver(service, options);
                } else {
                    throw new RuntimeException("Selected Browser doesn't have service to be executed");
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return webDriver;
    }

    private URL getHubHost() throws MalformedURLException {

        if (Config.getTestRunType().name().equalsIgnoreCase("mobile")) {
            return new URL(Config.getAppiumServerUrl());
        }

        return new URL("https" + Config.getGridHost() + ":" + Config.getGridPort() + "/wd/hub");
    }

    private Browser<Capabilities> getBrowserObject(final BrowserTypes browserName) {
        switch (browserName) {
            case CHROME:
                return new Chrome();

            case FIREFOX:
                return new Firefox();

//            case CHROMEANDROID:
//                return new MobileBrowser(new AndroidOS(new ChromeMobile()) );

            default:
                throw new InvalidArgumentException("Invalid Argument - Please check browser Name");
        }

    }

    /**
     * Clears all the shared data for the session
     */
    public static void clearSharedData() {
        sharedData.clear();
    }

    /**
     * get a value from the shared data
     *
     * @param key
     * @param <T>
     * @return shared data value
     */
    public static <T> T getSharedData(final String key) {
        if (Objects.isNull(sharedData)) {
            throw new FrameworkError("Shared Data map not initialized . Session not active");
        }

        return (T) sharedData.get(key);
    }

    /**
     * This method is used to set /add a value to the shared data
     *
     * @param key
     * @param data
     * @param <T>  Type of Data
     */
    public static <T> void setSharedData(final String key, final T data) {
        sharedData.put(key, data);
    }

    void quitDriver() {
        if (Objects.nonNull(webDriver)) {
            try {
                webDriver.quit();
            } catch (Exception e) {
                TestLogger.log("Unable to quit driver session . Please check grid health");
            } finally {
                webDriver = null;
            }
        }
    }

}
