package com.fasid.core.ui.browsers.desktop;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.fasid.config.Config;
import com.fasid.core.ui.browsers.Browser;
import com.fasid.core.ui.browsers.HasService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.service.DriverService;

public class Firefox implements Browser<Capabilities>, HasService<WebDriver, DriverService, Capabilities> {

    private GeckoDriverService geckoDriverService;

    @Override
    public FirefoxOptions getOptions() {

        final FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(Config.isHeadless());
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        final FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("devtools.console.stdout.content", true);

        return options;
    }

    @Override
    public Capabilities getMobileCapabilities() {
        final ChromeOptions options = new ChromeOptions();
        options.setCapability("build", Config.getProperty("test.build"));
        options.setCapability("name", Config.getProperty("test.name"));
        options.setCapability("platformName", Config.determineEffectiveOS());
        options.setCapability("automationName", Config.determineEffectiveMobileAutomationName());
        options.setCapability("browserName", Config.determineEffectiveDriver());
        options.setCapability("browserVersion", Config.determineEffectiveBrowserVersion());
        options.setCapability("network", Config.enableNetworkLog());
        options.setCapability("idleTimeout", 360);
        options.setCapability("deviceName", Config.determineEffectiveMobileDevice());
        options.setCapability("CapabilityType.VERSION", Config.determineEffectiveMobileDeviceVersion());

        return options;

    }

    @Override
    public DriverService startService() throws IOException {

        if (Objects.isNull(geckoDriverService)) {
            WebDriverManager.chromedriver().setup();
            geckoDriverService = new GeckoDriverService.Builder()
                    .usingDriverExecutable(new File(WebDriverManager.firefoxdriver().getDownloadedDriverPath()))
                    .usingAnyFreePort()
                    .build();
            geckoDriverService.start();
        }

        return geckoDriverService;
    }

    @Override
    public WebDriver getLocalDriver(final DriverService service, final Capabilities capabilities) {
        return new FirefoxDriver((GeckoDriverService) service, (FirefoxOptions) capabilities);
    }

    @Override
    public void stopService() {
        if (Objects.nonNull(geckoDriverService)) {
            geckoDriverService.stop();
        }

    }
}
