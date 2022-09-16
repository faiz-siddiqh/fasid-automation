package com.fasid.core.ui.browsers.desktop;

import com.fasid.config.Config;
import com.fasid.core.ui.browsers.Browser;
import com.fasid.core.ui.browsers.HasService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

public class Chrome implements Browser<Capabilities>, HasService<WebDriver, DriverService, Capabilities> {

    private ChromeDriverService chromeDriverService;

    @Override
    public ChromeOptions getOptions() {

        ChromeOptions options = new ChromeOptions();
        options.setHeadless(Config.isHeadless());
        options.setAcceptInsecureCerts(true);
        options.addArguments("--kiosk-printing");
        options.addArguments("--incognito");
        options.addArguments("--use-fake-device-for-media-stream", "--use-fake-ui-for-media-stream", "--disable-popup-blocking");
        options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        options.setCapability(ChromeOptions.LOGGING_PREFS, logPrefs);

        if (Config.isMobileEmulated()) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Nexus 5");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
            options.setExperimentalOption("w3c", false);
            options.setExperimentalOption("networkConnectionEnabled", true);

        }

        return options;
    }

    @Override
    public Capabilities getMobileCapabilities() {
        ChromeOptions options = new ChromeOptions();
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

        if (Objects.isNull(chromeDriverService)) {
            WebDriverManager.chromedriver().setup();
            chromeDriverService = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(System.getProperty("webdriver.chrome.driver")))
                    .usingAnyFreePort()
                    .build();
            chromeDriverService.start();
        }

        return chromeDriverService;
    }

    @Override
    public WebDriver getLocalDriver(DriverService service, Capabilities capabilities) {
        return new ChromeDriver((ChromeDriverService) service, (ChromeOptions) capabilities);
    }

    @Override
    public void stopService() {
        if (Objects.nonNull(chromeDriverService)) {
            chromeDriverService.stop();
        }

    }
}
