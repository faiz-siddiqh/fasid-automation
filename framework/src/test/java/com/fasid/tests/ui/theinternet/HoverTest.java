package com.fasid.tests.ui.theinternet;

import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.ElementActions.verifyTextOf;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static com.fasid.core.ui.actions.MouseActions.hoverOver;
import static com.fasid.tests.ui.theinternet.pages.HomePage.homePage;
import static com.fasid.tests.ui.theinternet.pages.HoverPage.hoverPage;
import static java.text.MessageFormat.format;

/**
 * Hover mouse test
 */
public class HoverTest extends AbstractUITest {
    private static final String URL = "https://the-internet.herokuapp.com/";

    @BeforeClass(description = "Setup test classes")
    public void setUpClass() {
        navigate().navigateTo(URL);
        clickOn(homePage().link("Hovers"));
    }

    /**
     * Hover mouse test.
     */
    @Test(description = "Hover mouse test",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testMouseHover() {
        final var expectedUserName = "name: user1";
        hoverOver(hoverPage().userImage(0));
        verifyTextOf(hoverPage().userName(0), format(expectedUserName, 1));
    }
}
