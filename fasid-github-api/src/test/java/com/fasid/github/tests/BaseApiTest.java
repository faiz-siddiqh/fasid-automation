package com.fasid.github.tests;

import com.fasid.config.Config;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Base Class for All API tests in this module
 */
public class BaseApiTest {

    private static String GITHUB_SCHEMAS_PATH = System.getProperty("user.dir") + "/fasid-github-api/src/test/resources/schemas/";

    @BeforeSuite(alwaysRun = true)
    public void init() throws ConfigurationException {
        Config.getInstance();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(ITestContext testContext) {
        //Add support for logs and extent report publish and flush
    }

    protected static String getSchema(String schemaName) {
        return GITHUB_SCHEMAS_PATH + schemaName;
    }

}
