package com.fasid;

import com.fasid.config.Config;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseApiTest {

    @BeforeSuite(alwaysRun = true)
    public void init() throws ConfigurationException {
        Config.getInstance();
    }


    @AfterSuite
    public void tearDown() {
        //Handing report flush and closing other files and connections

    }

}
