package com.fasid.tests.ui.theinternet;

import com.fasid.assertions.Asserts;
import com.fasid.config.Config;
import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.ElementActions.isSelected;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static com.fasid.tests.ui.theinternet.pages.CheckboxPage.checkboxPage;

public class CheckboxTest extends AbstractUITest {

    @BeforeClass(alwaysRun = true, description = "Setup test classes")
    public void setUpClass() {
        navigate().navigateTo(Config.getAppUrl() + "checkboxes");
    }

    /**
     * Test select checkbox.
     */
    @Test(description = "Verify select checkbox", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testCheckOption() {
        Asserts.assertFalse(isSelected(checkboxPage().getOption1()), "Error !! Expected False ");
        Asserts.assertTrue(isSelected(checkboxPage().getOption2()), "Error !! Expected True ");
        clickOn(checkboxPage().getOption1());
        Asserts.assertTrue(isSelected(checkboxPage().getOption1()), "Error !! Expected True ");
    }
}
