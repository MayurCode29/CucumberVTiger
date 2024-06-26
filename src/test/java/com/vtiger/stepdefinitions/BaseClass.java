package com.vtiger.stepdefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public LoginPage lp;
	public Properties prop;
	public static Map<String,Map<String,String>> dt;
	public static String TcName;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public void Initiation()
	{
		if (extent==null);
		createExtentReport();
		readproperties();
		dt=readExcel(System.getProperty("user.dir")+"/src/test/resources/Data/testdata.xlsx","Sheet1");
		System.out.println(dt);
		
		
	}

	
	
	public void launchApp()
	{
		if(prop.getProperty("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(prop.getProperty("browser").equals("firefox"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(prop.getProperty("browser").equals("headless"))
		{
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--headless=new");
			driver=new ChromeDriver(option);
		}
		else
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
			
				
		
	}
	
	public void readproperties()
	{
		 prop=new Properties();
		try {
			
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/settings.properties");
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String,Map<String,String>> readExcel(String file,String sheet)
	{
		Map<String,Map<String,String>> dt=new HashMap<String,Map<String,String>>();
		try
		{
	    
		Fillo fillo=new Fillo();
		Connection connection=fillo.getConnection(file);
		String strQuery="Select * from " +sheet ;
		Recordset recordset=connection.executeQuery(strQuery);
		int rowcount=recordset.getCount();
		List<String>lst=recordset.getFieldNames();
		int colcount=lst.size();
		 
		while(recordset.next()){
			
		
		Map<String,String>rowdata=new HashMap<String,String>();
		
		for(int i=0;i<colcount;i++)
		{
			rowdata.put(lst.get(i),recordset.getField(lst.get(i)));
		}
		
		dt.put(recordset.getField("TCName"),rowdata);
		
		}
		recordset.close();
		connection.close();
	     }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return dt;
	}
	
	 public void createExtentReport()
	 {
		 Date d=new Date();
		 DateFormat ft=new SimpleDateFormat("ddMMyyyyhhmmss");
		 String fileName=ft.format(d);
		 ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/src/test/java/com/vtiger/reports/ExtentReport"+fileName+".html");
		 //create object of extent reports
		 
		 extent= new ExtentReports();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Host Name","HHH");
		 extent.setSystemInfo("Environment","HHH");
		 extent.setSystemInfo("UserName","HHH");
		 htmlReporter.config().setDocumentTitle("Title of report comes here");
		 htmlReporter.config().setReportName("Title of report comes here");
		 htmlReporter.config().setTheme(Theme.STANDARD);
		 
		 
		 
	 }
	 
}

