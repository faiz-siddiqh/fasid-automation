package com.fasid.core.ui.utils;

import com.fasid.core.ui.BrowserInit;
import com.fasid.core.ui.WebDriverThread;
import com.fasid.core.ui.waits.Waits;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

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

    private BrowserAction switchToChildWindow(WebDriver driver) {
        String currentWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String eachWindowHandle : allWindowHandles) {
            if (!currentWindowHandle.equalsIgnoreCase(eachWindowHandle)) {
                driver.switchTo().window(eachWindowHandle);
                break;
            }
        }
        return this;
    }

    public BrowserAction createAndSwitchToNewTab() {
        String mainWindowHandle = driver.getWindowHandle();
        js.executeScript("window.open()");
        switchToChildWindow();
        return this;
    }

    public BrowserAction closeAllTabs() {
        String parentHandle = driver.getWindowHandle();

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

    public BrowserAction jsClick(WebElement element) {

        js.executeScript("arguments[0]dispatchEvent(new MouseEvent('click;,{'bubble':true}));", element);
        return this;
    }

    public BrowserAction jsClick(By element) {

        jsClick(driver.findElement(element));
        return this;
    }

    public <T> T executeJS(String jsScript) {
        return (T) js.executeScript(jsScript);

    }

    public <T> T executeJS(String jsScript, Objects... args) {
        return (T) js.executeScript(jsScript, args);

    }

    public <T> T executeJSAsync(String jsScript) {
        return (T) js.executeAsyncScript(jsScript);

    }

    public Map<String, Object> getLocalStorage() {
        String jsScript = "return window.localStorage";

        return (Map<String, Object>) executeJS(jsScript);
    }

    public BrowserAction jsScrollToView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return this;
    }

    public BrowserAction scrollToViewSmoothly(WebElement element) {
        final String jsScript = "arguments[0].scrollToView{behavior:\"smooth\",block:\"center\",inline:\"center\"});";
        js.executeScript(jsScript, element);
        return this;
    }

    public boolean hasScrollBar(WebElement element) {

        return (Boolean) js.executeScript("return arguments[0].scrollHeight>arguments[0].offsetHeight;", element);
    }

    public BrowserAction scrollBy() {
        String jsScript = "window.scrollBy(0,window.innerHeight);";
        return executeJS(jsScript);
    }

    public BrowserAction scrollBy(int x, int y) {
        String jsScript = String.format("window.scrollBy(%x,%y);", x, y);
        return executeJS(jsScript);
    }

    public BrowserAction clearLocalStorage() {
        String jsScript = "localStorage.clear()";
        return executeJS(jsScript);
    }

    public BrowserAction clearSessionStorage() {
        String jsScript = "sessionStorage.clear()";
        return executeJS(jsScript);
    }






    /*
    -----------------------------------------------------------INTERACTIIONS------------------------------------------------
     */

    public BrowserAction mouseHover(WebElement element) {

        actions
                .get()
                .moveToElement(element)
                .pause(Duration.ofSeconds(1))
                .perform();
        return this;
    }

    public BrowserAction contextClick(WebElement element) {

        actions
                .get()
                .contextClick(element)
                .build()
                .perform();
        return this;
    }

    public BrowserAction doubleClick(WebElement element) {

        actions
                .get()
                .moveToElement(element)
                .doubleClick(element)
                .build()
                .perform();

        return this;
    }

    public BrowserAction dragAndDrop(WebElement source, WebElement destination) {

        actions
                .get()
                .dragAndDrop(source, destination)
                .perform();

        return this;
    }

    public BrowserAction dragAndDropBy(WebElement source, int xOffset, int yOffset) {

        actions
                .get()
                .dragAndDropBy(source, xOffset, yOffset)
                .perform();

        return this;
    }

    public BrowserAction sendKeysUsingActions(WebElement element, String textToBeSent) {
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
        Keys CtrlKey = (System.getProperty("os.name").toLowerCase().contains("mac"))
                ? Keys.COMMAND
                : Keys.CONTROL;

        actions
                .get()
                .sendKeys(CtrlKey)
                .build()
                .perform();

        return this;
    }

    public List<String> getTextFromElements(By element) {

        List<String> list = new ArrayList<>();

        findElements(element)
                .forEach((webelement) -> {
                    list.add(webelement.getText());
                });

        return list;
    }

    public List<WebElement> findElements(By element) {

        return driver.findElements(element);
    }

    public BrowserAction actionClick(WebElement element) {
        actions
                .get()
                .moveToElement(element)
                .click()
                .build()
                .perform();

        return this;
    }


    public BrowserAction performMultiClick(CharSequence keys) {

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


    public BrowserAction clickOn(By element) {

        waits.forElementToBeClickable(element);
        driver.findElement(element).click();

        return this;
    }

    public BrowserAction clickOn(WebElement element) {

        waits.forElementToBeClickable(element);
        element.click();

        return this;
    }

    public BrowserAction clickAndTypeAndWait(By element, String keysToSend) {
        clickOn(element)
                .typeTheValue(element, keysToSend);

        return this;
    }

    public BrowserAction clickAndTypeAndWait(WebElement element, String keysToSend) {
        clickOn(element)
                .typeTheValue(element, keysToSend);

        return this;
    }

    public BrowserAction typeTheValue(By element, String keysToSend) {

        driver.findElement(element).sendKeys(keysToSend);
        waits
                .forSometime(1000);

        return this;
    }

    public BrowserAction typeTheValue(WebElement element, String keysToSend) {

        element.sendKeys(keysToSend);
        waits
                .forSometime(1000);

        return this;
    }

    public BrowserAction clear(By element) {
        driver.findElement(element).clear();
        waits.forSometime(500);

        return this;
    }

    public BrowserAction clear(WebElement element) {

        Stream.iterate(0, i -> i + 1)
                .limit(element.getAttribute("value").length())
                .forEach(i -> {
                    element.sendKeys(Keys.BACK_SPACE);
                });

        return this;
    }

    public BrowserAction clearAndTypeAndWait(By element, String valueToSend) {

        clear(driver.findElement(element))
                .typeTheValue(element, valueToSend);

        return this;
    }

    public boolean areElementsPresent(By element) {

        return findElements(element).size() > 0;
    }

    public BrowserAction navigateTo(String Url) {

        driver.get(Url);
        return this;
    }

    public int getRandomNumber(int min, int max) {

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
