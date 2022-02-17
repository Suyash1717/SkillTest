package com.suyash.test;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pageEvents.LoginPageEvents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class baseTest {
	
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	String errorMessage = null;
	String parameter = null;
	
	@BeforeTest
	public void beforeTestMethod() {
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+File.separator+"reports"+File.separator+"AutomationReports.html" );
		htmlReporter.config().setEncoding("uft-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tets", "Suyash");
	}
	
	@BeforeMethod
	@Parameters({"browserName","ParameterNValue","inputsheet"})
	public void beforeMethodMethod(String browserName, Method testMethod,String ParameterNValue, String inputsheet ) {
		
		logger = extent.createTest(testMethod.getName());
		parameter = ParameterNValue;
		setupDriver(browserName);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		}
		
	public List<Integer> getRowArray(String strDTParametersNValues, String splitType) throws Exception {
		try {
			int startRow = 0, endRow = 0;
			List<Integer> rowList = new ArrayList<Integer>();
			if (strDTParametersNValues.contains(",")) {
				String[] totalRows = strDTParametersNValues.replace(splitType, "").split(",");
				for (int count = 0; count < totalRows.length; count++) {
					if (totalRows[count].contains("-")) {
						startRow = Integer.parseInt(totalRows[count].split("-")[0]);
						endRow = Integer.parseInt(totalRows[count].split("-")[1]);
						rowSaperateByHyphen(startRow, endRow, count, rowList);
					} else {
						startRow = Integer.parseInt(totalRows[count]);
						rowList.add(startRow);
					}
				}
				return rowList;
			} else {
				startRow = Integer.parseInt((strDTParametersNValues.replace(splitType, "").split("-"))[0]);
				endRow = Integer.parseInt((strDTParametersNValues.replace(splitType, "").split("-"))[1]);
				rowSaperateByHyphen(startRow, endRow, 0, rowList);
				return rowList;
			}
		} catch (Exception objException) {
			
			return null;
		}
	}

	private void rowSaperateByHyphen(int startRow, int endRow, int index, List<Integer> arrli) {

		for (int count = startRow; count <= endRow; count++) {
			arrli.add(count);
		}
	}
	
	public boolean launchURL(String url) {
		
		try {
			
			driver.manage().deleteAllCookies();
			driver.get(url);
			return true;
			
		} catch(Exception e) {
		
		return false;
		}
		
	}
	
	
	
	@AfterMethod
	public void afterMethodMethod(ITestResult result) {
		if(result.getStatus()==ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+methodName+" Passed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		} else if(result.getStatus()==ITestResult.FAILURE) {
			
			String methodName = result.getMethod().getMethodName();
			String logText = "Test Case: "+methodName+" Failed";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
			
		}
		
		driver.quit();
	}
	
	@AfterTest
	public void afterTestMethod() {
		
		extent.flush();
	}
	
	public void setupDriver(String browserName) {
		
	try {
		if(browserName.equalsIgnoreCase("chrome")) {
			String a = (System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"chromedriver");
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"chromedriver.exe");
			driver = new ChromeDriver();
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"geckodriver");
			driver = new FirefoxDriver();
			
		} else {
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+File.separator+"drivers"+File.separator+"chromedriver");
			driver = new ChromeDriver();
			
		}
		
	} catch (Exception e) {
        // generic exception handling
        e.printStackTrace();
}
		
	}
	 

	
	
}
