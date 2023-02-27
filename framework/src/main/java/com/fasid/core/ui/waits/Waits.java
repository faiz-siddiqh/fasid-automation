package com.fasid.core.ui.waits;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import com.fasid.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import static com.fasid.core.ui.BrowserInit.getDriver;

public class Waits {

    private static Waits INSTANCE;
    private Wait<WebDriver> wait;

    public Waits(final WebDriver driver) {
        wait = new FluentWait<>(getDriver())
                .withTimeout(Config.getDefaultWaitDuration())
                .pollingEvery(Config.getDefaultPollingDuration())
                .ignoring(NoSuchElementException.class);
    }

    public Waits(final WebDriver driver,final Duration seconds,final Duration pollingSeconds) {
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

    public static void setINSTANCE(final WebDriver driver) {
        INSTANCE = new Waits(driver);
    }

    public Boolean forStalenessOfElementLocatedBy(final WebElement element) {
        return wait.until(ExpectedConditions.stalenessOf(element));
    }

    public WebElement forPresenceOfElementLocatedBy(final By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public Boolean forPresenceOfElementLocatedByBoolean(final By element) {
        try {
            forPresenceOfElementLocatedBy(element);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public List<WebElement> forPresenceOfAllElementLocatedBy(final By element) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
    }

    public Boolean forPresenceOfElementWithText(final WebElement element,final String elementText) {
        return wait.until(ExpectedConditions.textToBePresentInElement(element, elementText));
    }

    public Boolean forPresenceOfElementWithTextToBe(final By element, final String elementText) {
        return wait.until(ExpectedConditions.textToBe(element, elementText));
    }

    public Boolean forUrlToContain(final String Url) {
        return wait.until(ExpectedConditions.urlContains(Url));
    }

    public Boolean forUrMatches(final String Url) {
        return wait.until(ExpectedConditions.urlMatches(Url));
    }

    public WebElement forVisibilityOfElementLocatedBy(final By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public List<WebElement> forVisibilityOfElementsLocatedBy(final By element) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
    }

    public Boolean waitForExpectedConditionsOr(final ExpectedCondition<?>... conditions) {
        return wait.until(ExpectedConditions.or(conditions));
    }

    public Boolean waitForExpectedConditionsAnd(final ExpectedCondition<?>... conditions) {
        return wait.until(ExpectedConditions.and(conditions));
    }

    public WebElement forElementToBeClickable(final By element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement forElementToBeClickable(final WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Boolean forInVisibilityOfElementLocatedBy(final By element) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public WebDriver forIFrameToLoadAndSwitchToIt(final By element) {
        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public List<WebElement> forNumberOfElementsToBe(final By element,final int size) {
        return wait.until(ExpectedConditions.numberOfElementsToBe(element, size));
    }

    public Boolean attributeContains(final By element, final String attribute, final String value) {
        return wait.until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    public Boolean awaitForElementToBeDisplayed(final By element,final int seconds) {
        try {
            waitForElementVisibilityByCss(element, Duration.ofSeconds(seconds));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForElementVisibilityByCss(final By element,final Duration ofSeconds) {
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
