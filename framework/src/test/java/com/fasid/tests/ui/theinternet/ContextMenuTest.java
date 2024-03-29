package com.fasid.tests.ui.theinternet;

import com.fasid.assertions.Asserts;
import com.fasid.config.Config;
import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.acceptAlert;
import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static com.fasid.core.ui.actions.MouseActions.rightClickOn;
import static com.fasid.tests.ui.theinternet.pages.ContextMenuPage.contextMenuPage;
import static com.fasid.tests.ui.theinternet.pages.HomePage.homePage;

/**
 * Context menu test.
 */
public class ContextMenuTest extends AbstractUITest {

    @BeforeClass(alwaysRun = true, description = "Setup test classes")
    public void setUpClass() {
        navigate().navigateTo(Config.getAppUrl());
        clickOn(homePage().link("Context Menu"));
    }

    /**
     * Test context menu.
     */
    @Test(description = "Test context menu", groups = TestGroups.FRAMEWORK_UNIT_TEST)
    public void testContextMenu() {
        rightClickOn(contextMenuPage().getHotSpot());
        Asserts.assertEquals(acceptAlert(), "You selected a context menu", "Error !! Please check the values");
    }
}
