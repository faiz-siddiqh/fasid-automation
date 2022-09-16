package com.fasid.core.ui.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;


public interface Browser<T extends Capabilities> {

    T getOptions();

    T getMobileCapabilities();

    static RemoteWebDriver getRemoteWebDriver(URL remoteURL, Capabilities capabilities) {

        RemoteWebDriver driver = new RemoteWebDriver(remoteURL, capabilities);
        driver.setFileDetector(new LocalFileDetector());

        //Implement Grid if required

        return driver;
    }

    static void setWindowSize(WebDriver driver) {

        Dimension defaultDimensionSize = new Dimension(1920, 1080);
        driver.manage().window().setSize(defaultDimensionSize);
    }

    static void setTimeOuts(WebDriver driver) {
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(60));
    }

}
