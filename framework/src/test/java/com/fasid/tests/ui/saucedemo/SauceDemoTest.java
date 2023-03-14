package com.fasid.tests.ui.saucedemo;

import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import com.fasid.tests.ui.saucedemo.actions.SauceDemoActions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SauceDemoTest extends AbstractUITest {

    /**
     * Test login functionality.
     */
    @BeforeClass(alwaysRun = true,description = "Test login functionality")
    public void login() {

        new SauceDemoActions()
                .verifyLogin("standard_user", "secret_sauce");
    }

    /**
     * Test add to cart functionality.
     */
    @Test(testName = "testAddToCart functionality", description = "Test adding a product to cart", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testAddToCart() {
        new SauceDemoActions()
                .verifyAddToCart();
    }

    /**
     * Test checkout page step 1.
     */
    @Test(description = "Test checkout page step 1.", dependsOnMethods = "testAddToCart", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testCheckoutStep1() {

        new SauceDemoActions()
                .verifyCheckoutStep1();
    }

    /**
     * Test checkout page step 2.
     */
    @Test(description = "Test checkout page step 2.", dependsOnMethods = "testCheckoutStep1", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testCheckoutStep2() {

        new SauceDemoActions()
                .verifyCheckoutStep2();
    }

    /**
     * Test checkout page step 2.
     */
    @Test(description = "Test Sign out.", dependsOnMethods = "testCheckoutStep2", groups = {TestGroups.FRAMEWORK_UNIT_TEST, TestGroups.TESTS})
    public void testSignOut() {

        new SauceDemoActions()
                .verifySignOut();
    }

}
