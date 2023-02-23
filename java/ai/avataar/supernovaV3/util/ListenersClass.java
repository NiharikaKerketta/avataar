package ai.avataar.supernovaV3.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ai.avataar.supernovaV3.base.TestBase;


public class ListenersClass extends TestBase implements ITestListener {
	

	FileUtility fLib = new FileUtility();
	ExtentReports report;
	ExtentTest test;

	
	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName()+" is passed");
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			
		//step8: log for failed methods
		String testNAme = result.getMethod().getMethodName();
		test.log(Status.FAIL, result.getMethod().getMethodName()+" is failed");
		test.log(Status.FAIL, result.getThrowable());

		//String date = jLib.getDateAndTime();
		EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
		File srcFile = edriver.getScreenshotAs(OutputType.FILE);
						
		String date = null;
		File dstFile = new File("./screenshot/"+testNAme+"_"+date +".png");
		try {
			test.addScreenCaptureFromPath(dstFile.getAbsolutePath());
		} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
		
		try {
			FileUtils.copyFile(srcFile, dstFile);
		} catch (IOException e) {
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus() == ITestResult.SKIP) {
		//step7:log for skipped methods
		test.log(Status.SKIP, result.getMethod().getMethodName()+" is skipped");
		test.log(Status.SKIP, result.getThrowable());
	}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
		Date date = new Date();
		String fileNameType = date.toString().replace(" ", "_").replace(":","_")+".html";
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String reportFilePath = "extentreports//"+ fileNameType;
		
		
		ExtentSparkReporter spark = new ExtentSparkReporter(reportFilePath);
		
		//ExtentSparkReporter spark = new ExtentSparkReporter("reports//ExtentReport.html"); //removed after adding filepath
		spark.config().setDocumentTitle("Supernova Automation");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Supernova Automation Execution reports");

		//step2:- attaching the configuration to extent report and also System info
		report = new ExtentReports();

		report.attachReporter(spark);

		//write system info
		report.setSystemInfo("ProjectName", "Supernova V3");
		report.setSystemInfo("OS", System.getProperty("os.name"));
		report.setSystemInfo("Browser", prop.getProperty("browser"));
		//report.setSystemInfo("Platform", "WINDOWS");
		report.setSystemInfo("QA Test Engineers", "Manjunath, Nilesh, Niharika");
		
		
		
		try {
			report.setSystemInfo("url",  fLib.getPropertyKeyValue("url"));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}
		
 
}
