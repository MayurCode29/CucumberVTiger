package com.vtiger.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LeadSteps extends BaseClass {
	
	
	
	@When("user clicks on New lead link")
	public void Click_lead()
	{
		driver.findElement(By.linkText("New Lead")).click();
	}
	
	@When("fill all mandatory fields and clicks on save")
	public void fill_all_mandtory_field()
	{
		driver.findElement(By.name("lastname")).sendKeys(dt.get(TcName).get("LastName"));
		driver.findElement(By.name("company")).sendKeys(dt.get(TcName).get("Company"));
		driver.findElement(By.name("button")).click();
	}
	@When("lead should be created successfully")
	public void validate_lead_creation()
	{
		driver.findElement(By.xpath("//td[text()='Last Name:']/following::td[1]")).getText().equals("Mike");
		driver.findElement(By.xpath("//td[text()='Company:']/following::td[1]")).getText().equals("MRF");
		
		
	} 
	

@When("user clicks creates the multiple leads with {string} and {string} and verified")
	public void user_clicks_creates_the_multiple_leads_with_and_and_verified(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
		List<Map<String,String>>dt=dataTable.asMaps();
		for(Map<String,String>m:dt)
		{
		driver.findElement(By.linkText("New Lead")).click();
		driver.findElement(By.name("lastname")).sendKeys(m.get("lastname"));
		driver.findElement(By.name("company")).sendKeys(m.get("company"));
		driver.findElement(By.name("button")).click();
		driver.findElement(By.xpath("//td[text()='Last Name:']/following::td[1]")).getText().equals(m.get("lastname"));
		driver.findElement(By.xpath("//td[text()='Company:']/following::td[1]")).getText().equals(m.get("company"));
		}
	   
	}
	@When("user should click on logout button")
	public void user_should_click_on_logout_button() {
		driver.findElement(By.xpath("//*[text()='Logout']")).click();
	}
	    
	    




}
