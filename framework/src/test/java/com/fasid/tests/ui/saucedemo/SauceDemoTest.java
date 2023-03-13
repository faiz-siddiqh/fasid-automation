package com.fasid.tests.ui.saucedemo;

import com.fasid.groups.TestGroups;
import com.fasid.tests.ui.AbstractUITest;
import com.fasid.tests.ui.saucedemo.actions.SauceDemoActions;
import org.testng.annotations.Test;

public class SauceDemoTest extends AbstractUITest {

    /**
     * Test add to cart functionality.
     */
    @Test(testName = "testAddToCart functionality", description = "Test adding a product to cart", dependsOnMethods = "testLogin",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testAddToCart() {
        new SauceDemoActions()
                .verifyAddToCart();
    }

    /**
     * Test checkout page step 1.
     */
    @Test(description = "Test checkout page step 1.", dependsOnMethods = "testAddToCart",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testCheckoutStep1() {

        new SauceDemoActions()
                .verifyCheckoutStep1();
    }

    /**
     * Test checkout page step 2.
     */
    @Test(description = "Test checkout page step 2.", dependsOnMethods = "testCheckoutStep1",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testCheckoutStep2() {

        new SauceDemoActions()
                .verifyCheckoutStep2();
    }

    /**
     * Test login functionality.
     */
    @Test(description = "Test login functionality",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testLogin() {

        new SauceDemoActions()
                .verifyLogin("standard_user", "secret_sauce");
    }

    /**
     * Test checkout page step 2.
     */
    @Test(description = "Test Sign out.", dependsOnMethods = "testCheckoutStep2",groups = TestGroups.FRAMEWORK_UNITTEST)
    public void testSignOut() {

        new SauceDemoActions()
                .verifySignOut();
    }


}
