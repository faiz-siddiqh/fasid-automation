package com.fasid.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

public class TestLogger {
    private static final Logger log = LoggerFactory.getLogger(TestLogger.class);
    public static final String TEST_NAME = "testname";


    public static void start(String testname) {
        MDC.put(TEST_NAME, testname);
    }

    public static void log(String logMessage) {
        log.info(logMessage);
    }

    public static void log(String date, Object logMessage) {
        log.info(date, logMessage);
    }

    public static void stop() {
        MDC.remove(TEST_NAME);
    }

}
