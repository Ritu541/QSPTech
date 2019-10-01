package com.ProjectName.ModuleName.testCases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ProjectName.ModuleName.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class LoginToApp2 extends TestBase {
	
	/*@Test (dataProvider= "Data_New")
	public void login(Hashtable<String, String > testData) 
	{		
		test = extent.startTest("login");
		
		driver.findElement(By.xpath(OR.getProperty("Expath1"))).sendKeys(testData.get("Email_ID"));
		test.log(LogStatus.PASS, "email add has been entered");
		
		driver.findElement(By.xpath(OR.getProperty("nxtbutton1"))).click();
		test.log(LogStatus.PASS, "next button has been entered");
	}*/
	
	@Test
	public void login()
	{
		
		driver.findElement(By.xpath(OR.getProperty("Expath1"))).sendKeys("praveenjaldawar");
	}
}
