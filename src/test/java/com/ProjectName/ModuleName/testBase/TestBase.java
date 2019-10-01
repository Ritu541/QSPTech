package com.ProjectName.ModuleName.testBase;

import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.AutomationWorldByRahul.SeleniumTraining.DataCollection;
import com.AutomationWorldByRahul.SeleniumTraining.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public static ExcelReader Test3=new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\Master_Sheet.xlsx");
	
//	@BeforeSuite
	@BeforeMethod
	public void loadingFiles() throws IOException {
		
			config = new Properties();
			FileInputStream Test1=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\config.properties");
			config.load(Test1);
			System.out.println("Config has been loaded");
			
			OR = new Properties();	
			FileInputStream Test2=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\or.properties");
			OR.load(Test2);
			System.out.println("OR has been loaded");
			
			String timestamp = new SimpleDateFormat("yy.MM.DD.mm.ss").format(new Date());
			extent = new ExtentReports(System.getProperty("user.dir" +"\\src\\test\\resources\\ExecutionReport\\abc_"+timestamp+".html"));
			
			if (config.getProperty("browser").equalsIgnoreCase("chrome"))
				{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				}
				else if (config.getProperty("browser").equalsIgnoreCase("firefox"))
				{
				System.setProperty("webdriver.geckodriver.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				}
				else if(config.getProperty("browser").equalsIgnoreCase("IE")) 
				{
				System.setProperty("webdriver.IEDriverServer.driver", System.getProperty("user.dir")+"//src\\test\\resources\\drivers\\IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
			
				driver.get(config.getProperty("ApplicationURL"));
				System.out.println("aapplication has been launched");
				driver.manage().window().maximize();

				driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
	}
	
	@DataProvider 	
	public static Object[][] Data_New() {
	DataCollection sample = new DataCollection(Test3, "Test_Data", "login");
			return sample.dataArray();	
	}
	
	
	@AfterSuite
	public static void closesuite () {
		extent.endTest(test);
		extent.flush();
	}
}