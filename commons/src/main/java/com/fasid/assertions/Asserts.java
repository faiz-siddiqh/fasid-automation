package com.fasid.assertions;

import org.testng.Assert;

import java.util.List;

public class Asserts {

    public static void assertTrue(boolean condition, String failureMessage) {
        Assert.assertTrue(condition, failureMessage);
    }

    public static void assertFalse(boolean condition, String failureMessage) {
        Assert.assertFalse(condition, failureMessage);
    }

    public static void assertEquals(Object actualValue, Object expectedValue, String failureMessage) {
        Assert.assertEquals(actualValue, expectedValue, failureMessage);
    }

    public static void assertIsNull(Object object, String failureMessage) {
        Assert.assertNull(object, failureMessage);
    }


    public static void assertIsNotNull(Object object, String failureMessage) {
        Assert.assertNull(object, failureMessage);
    }

    public static void assertIsEmpty(List<?> object, String failureMessage) {
        Assert.assertTrue(object.isEmpty(), failureMessage);
    }

    public static void fail(String failureMessage) {
        Assert.fail(failureMessage);
    }

    public static void pass() {
        Assert.assertTrue(true);
    }


}
