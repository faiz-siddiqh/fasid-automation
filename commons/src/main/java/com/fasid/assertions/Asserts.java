package com.fasid.assertions;

import java.util.List;

import org.testng.Assert;

public class Asserts {

    private Asserts() {
        //private constructor to avoid instantiation
    }

    public static void assertTrue(final boolean condition,final String failureMessage) {
        Assert.assertTrue(condition, failureMessage);
    }

    public static void assertFalse(final boolean condition, final String failureMessage) {
        Assert.assertFalse(condition, failureMessage);
    }

    public static void assertEquals(final Object actualValue,final Object expectedValue,final String failureMessage) {
        Assert.assertEquals(actualValue, expectedValue, failureMessage);
    }

    public static void assertIsNull(final Object object,final String failureMessage) {
        Assert.assertNull(object, failureMessage);
    }

    public static void assertIsNotNull(final Object object,final String failureMessage) {
        Assert.assertNull(object, failureMessage);
    }

    public static void assertIsEmpty(final List<?> object,final String failureMessage) {
        Assert.assertTrue(object.isEmpty(), failureMessage);
    }

    public static void fail(final String failureMessage) {
        Assert.fail(failureMessage);
    }

    public static void pass() {
        Assert.assertTrue(true);
    }


}
