package tutorialsninja_Listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tutorialsninja_Utils.EmailUtils;
import tutorialsninja_Utils.ExtentReporter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Listener implements ITestListener {

    public static ExtentReports extentReport;
    public static ExtentTest extentTest;
    Page page;

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt;
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    @Override
    public void onStart(ITestContext context) {
        extentReport = ExtentReporter.generateExtentReport();
    }

    /**
     * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + "Started Executing");
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.INFO, result.getName() + "Got Executed Successfully");
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Screenshot Taken");
        extentTest.log(Status.FAIL, "test failed" + result.getThrowable());
        try {
            Object testClassInstance = result.getInstance();
            Field field = testClassInstance.getClass().getDeclaredField("page");
            field.setAccessible(true);
            page = (Page) field.get(testClassInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (page != null) {
            String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";
            String dir = System.getProperty("user.dir") + File.separator + "screenshot";
            Path path = Paths.get(dir, fileName);
            String relativePath = "../screenshot/" + fileName;
            try {
                Files.createDirectories(path.getParent());
                page.screenshot(new Page.ScreenshotOptions().setPath(path).setFullPage(true));
                extentTest.addScreenCaptureFromPath(relativePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Screenshot not taken");
        }
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.INFO, result.getThrowable());
        extentTest.log(Status.INFO, result.getName() + "Got Skipped");
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
        File extentReportFile = new File(ExtentReporter.getReportFilePath());
        try {
            if (extentReportFile.exists()) {
//              Open the report in browser
                Desktop.getDesktop().browse(extentReportFile.toURI());
                System.out.println("opened report" + extentReportFile.getAbsolutePath());

//                Send the report via email
                EmailUtils.sendReport(extentReportFile.getAbsolutePath());
                System.out.println("Email sent successfully with the latest report");
            } else {
                System.out.println("Report is not found" + extentReportFile.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
