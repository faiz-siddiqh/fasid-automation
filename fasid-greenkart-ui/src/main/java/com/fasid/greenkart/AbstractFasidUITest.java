package com.fasid.greenkart;

import com.fasid.core.ui.BrowserInit;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import static com.fasid.core.ui.actions.DriverActions.saveLogs;
import static com.fasid.core.ui.utils.ReportManager.getReportManager;


public class AbstractFasidUITest extends BrowserInit {
    @BeforeClass
    public void CreateReporter(ITestContext context) {
        String className = context.getCurrentXmlTest().getClasses().get(0).getName();
        getReportManager().createExtentReportInstance(className);
    }

    @BeforeMethod(alwaysRun = true)
    public void initReports(Method method, Object[] parameters) {

        Test testAnnotation = method.getAnnotation(Test.class);
        getReportManager().initExtentTest(testAnnotation.testName());

    }

    @AfterMethod
    public void flushReports(ITestResult result) {
        saveLogs();
        getReportManager().cleanUp(result);

    }


}
