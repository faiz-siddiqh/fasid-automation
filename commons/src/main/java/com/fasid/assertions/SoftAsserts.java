package com.fasid.assertions;

import java.util.List;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {

    private static SoftAssert softAsserts;

    private SoftAsserts() {
        //private constructor to avoid instantiation
    }

    public static void initInstance() {
        if (softAsserts == null) {
            softAsserts = new SoftAssert();
        }
    }

    public static void assertTrue(final boolean condition,final String failureMessage) {
        softAsserts.assertTrue(condition, failureMessage);
    }

    public static void assertFalse(final boolean condition,final String failureMessage) {
        softAsserts.assertFalse(condition, failureMessage);
    }

    public static void assertEquals(final Object actualValue,final Object expectedValue,final String failureMessage) {
        softAsserts.assertEquals(actualValue, expectedValue, failureMessage);
    }

    public static void assertIsNull(final Object object,final String failureMessage) {
        softAsserts.assertNull(object, failureMessage);
    }

    public static void assertIsNotNull(final Object object,final String failureMessage) {
        softAsserts.assertNull(object, failureMessage);
    }

    public static void assertIsEmpty(final List<?> object,final String failureMessage) {
        softAsserts.assertTrue(object.isEmpty(), failureMessage);
    }

    public static void assertAll() {
        softAsserts.assertAll();
    }

}
