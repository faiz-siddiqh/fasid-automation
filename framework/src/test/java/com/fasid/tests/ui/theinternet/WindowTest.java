package com.fasid.tests.ui.theinternet;

import com.fasid.assertions.Asserts;
import com.fasid.core.ui.waits.Waits;
import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.*;
import static com.fasid.core.ui.actions.ElementActions.verifyBrowserUrl;
import static com.fasid.core.ui.actions.ElementActions.verifyTextOf;
import static com.fasid.core.ui.actions.MouseActions.clickOn;
import static com.fasid.tests.ui.theinternet.pages.HomePage.homePage;
import static com.fasid.tests.ui.theinternet.pages.MultiWindowPage.multiWindowPage;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.WindowType.TAB;

/**
 * Browser window specific tests.
 */
public class WindowTest extends AbstractUITest {
    private static final String URL = "https://the-internet.herokuapp.com/";

    @BeforeClass(description = "Setup test classes")
    public void setUpClass() {
        navigate().navigateTo(URL);
        clickOn(homePage().link("Multiple Windows"));
    }

    /**
     * Test case to verify browser back navigation.
     */
    @Test(description = "Test browser back navigation", groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testBackNavigation() {
        navigate().back();
        verifyBrowserUrl(URL);
    }

    /**
     * Test case to verify execute script.
     */
    @Test(description = "Test execute script method", groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testExecuteScript() {
        final String script = "alert('Hello World');";
        executeJsScript(script);
        Asserts.assertEquals(acceptAlert(), "Hello World", "ERROR !! Values dont match");
    }

    /**
     * Test case to verify browser forward navigation.
     */
    @Test(description = "Test browser forward navigation", groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testForwardNavigation() {
        navigate().forward();
        verifyBrowserUrl(format("{0}windows", URL));
    }

    /**
     * Test case to verify opening new tab window.
     */
    @Test(description = "Test to verify opening new tab", groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testOpenNewTab() {
        try {
            switchToNewWindow(TAB);
            title().isEmpty();
            verifyBrowserUrl("about:blank");
        } finally {
            closeWindow();
        }
    }

    /**
     * Test case to verify opening new window.
     */
    @Test(description = "Check New window Open functionality",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testOpenWindow() {
        final var currentWindow = getCurrentHandle();
        clickOn(multiWindowPage().getClickHere());
        final var newWindow = windowHandles().stream()
                .filter(handle -> !handle.equals(currentWindow))
                .findFirst();
        Asserts.assertTrue(newWindow.isPresent(), "Error!! Expected to be True");
        switchToWindow(newWindow.get());
        Waits.getInstance().forUrMatches((format("{0}windows/new", URL)));
        verifyBrowserUrl(format("{0}windows/new", URL));
        verifyTextOf(multiWindowPage().getTitle(), "New Window");
        closeWindow();
        verifyBrowserUrl(format("{0}windows", URL));
    }

    /**
     * Test case to verify browser refresh.
     */
    @Test(description = "Test to verify page refresh", groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testRefreshPage() {
        navigate().refresh();
        verifyBrowserUrl(format("{0}windows", URL));
    }
}
