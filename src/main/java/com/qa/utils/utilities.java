package com.qa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class utilities {
	public static ExtentReports extentReports;

	/*
	 * 
	 * Extent Reports has 2 classes mainly Extent Reports and ExtentSpark Reporter
	 */
	public static ExtentReports generateReporter() {
		/**
		 * Extent Reports
		 */
		// The path where extent reports html will be stored
		String reportPath = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("RSA Academy Smoke Test Report");
		reporter.config().setDocumentTitle("Smoke Test Results");
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Prathamesh D Dhamanaskar", "QA Tester");
		extentReports.setSystemInfo("Machine", "Window 7 OS");
		extentReports.setSystemInfo("Suite Name", "Smoke");
		
		return extentReports;
	}
	
	public static String getScreenshotPath(WebDriver driver,String testcaseName) throws IOException {
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = System.getProperty("user.dir") + "\\reports\\" + testcaseName + ".png";
		FileUtils.copyFile(source, new File(screenshotPath));
		return screenshotPath;
	}
	

}
