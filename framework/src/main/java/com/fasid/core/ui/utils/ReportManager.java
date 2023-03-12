package com.fasid.core.ui.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasid.config.Config;
import com.fasid.utils.FileUtils;
import org.testng.ITestResult;;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static com.fasid.core.ui.actions.DriverActions.takeScreenshot;
import static java.text.MessageFormat.format;

public class ReportManager {

    private static String basePath = System.getProperty("user.dir") + "/build/test-logs";
    private static ExtentReports extentReport;

    private static ReportManager INSTANCE;

    private static ExtentTest extentTest;

    private ReportManager() {
        //Utility class should be private - SINGLETON pattern
    }

    public static ReportManager getReportManager() {
        if (INSTANCE == null) {
            INSTANCE = new ReportManager();
        }
        return INSTANCE;
    }

    public void createExtentReportInstance(final String suiteName) {

        try {
            String path = basePath + "//" + suiteName;
            final File suiteFolderName = new File(path);
            if (suiteFolderName.exists()) {
                FileUtils.deleteFile(path);
            }
            suiteFolderName.mkdir();
            final String reportName = suiteName + " ExtentReport"; //customizable
            extentReport = new ExtentReports();
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path + "//" + "ExtentReport.html");
            htmlReporter.config().setDocumentTitle(suiteName + Config.getEnv());
            htmlReporter.config().setReportName(reportName);
            htmlReporter.config().setTheme(Theme.DARK);
            extentReport.attachReporter(htmlReporter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ExtentReports getExtentReport() {
        if (Objects.nonNull(extentReport)) {
            return extentReport;
        } else {
            throw new RuntimeException("EXTENT REPORT NOT CREATED");
        }
    }

    public void initExtentTest(String testName) {
        try {
            if (Objects.nonNull(extentReport)) {
                extentTest = extentReport.createTest(testName);
                extentTest.log(Status.INFO, "Initializing Extent Test");
                extentTest.log(Status.INFO, format("Initialising test on browser :{}", Config.determineEffectiveDriver()));
            } else {
                System.err.println("Extent report is not available. Skipping test initialization.");
            }

        } catch (Exception e) {

        }

    }

    public void cleanUp(ITestResult result) {

        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                extentTest.log(Status.FAIL, "Test Failed");
                break;
            case ITestResult.SUCCESS:
                final String screenshotPath = captureScreenshot();
                extentTest.log(Status.PASS, "Success", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                break;
            case ITestResult.SKIP:
                extentTest.log(Status.SKIP, "Test Skipped");
                break;
            default:
                extentTest.log(Status.WARNING, "Test result not recognized");
                break;
        }
        getExtentReport().flush();
    }

    /**
     * This method captures and Adds screenshot to the extent-report
     */
    public static void addScreenshot() {
        String screenshotPath = captureScreenshot();
        extentTest.addScreenCaptureFromPath(screenshotPath);
    }

    /**
     * This method is used to logInfo into the report
     *
     * @param info
     */
    public static void logInfo(final String info) {
        if (Objects.nonNull(extentTest)) {
            extentTest.log(Status.INFO, info);
        }
    }


    private static String captureScreenshot() {
        final var date = new SimpleDateFormat("yyyyMMdd-HHmmss");
        final var timestamp = date.format(Calendar.getInstance().getTime());
        final var fileName = "%s/%s-%s.%s";
        final var prefix = "SHOT";
        final var extension = "png";
        final var path = basePath + "/screenshots";
        String destination = String.format(fileName, path, prefix, timestamp, extension);

        File screenshot = takeScreenshot();
        if (screenshot.exists()) {
            final File screenshotFile = new File(destination);
            try {
                org.apache.commons.io.FileUtils.copyFile(screenshot, screenshotFile);
                return screenshotFile.getAbsolutePath();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } else {
            return null;
        }

    }

}
