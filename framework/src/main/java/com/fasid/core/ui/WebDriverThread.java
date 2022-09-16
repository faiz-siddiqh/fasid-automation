package com.fasid.core.ui;

import com.fasid.config.Config;
import com.fasid.core.ui.browsers.Browser;
import com.fasid.core.ui.browsers.HasService;
import com.fasid.core.ui.browsers.desktop.Chrome;
import com.fasid.core.ui.browsers.desktop.Firefox;
import com.fasid.enums.BrowserTypes;
import com.fasid.enums.Run;
import com.fasid.logger.TestLogger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class WebDriverThread {

    private WebDriver webDriver;
    private Browser<Capabilities> browsers;

    public WebDriver getDriver() {
        if (Objects.isNull(webDriver)) {

            Run runType = Config.getTestRunType();
            BrowserTypes browserTypes = Config.determineEffectiveDriver();

            browsers = getBrowserObject(browserTypes);
            webDriver = setExecutionStyle(runType);
            Browser.setTimeOuts(webDriver);
            if (!runType.equals(Run.MOBILE) && !runType.equals(Run.DEVICE)) {
                Browser.setWindowSize(webDriver);
            }
        }

        return webDriver;
    }

    private WebDriver setExecutionStyle(Run runType) {

        Capabilities options;
        DriverService service;

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
                    throw new RuntimeException("Selected Browser doesnt have service to be executed");
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

    private Browser<Capabilities> getBrowserObject(BrowserTypes browserName) {
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
