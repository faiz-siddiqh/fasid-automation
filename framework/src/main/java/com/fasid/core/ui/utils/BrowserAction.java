package com.fasid.core.ui.utils;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

import com.fasid.core.ui.BrowserInit;
import com.fasid.core.ui.waits.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BrowserAction {

    private static BrowserAction INSTANCE;
    private TakesScreenshot screenshot;
    private WebDriver driver;
    private JavascriptExecutor js;
    private Waits waits;
    private ThreadLocal<Actions> actions;

    public BrowserAction() {
        driver = BrowserInit.getDriver();
        screenshot = (TakesScreenshot) driver;
        waits = new Waits(driver);
        actions = ThreadLocal.withInitial(() -> new Actions(driver));
    }

    /**
     * Implementing Singleton pattern
     *
     * @return BrowserAction Instance
     */
    public static BrowserAction getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BrowserAction();
        }

        return INSTANCE;
    }

    public BrowserAction switchToChildWindow() {
        return switchToChildWindow(driver);

    }

    private BrowserAction switchToChildWindow(final WebDriver driver) {
        final String currentWindowHandle = driver.getWindowHandle();

        final Set<String> allWindowHandles = driver.getWindowHandles();

        for (String eachWindowHandle : allWindowHandles) {
            if (!currentWindowHandle.equalsIgnoreCase(eachWindowHandle)) {
                driver.switchTo().window(eachWindowHandle);
                break;
            }
        }
        return this;
    }

    public BrowserAction createAndSwitchToNewTab() {
        final String mainWindowHandle = driver.getWindowHandle();
        js.executeScript("window.open()");
        switchToChildWindow();
        return this;
    }

    public BrowserAction closeAllTabs() {
        final String parentHandle = driver.getWindowHandle();

        for (String eachWindowHandle : driver.getWindowHandles()) {
            if (!eachWindowHandle.equalsIgnoreCase(parentHandle)) {
                driver.switchTo().window(eachWindowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(parentHandle);

        return this;
    }

    public BrowserAction pageRefresh() {
        driver.navigate().refresh();
        return this;
    }

    /*
    ------------------------------------------JS Actions-----------------------------------------------
     */

    public BrowserAction jsClick(final WebElement element) {

        js.executeScript("arguments[0]dispatchEvent(new MouseEvent('click;,{'bubble':true}));", element);
        return this;
    }

    public BrowserAction jsClick(final By element) {

        jsClick(driver.findElement(element));
        return this;
    }

    public <T> T executeJS(final String jsScript) {
        return (T) js.executeScript(jsScript);

    }

    public <T> T executeJS(final String jsScript, final Objects... args) {
        return (T) js.executeScript(jsScript, args);

    }

    public <T> T executeJSAsync(final String jsScript) {
        return (T) js.executeAsyncScript(jsScript);

    }

    public Map<String, Object> getLocalStorage() {
        final String jsScript = "return window.localStorage";

        return (Map<String, Object>) executeJS(jsScript);
    }

    public BrowserAction jsScrollToView(final WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public BrowserAction scrollToViewSmoothly(final WebElement element) {
        final String jsScript = "arguments[0].scrollToView{behavior:\"smooth\",block:\"center\",inline:\"center\"});";
        js.executeScript(jsScript, element);
        return this;
    }

    public boolean hasScrollBar(final WebElement element) {

        return (Boolean) js.executeScript("return arguments[0].scrollHeight>arguments[0].offsetHeight;", element);
    }

    public BrowserAction scrollBy() {
        final String jsScript = "window.scrollBy(0,window.innerHeight);";
        return executeJS(jsScript);
    }

    public BrowserAction scrollBy(final int x, final int y) {
        final String jsScript = String.format("window.scrollBy(%x,%y);", x, y);
        return executeJS(jsScript);
    }

    public BrowserAction clearLocalStorage() {
        final String jsScript = "localStorage.clear()";
        return executeJS(jsScript);
    }

    public BrowserAction clearSessionStorage() {
        final String jsScript = "sessionStorage.clear()";
        return executeJS(jsScript);
    }

    /*
    -----------------------------------------------------------INTERACTIIONS------------------------------------------------
     */

    public BrowserAction mouseHover(final WebElement element) {

        actions
                .get()
                .moveToElement(element)
                .pause(Duration.ofSeconds(1))
                .perform();
        return this;
    }

    public BrowserAction contextClick(final WebElement element) {

        actions
                .get()
                .contextClick(element)
                .build()
                .perform();
        return this;
    }

    public BrowserAction doubleClick(final WebElement element) {

        actions
                .get()
                .moveToElement(element)
                .doubleClick(element)
                .build()
                .perform();

        return this;
    }

    public BrowserAction dragAndDrop(final WebElement source, final WebElement destination) {

        actions
                .get()
                .dragAndDrop(source, destination)
                .perform();

        return this;
    }

    public BrowserAction dragAndDropBy(final WebElement source, final int xOffset, final int yOffset) {

        actions
                .get()
                .dragAndDropBy(source, xOffset, yOffset)
                .perform();

        return this;
    }

    public BrowserAction sendKeysUsingActions(final WebElement element, final String textToBeSent) {
        actions
                .get()
                .sendKeys(element, textToBeSent)
                .build()
                .perform();

        return this;
    }

    public BrowserAction enterKeyStroke() {
        actions
                .get()
                .sendKeys(Keys.RETURN)
                .build()
                .perform();

        return this;
    }

    public BrowserAction escKeyStroke() {
        actions
                .get()
                .sendKeys(Keys.ESCAPE)
                .build()
                .perform();

        return this;
    }

    public BrowserAction ctrlKeyStroke() {
        final Keys CtrlKey = (System.getProperty("os.name").toLowerCase().contains("mac"))
                ? Keys.COMMAND
                : Keys.CONTROL;

        actions
                .get()
                .sendKeys(CtrlKey)
                .build()
                .perform();

        return this;
    }

    public List<String> getTextFromElements(final By element) {

        final List<String> list = new ArrayList<>();

        findElements(element)
                .forEach((webelement) -> {
                    list.add(webelement.getText());
                });

        return list;
    }

    public List<WebElement> findElements(final By element) {

        return driver.findElements(element);
    }

    public BrowserAction actionClick(final WebElement element) {
        actions
                .get()
                .moveToElement(element)
                .click()
                .build()
                .perform();

        return this;
    }

    public BrowserAction performMultiClick(final CharSequence keys) {

        actions
                .get()
                .keyDown(Keys.CONTROL)
                .sendKeys(keys)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();

        return this;
    }

    //Implement Select ALL ,Copy ,Paste using the above method

    public BrowserAction clickOn(final By element) {

        waits.forElementToBeClickable(element);
        driver.findElement(element).click();

        return this;
    }

    public BrowserAction clickOn(final WebElement element) {

        waits.forElementToBeClickable(element);
        element.click();

        return this;
    }

    public BrowserAction clickAndTypeAndWait(final By element, final String keysToSend) {
        clickOn(element)
                .typeTheValue(element, keysToSend);

        return this;
    }

    public BrowserAction clickAndTypeAndWait(final WebElement element, final String keysToSend) {
        clickOn(element)
                .typeTheValue(element, keysToSend);

        return this;
    }

    public BrowserAction typeTheValue(final By element, final String keysToSend) {

        driver.findElement(element).sendKeys(keysToSend);
        waits
                .forSometime(1000);

        return this;
    }

    public BrowserAction typeTheValue(final WebElement element, final String keysToSend) {

        element.sendKeys(keysToSend);
        waits
                .forSometime(1000);

        return this;
    }

    public BrowserAction clear(final By element) {
        driver.findElement(element).clear();
        waits.forSometime(500);

        return this;
    }

    public BrowserAction clear(final WebElement element) {

        Stream.iterate(0, i -> i + 1)
                .limit(element.getAttribute("value").length())
                .forEach(i -> {
                    element.sendKeys(Keys.BACK_SPACE);
                });

        return this;
    }

    public BrowserAction clearAndTypeAndWait(final By element, final String valueToSend) {

        clear(driver.findElement(element))
                .typeTheValue(element, valueToSend);

        return this;
    }

    public boolean areElementsPresent(final By element) {

        return findElements(element).size() > 0;
    }

    public BrowserAction navigateTo(final String Url) {

        driver.get(Url);
        return this;
    }

    public int getRandomNumber(final int min, final int max) {

        return (int) Math.random() * (max - min + 1) + min;
    }

    public BrowserAction switchToParentFrame() {

        driver
                .switchTo()
                .defaultContent();

        return this;
    }

    /*
    -----------------------------------------------------------------------------------------------------------
     */

    public Optional<File> takeScreenshot() {
        try {
            if (Objects.nonNull(screenshot)) {
                return Optional.of(screenshot.getScreenshotAs(OutputType.FILE));
            }
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.empty();
    }

}
