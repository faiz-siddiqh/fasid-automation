package com.fasid.tests.ui.theinternet;

import com.fasid.assertions.Asserts;
import com.fasid.config.Config;
import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.*;
import static com.fasid.core.ui.actions.ElementActions.verifyTextOf;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static com.fasid.tests.ui.theinternet.pages.AlertPage.alertPage;
import static com.fasid.tests.ui.theinternet.pages.HomePage.homePage;

public class AlertTest extends AbstractUITest {

    @BeforeClass(description = "Setup test classes", alwaysRun = true)
    public void setUpClass() {
        navigate().navigateTo(Config.getAppUrl());
        clickOn(homePage().link("JavaScript Alerts"));
    }

    /**
     * This will test Accept alert button action.
     */
    @Test(description = "Tests Accept alert", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testAcceptAlert() {
        clickOn(alertPage().getAlertButton());
        Asserts.assertEquals(acceptAlert(), "I am a JS Alert", "Error !! Please check the values");
        verifyTextOf(alertPage().getResult(), "You successfully clicked an alert");
    }

    /**
     * This will test accept confirm button action.
     */
    @Test(description = "Tests Accept confirm alert", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testAcceptConfirmAlert() {
        clickOn(alertPage().getConfirmButton());
        Asserts.assertEquals(acceptAlert(), "I am a JS Confirm", "Error !! Please check the values");
        verifyTextOf(alertPage().getResult(), "You clicked: Ok");
    }

    /**
     * This will test dismiss confirm button action.
     */
    @Test(description = "Tests Dismiss confirm alert", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testDismissConfirmAlert() {
        clickOn(alertPage().getConfirmButton());
        Asserts.assertEquals(dismissAlert(), "I am a JS Confirm", "Error !! Please check the values");
        verifyTextOf(alertPage().getResult(), "You clicked: Cancel");
    }

    /**
     * This will test dismiss confirm button action.
     */
    @Test(description = "Tests Dismiss prompt alert", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testDismissPromptAlert() {
        clickOn(alertPage().getPromptButton());
        Asserts.assertEquals(acceptAlertAndEnterText("Fasid"), "I am a JS prompt", "Error !! Please check the values");
        verifyTextOf(alertPage().getResult(), "You entered: Fasid");
    }


}
