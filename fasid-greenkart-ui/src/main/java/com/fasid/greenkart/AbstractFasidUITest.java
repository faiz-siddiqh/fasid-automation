package com.fasid.greenkart;

import com.fasid.core.ui.BrowserInit;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AbstractFasidUITest extends BrowserInit {

    @BeforeMethod(alwaysRun = true)
    public void initReports(Method method, Object[] parameters) {

        Test testAnnotation = method.getAnnotation(Test.class);

        //Handle reporting and adding parmaters

    }

    @AfterMethod
    public void flushReports(ITestResult result) {

        //Handle reporting and flushing reports
    }


}
