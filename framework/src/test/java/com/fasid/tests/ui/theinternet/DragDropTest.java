package com.fasid.tests.ui.theinternet;

import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.ElementActions.verifyTextOf;
import static com.fasid.core.ui.actions.MouseActions.dragAndDrop;
import static com.fasid.tests.ui.theinternet.pages.DragDropPage.dragDropPage;

/**
 * Drag and drop test.
 */
public class DragDropTest extends AbstractUITest {

    @BeforeClass(alwaysRun = true,description = "Setup test classes")
    public void setUpClass() {

        final String URL = "https://webdriveruniversity.com/Actions/index.html";
        navigate().navigateTo(URL);
    }

    /**
     * Drag and Drop test.
     */
    @Test(description = "Drag Drop test", groups = TestGroups.FRAMEWORK_UNIT_TEST)
    public void testDragDrop() {
        dragAndDrop(dragDropPage().getDraggable(), dragDropPage().getDroppable());
        verifyTextOf(dragDropPage().getHeader(), "Dropped!");
    }
}
