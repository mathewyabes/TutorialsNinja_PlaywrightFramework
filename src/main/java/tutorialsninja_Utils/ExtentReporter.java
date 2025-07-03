package tutorialsninja_Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter {

    public static ExtentReports extentReport = ExtentReporter.generateExtentReport(); // connect it!
    public static ExtentTest extentTest;
    private static String reportFilePath;

    public static ExtentReports generateExtentReport() {
        ExtentReports extentReport = new ExtentReports();
        String timeStamp = new SimpleDateFormat("dd_MM_yyyy HH_mm_ss").format(new Date());
        reportFilePath = System.getProperty("user.dir") + "\\test-output\\ExtentReport\\extentReport" + timeStamp + ".html";
        File extentReportFile = new File(reportFilePath);
        extentReportFile.getParentFile().mkdir();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("TutorialsNinja Test Report");
        sparkReporter.config().setDocumentTitle("TN Automation Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");

        extentReport.attachReporter(sparkReporter);

        extentReport.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReport.setSystemInfo("User name", System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extentReport;
    }

    public static String getReportFilePath() {
        return reportFilePath;
    }
}
