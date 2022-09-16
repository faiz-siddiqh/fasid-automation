package com.fasid.core.ui.waits;

import com.fasid.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.fasid.core.ui.BrowserInit.getDriver;

public class Waits {

    private static Waits INSTANCE;
    private Wait<WebDriver> wait;

    public Waits(WebDriver driver) {
        wait = new FluentWait<>(getDriver())
                .withTimeout(Config.getDefaultWaitDuration())
                .pollingEvery(Config.getDefaultPollingDuration())
                .ignoring(NoSuchElementException.class);
    }

    public Waits(WebDriver driver, Duration seconds, Duration pollingSeconds) {
        wait = new FluentWait<>(getDriver())
                .withTimeout(seconds)
                .pollingEvery(pollingSeconds)
                .ignoring(NoSuchElementException.class);
    }

    public Waits() {
        wait = new FluentWait<>(getDriver())
                .withTimeout(Config.getDefaultWaitDuration())
                .pollingEvery(Config.getDefaultPollingDuration())
                .ignoring(NoSuchElementException.class);
    }

    public static Waits getInstance() {
        if (Objects.isNull(INSTANCE))
            INSTANCE = new Waits();

        return INSTANCE;
    }

    public static void setINSTANCE(WebDriver driver) {
        INSTANCE = new Waits(driver);
    }


    public Boolean forStalenessOfElementLocatedBy(WebElement element) {
        return wait.until(ExpectedConditions.stalenessOf(element));
    }

    public WebElement forPresenceOfElementLocatedBy(By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public Boolean forPresenceOfElementLocatedByBoolean(By element) {
        try {
            forPresenceOfElementLocatedBy(element);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public List<WebElement> forPresenceOfAllElementLocatedBy(By element) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public Boolean forPresenceOfElementWithText(WebElement element, String elementText) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, elementText));
    }

    public Boolean forPresenceOfElementWithTextToBe(By element, String elementText) {
        return wait.until(ExpectedConditions.textToBe(element, elementText));
    }

    public Boolean forUrlToContain(String Url) {
        return wait.until(ExpectedConditions.urlContains(Url));
    }

    public Boolean forUrMatches(String Url) {
        return wait.until(ExpectedConditions.urlMatches(Url));
    }

    public WebElement forVisibilityOfElementLocatedBy(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public List<WebElement> forVisibilityOfElementsLocatedBy(By element) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public Boolean waitForExpectedConditionsOr(ExpectedCondition<?>... conditions) {
        return wait.until(ExpectedConditions.or(conditions));
    }

    public Boolean waitForExpectedConditionsAnd(ExpectedCondition<?>... conditions) {
        return wait.until(ExpectedConditions.and(conditions));
    }

    public WebElement forElementToBeClickable(By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement forElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean forInVisibilityOfElementLocatedBy(By element) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public WebDriver forIFrameToLoadAndSwitchToIt(By element) {
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public List<WebElement> forNumberOfElementsToBe(By element, int size) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(element, size));
    }

    public Boolean attributeContains(By element, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public Boolean awaitForElementToBeDisplayed(By element, int seconds) {
        try {
            waitForElementVisibilityByCss(element, Duration.ofSeconds(seconds));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForElementVisibilityByCss(By element, Duration ofSeconds) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public Boolean awaitForPageToLoad() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void forSometime(long seconds) {

        try {
            Thread.sleep(seconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
