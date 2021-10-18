package com.tea.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class base {
	
	public static WebDriver driver;
	ChromeOptions co;
	Properties pro;

	public WebDriver setup() throws IOException {
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
	pro = new Properties();
	pro.load(fis);
	
	if (System.getProperty("browser").contains("chrome")){
		if (System.getProperty("browser").contains("headless")) {
			co= new ChromeOptions();
			co.addArguments("--headless");
			driver= new ChromeDriver(co);
			if (System.getProperty("channel").equals("prod")) {
				driver.get(pro.getProperty("produrl"));
			}else if (System.getProperty("channel").equals("ete")) {
				driver.get(pro.getProperty("eteurl"));
			}
		}else {		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		driver= new ChromeDriver();
		}
		
		if (System.getProperty("channel").equals("prod")) {
			driver.get(pro.getProperty("produrl"));
		}else if (System.getProperty("channel").equals("ete")) {
			driver.get(pro.getProperty("eteurl"));
		}
		
	}else if  (System.getProperty("browser").contains("ff")){
		if (System.getProperty("browser").contains("headless")) {
			ChromeOptions co= new ChromeOptions();
			co.addArguments("--headless");
		}else {		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		WebDriver driver= new ChromeDriver(co);
		}
		
		if (System.getProperty("channel").equals("prod")) {
			driver.get(pro.getProperty("produrl"));
		}else if (System.getProperty("channel").equals("ete")) {
			driver.get(pro.getProperty("eteurl"));
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	return driver;
	} 
}
