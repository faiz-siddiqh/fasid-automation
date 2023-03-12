package com.fasid.tests.ui.theinternet;

import com.fasid.assertions.Asserts;
import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.acceptAlert;
import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.ElementActions.verifyTextOf;
import static com.fasid.core.ui.actions.MouseActions.*;
import static com.fasid.tests.ui.theinternet.pages.DoubleClickPage.doubleClickPage;


/**
 * Drag and drop test.
 */
public class DoubleClickTest extends AbstractUITest {

    private static final String URL = "https://webdriveruniversity.com/Actions/index.html";

    @BeforeClass(description = "Setup test classes")
    public void setUpClass() {
        navigate().navigateTo(URL);
    }

    /**
     * Test click and hold method.
     */
    @Test(description = "Verify Click and Hold method",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testClickAndHold() {
        clickAndHold(doubleClickPage().getClickHold());
        verifyTextOf(doubleClickPage().getClickHold(),"Well done! keep holding that click now.....");
    }

    /**
     * Double click test.
     */
    @Test(description = "Double click test",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testDoubleClick() {
        doubleClickOn(doubleClickPage().getDoubleClick());
    }

    /**
     * Test hover and click method.
     */
    @Test(description = "Verify Click and Hold method",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testHoverAndClick() {
        hoverOver(doubleClickPage().getHoverButton());
        clickOn(doubleClickPage().getHoverMenu());
        Asserts.assertEquals(acceptAlert(), "Well done you clicked on the link!", "Error !! Please check the values");
    }
}
