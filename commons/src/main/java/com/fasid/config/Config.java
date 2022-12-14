package com.fasid.config;

import com.fasid.enums.BrowserTypes;
import com.fasid.enums.Run;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.time.Duration;
import java.util.Objects;

import static com.fasid.config.Constants.*;

public class Config {


    private static final String HUB_USERNAME = System.getenv("HUB_USERNAME");
    private static final String HUB_ACCESSKEY = System.getenv("HUB_ACCESSKEY");
    private static final String HUB_URL = System.getenv("HUB_URL");
    private static final boolean isGrid = Boolean.parseBoolean(System.getProperty("HUB_URL", "false"));
    public static boolean quickDebugMode = Boolean.parseBoolean(System.getProperty("debugMode", "false"));

    private static Configuration configuration;

    //This is an example of Singleton Design Pattern
    public static Configuration getInstance() throws ConfigurationException {
        if (configuration == null) {
            Configurations configs = new Configurations();
            configuration = configs.properties(loadPropertiesFile());
        }

        return configuration;
    }

    private static File loadPropertiesFile() {

        String configFile = getEnvProperties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String configFilePath = Objects.requireNonNull(loader.getResource(configFile)).getPath();

        return new File(configFilePath);

    }

    private static String getEnvProperties() {

        return getEnv() + ".properties";
    }

    private static String getEnv() {
        if (System.getProperty("env", "local") == null) {
            throw new IllegalArgumentException("Environment not found- aborting run");
        }

        return System.getProperty("env", "qa"); //the respective environment has to be passed
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty(HEADLESS, "false"));
    }

    public static String getProperty(String property, String defaultValue) {
        if (System.getProperty(property) != null && !System.getProperty(property).isEmpty()) {
            return System.getProperty(property);
        }

        return defaultValue;
    }

    public static String getProperty(String property) {
        if (System.getProperty(property) != null && !System.getProperty(property).isEmpty()) {
            return System.getProperty(property);
        }

        return configuration.getString(property);
    }

    public static String getGridPort() {
        return getProperty(HUB_PORT, "4444");
    }

    public static Duration getDefaultWaitDuration() {
        long duration = Long.parseLong(getProperty(WAIT_DURATION, "40"));
        return Duration.ofSeconds(duration);
    }

    public static Duration getDefaultPollingDuration() {
        long duration = Long.parseLong(getProperty(POLLING_DURATION, "40"));
        return Duration.ofSeconds(duration);
    }

    public static String getHubUsername() {

        return getProperty(HUB_USER_NAME, HUB_USERNAME);
    }

    public static String getHubAccesskey() {

        return getProperty(HUB_ACCESS_KEY, HUB_ACCESSKEY);
    }

    public static String getBaseUrl() {

        return getProperty(BASEURL);
    }

    public static String getAppUrl() {

        return getProperty(APPURL);
    }

    public static boolean isMobileEmulated() {
        if (System.getProperty(MOBILE_EMULATED) != null && !System.getProperty(MOBILE_EMULATED).isEmpty()) {
            return Boolean.parseBoolean(System.getProperty(MOBILE_EMULATED));
        }
        return configuration.getBoolean(MOBILE_EMULATED, false);
    }

    public static String determineEffectiveOS() {
        if (Objects.nonNull(System.getProperty("os"))) {
            return System.getProperty("os.version.override");
        }
        return "Windows";
    }

    public static String determineEffectiveMobileAutomationName() {
        if (Objects.nonNull(System.getProperty("mobile.automation.name")))
            return System.getProperty("mobile.automation.name");

        return "XCUITest";
    }

    public static BrowserTypes determineEffectiveDriver() {
        if (Objects.nonNull(System.getProperty("browser")))
            return BrowserTypes.valueOf(System.getProperty("browser").toUpperCase());

        return BrowserTypes.CHROME;
    }

    public static String determineEffectiveBrowserVersion() {
        if (Objects.nonNull(System.getProperty("browser.version")))
            return System.getProperty("broswer.version");

        return "latest";
    }

    public static boolean enableNetworkLog() {

        if (Objects.nonNull(System.getenv("enable.network.log")))
            return Boolean.valueOf(System.getenv("enable.network.log"));

        return false;
    }

    public static String determineEffectiveMobileDevice() {
        if (Objects.nonNull(System.getProperty("mobile"))) {
            return System.getProperty("mobile");
        }

        return "iphone 12 Pro Max";
    }

    public static String determineEffectiveMobileDeviceVersion() {
        if (Objects.nonNull(System.getProperty("mobile.device.version"))) {
            return System.getProperty("mobile.device.version");
        }

        return "14.4";
    }

    public static Run getTestRunType() {
        if (Objects.nonNull(System.getProperty("runType"))) {
            return Run.valueOf(System.getProperty("runType").toUpperCase());
        }

        return Run.LOCAL;
    }

    public static String getAppiumServerUrl() {
        return getProperty(HUB_USER_NAME, HUB_USER_NAME);
    }

    public static String getGridHost() {
        if (Objects.nonNull(System.getenv("HUB_HOST")))
            return System.getenv("HUB_HOST");

        return "localhost";
    }

    public static String getToken() {

        if (Objects.nonNull(System.getenv("TOKEN")))
            return System.getenv("TOKEN");

        return getProperty(TOKEN);
    }
}
