package com.fasid.assertions;

import org.testng.asserts.SoftAssert;

import java.util.List;

public class SoftAsserts {

    private static SoftAssert softAsserts;

    public static void initInstance() {
        if (softAsserts == null)
            softAsserts = new SoftAssert();
    }

    public static void assertTrue(boolean condition, String failureMessage) {
        softAsserts.assertTrue(condition, failureMessage);
    }

    public static void assertFalse(boolean condition, String failureMessage) {
        softAsserts.assertFalse(condition, failureMessage);
    }

    public static void assertEquals(Object actualValue, Object expectedValue, String failureMessage) {
        softAsserts.assertEquals(actualValue, expectedValue, failureMessage);
    }

    public static void assertIsNull(Object object, String failureMessage) {
        softAsserts.assertNull(object, failureMessage);
    }


    public static void assertIsNotNull(Object object, String failureMessage) {
        softAsserts.assertNull(object, failureMessage);
    }

    public static void assertIsEmpty(List<?> object, String failureMessage) {
        softAsserts.assertTrue(object.isEmpty(), failureMessage);
    }

    public static void assertAll() {
        softAsserts.assertAll();
    }

}
