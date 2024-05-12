package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.common.PageActions;

public class LoginPage extends PageActions {
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="user_name")
	WebElement tb_uid;
	
	@FindBy(name="user_password")
	WebElement tb_pass;
	
	@FindBy(name="Login")
	WebElement btn_login;
	
	@FindBy(name="login_theme")
	WebElement dp_theme;
	
	@FindBy(xpath="//img[@src='include/images/login_left.gif']")
	WebElement img_logo;
	
	@FindBy(xpath="//font[text()='Key Modules']")
	WebElement txt_keyModules;
	
	public void VerifyTitle(String expectedTitle)
	
	{
		validate_Expected_Actual_Text(expectedTitle,driver.getTitle(),"expected and actual title validated successfully");
	}
	
	
    public void VerifyLogo()
	
	{
		
    	ElementExist(img_logo,"Logo displayed successfully");
	}
    
public void VerifyKeyModuleText()
	
	{
		
    	ElementExist(txt_keyModules,"Key moduletext verified");
	}
	
	public void Valid_Login(String userid,String pwd)
	{
		setUserName(userid);
		setPassword(pwd);
		Click_Login();
		
	}
	
	public void Valid_Login(String userid,String pwd,String theme)
	{
		setUserName(userid);
		setPassword(pwd);
		selectTheme(theme);
		Click_Login();
		
	}
	
	public void setUserName(String userid)
	{
		//tb_uid.clear();
		//tb_uid.sendKeys(userid);
		setText(tb_uid,userid,userid +"has been entered successfully in username field");
	}
	
	public void setPassword(String pwd)
	{
		//tb_pass.clear();
		//tb_pass.sendKeys(pwd); locator and data
		setText(tb_pass,pwd,pwd +"has been entered successfully in password field");
	}
	
	public void Click_Login()
	{
		//btn_login.click();
		clickElement(btn_login,"login button clicked successfully");
	

}
	public void selectTheme(String theme)
	{
		SelectText(dp_theme,theme,theme+"selected from theme dropdown");
	}
}
