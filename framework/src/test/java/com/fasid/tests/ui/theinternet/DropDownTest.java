package com.fasid.tests.ui.theinternet;

import com.fasid.assertions.Asserts;
import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.fasid.core.ui.actions.DriverActions.navigate;
import static com.fasid.core.ui.actions.DropDownActions.*;
import static com.fasid.tests.ui.theinternet.pages.DropDownPage.dropDownPage;

/**
 * DropDown test.
 *
 * @author Wasiq Bhamla
 * @since 30-Jul-2022
 */
public class DropDownTest extends AbstractUITest {
    private static final String URL = "https://letcode.in/dropdowns";

    @BeforeClass(description = "Setup test classes")
    public void setUpClass() {
        navigate().navigateTo(URL);
    }

    /**
     * Test dropdown deselect all.
     */
    @Test(description = "Verify deselect all dropdown values",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testDeselectAll() {
        selectByIndex(dropDownPage().getSuperHeroes(), 3);
        selectByIndex(dropDownPage().getSuperHeroes(), 4);
        deselectAll(dropDownPage().getSuperHeroes());
        Asserts.assertTrue(getSelectedItems(dropDownPage().getSuperHeroes()).isEmpty(), "Error !! Expected True");
    }

//    /**
//     * Test dropdown deselect by index.
//     */
//    @Test(description = "Verify deselect dropdown value by index")
//    public void testDeselectByIndex() {
//        selectByIndex(dropDownPage().getSuperHeroes(), 4);
//        Asserts.assertEquals(getSelectedItem(dropDownPage().getSuperHeroes()), "Batwoman", "Error !!  Values don't match");
//        deselectByIndex(dropDownPage().getSuperHeroes(), 4);
//        Asserts.assertTrue(getSelectedItem(dropDownPage().getSuperHeroes()).isEmpty(), "Error !! Expected True");
//    }
//
//    /**
//     * Test dropdown deselect by text.
//     */
//    @Test(description = "Verify deselect dropdown value by text")
//    public void testDeselectByText() {
//        selectByText(dropDownPage().getSuperHeroes(), "Aquaman");
//        Asserts.assertEquals(getSelectedItem(dropDownPage().getSuperHeroes()), "Aquaman", "Error !! Values don't match");
//        deselectByText(dropDownPage().getSuperHeroes(), "Aquaman");
//        Asserts.assertTrue(getSelectedItem(dropDownPage().getSuperHeroes()).isEmpty(), "Error !! Expected True");
//    }
//
//    /**
//     * Test dropdown deselect by value.
//     */
//    @Test(description = "Verify deselect dropdown value by value")
//    public void testDeselectByValue() {
//        selectByValue(dropDownPage().getSuperHeroes(), "bt");
//        Asserts.assertEquals(getSelectedItem(dropDownPage().getSuperHeroes()), "Batman", "Error !!  Values don't match");
//        deselectByValue(dropDownPage().getSuperHeroes(), "bt");
//        Asserts.assertTrue(getSelectedItem(dropDownPage().getSuperHeroes()).isEmpty(), "Error !! Expected True");
//    }

    /**
     * Test multiple select.
     */
    @Test(description = "Verify multi select dropdown values",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testMultiSelect() {
        selectByValue(dropDownPage().getSuperHeroes(), "ta");
        selectByIndex(dropDownPage().getSuperHeroes(), 3);
        selectByText(dropDownPage().getSuperHeroes(), "Black Panther");
    }

    /**
     * Test select by index.
     */
    @Test(description = "Verify select dropdown value by index",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testSelectByIndex() {
        selectByIndex(dropDownPage().getFruits(), 3);
        Asserts.assertEquals(getSelectedItem(dropDownPage().getFruits()), "Orange", "Error !!  Values don't match");
    }

    /**
     * Test select by text.
     */
    @Test(description = "Verify select dropdown value by text",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testSelectByText() {
        selectByText(dropDownPage().getFruits(), "Apple");
        Asserts.assertEquals(getSelectedItem(dropDownPage().getFruits()), "Apple", "Error !!  Values don't match");
    }

    /**
     * Test select by value.
     */
    @Test(description = "Verify select dropdown value by value",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testSelectByValue() {
        selectByValue(dropDownPage().getFruits(), "1");
        Asserts.assertEquals(getSelectedItem(dropDownPage().getFruits()), "Mango", "Error !! Values don't match");
    }
}
