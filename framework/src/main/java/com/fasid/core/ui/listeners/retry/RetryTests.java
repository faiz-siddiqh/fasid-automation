package com.fasid.core.ui.listeners.retry;

import com.fasid.config.Config;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.logging.Logger;

public class RetryTests implements IRetryAnalyzer {

    private static final Logger logger = Logger.getLogger("Test-Retry-Listener");
    private int retryCount = 0;
    private final int maxRetries = Config.quickDebugMode ? 0 : Integer.parseInt(System.getProperty("RETRIES", "1"));

    @Override
    public boolean retry(ITestResult result) {

        //Handle logging and setting params in reporting
        if (retryCount > maxRetries) {
            ++retryCount;
            return true;
        }

        return false;
    }
}
