package com.vtiger.common;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vtiger.stepdefinitions.BaseClass;

public class PageActions {
	
	private WebDriver driver;
	WebDriverWait wait;
	
	public PageActions(WebDriver driver)
	{
		this.driver=driver;
	    wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	}
	
	
	public String getScreenShot()
	{
		Date d=new Date();
		DateFormat ft=new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName=ft.format(d);
		String path=(System.getProperty("user.dir")+ "/src/test/java/com/vtiger/reports/Screenshot/"+fileName+".png");
		TakesScreenshot ts=((TakesScreenshot)driver);
		File srcFile=ts.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(path);
		//copy file at destination
		try
		{
			FileUtils.copyFile(srcFile,DestFile);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return path;
	}
	
	public void validate_Expected_Actual_Text(String exp,String act,String msg)
	{
		if(exp.equals(act))
		{
			BaseClass.logger.pass(msg);
		}
		else
		{
			BaseClass.logger.fail("comparison failed because expected text is "+exp+" and found "+act+"<a href='"+getScreenShot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	public void setText(WebElement elm,String value,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.clear();
		elm.sendKeys(value);
		BaseClass.logger.pass(msg);
		
		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClass.logger.fail("failed due to"+e.getMessage()+"<a href='"+getScreenShot()+"'><span class='label end-time'>Screenshot</span></a>");
			
			
		}
	}
	
	public void setText(By elm,String value)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(elm)));
		driver.findElement(elm).clear();
		driver.findElement(elm).sendKeys(value);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setText(String elm,String value)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(elm))));
		driver.findElement(By.xpath(elm)).clear();
		driver.findElement(By.xpath(elm)).sendKeys(value);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void ElementExist(WebElement elm,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isDisplayed();
		BaseClass.logger.pass(msg);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClass.logger.fail("element not found  due to errror"+e.getMessage()+"<a href='"+getScreenShot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	public void clickElement(WebElement elm,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		
		elm.click();
        BaseClass.logger.pass(msg);
		
		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClass.logger.fail("failed due to"+e.getMessage()+"<a href='"+getScreenShot()+"'><span class='label end-time'>Screenshot</span></a>");
			
			
		}
	}
	
	public void SelectText(WebElement elm,String value,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		Select sel=new Select(elm);
		sel.selectByVisibleText(value);
		elm.sendKeys(value);
        BaseClass.logger.pass(msg);
		
		}
		
		
		catch(Exception e)
		{
			e.printStackTrace();
			BaseClass.logger.fail("failed due to"+e.getMessage());
			
			
		}
}
}
