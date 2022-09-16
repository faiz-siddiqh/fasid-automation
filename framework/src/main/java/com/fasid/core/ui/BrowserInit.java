package com.fasid.core.ui;

import com.fasid.config.Config;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;

import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BrowserInit {

    private static ThreadLocal<WebDriverThread> primaryThreadLocalDriver;
    private static ThreadLocal<WebDriverThread> secondThreadLocalDriver;
    private static List<WebDriverThread> threadLocalDriverPool = Collections.synchronizedList(new LinkedList<>());

    @BeforeSuite(alwaysRun = true)
    public static void initDriver(XmlTest test) throws ConfigurationException {
        Config.getInstance();
        primaryThreadLocalDriver = ThreadLocal.withInitial(() -> {
            WebDriverThread primaryWebDriverThread = new WebDriverThread();
            threadLocalDriverPool.add(primaryWebDriverThread);

            return primaryWebDriverThread;
        });
        secondThreadLocalDriver = ThreadLocal.withInitial(() -> {
            WebDriverThread secondaryWebDriverThread = new WebDriverThread();
            threadLocalDriverPool.add(secondaryWebDriverThread);

            return secondaryWebDriverThread;
        });

    }


    /**
     * This method is to get Instance on Webdriver in current thread
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {

        return primaryThreadLocalDriver.get().getDriver();
    }

    public static WebDriver getSecondaryDriver() {
        return secondThreadLocalDriver.get().getDriver();
    }

    @AfterSuite(alwaysRun = true)
    public static void quitDriver() {
        threadLocalDriverPool.forEach((webDriverThread -> {
            webDriverThread.quitDriver();
            primaryThreadLocalDriver.remove();
            secondThreadLocalDriver.remove();
        }));
    }

}
