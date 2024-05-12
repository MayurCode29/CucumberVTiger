package com.vtiger.stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vtiger.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Loginstepdefinitions extends BaseClass{
	
	
	@Before
	public void getScenarioName(Scenario scenario)
	{
		TcName=scenario.getName();
		Initiation();
		logger=extent.createTest(TcName);
		
	}
	
	
	@After
	public void closeApp()
	{
		extent.flush();
		driver.quit();
		
	}
	
	
	
	@Given("user should be on login page")
	public void user_should_be_on_login_page() {
		
		
		launchApp();
	    lp=new LoginPage(driver);
	    
	}
	@When("user enters valid credentials")
	public void user_enters_valid_credentials() {
		//driver.findElement(By.name("user_name")).sendKeys("admin");
		//driver.findElement(By.name("user_password")).sendKeys("admin");
		//driver.findElement(By.name("Login")).click();
		lp.setUserName(prop.getProperty("userid"));
		lp.setPassword(prop.getProperty("password"));
		lp.Click_Login();
	    
	}
	@Then("user should be navigated to homepage")
	public void user_should_be_navigated_to_homepage() {
	    driver.findElement(By.linkText("Home")).isDisplayed();
	}
	@Then("user can see logout link")
	public void user_can_see_logout_link() {
		driver.findElement(By.linkText("Logout")).isDisplayed();
	    
	}
	
	@When("user enters invalid credentials")
	public void user_enters_invalid_credentials() {
		//driver.findElement(By.name("user_name")).sendKeys("admin12");
		//driver.findElement(By.name("user_password")).sendKeys("admin12");
		//driver.findElement(By.name("Login")).click();
		lp.setUserName("userid56");
		lp.setPassword("admin12");
		lp.Click_Login();
	}
	@Then("user should be navigated to Login page")
	public void user_should_be_navigated_to_login_page() {
		driver.findElement(By.name("Login")).isDisplayed();
	}
	@Then("user can see error message")
	public void user_can_see_error_message() {
		driver.findElement(By.xpath("//*[contains(text(),'You must')]")).isDisplayed();
	}
		
	@When("user enters userid as {string} and password as {string} invalid credentials")
		public void user_enters_userid_as_and_password_as_invalid_credentials(String uid, String pwd)
		{
		    //driver.findElement(By.name("user_name")).clear();
		    //driver.findElement(By.name("user_name")).sendKeys(uid);;
		    //driver.findElement(By.name("user_password")).sendKeys(pwd);
		    //driver.findElement(By.name("user_passwoed")).clear();
		lp.setUserName(uid);
		lp.setPassword(pwd);
		}
	
	@When("user can verify the title")
	public void verifyTitle()
	{
		lp.VerifyTitle(dt.get(TcName).get("Title"));
	}
	
	
	@When("user can verify the logo")
	public void verifyLogo()
	{
		lp.VerifyLogo();
	}
	
	@When("user can verify keymodule text")
	public void verifyeyModuleText()
	{
		lp.VerifyLogo();
	}
	
	   
}







