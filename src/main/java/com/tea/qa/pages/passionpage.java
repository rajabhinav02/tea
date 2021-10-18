package com.tea.qa.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class passionpage {

	WebDriver driver;
	public static Logger log = LogManager.getLogger(passionpage.class.getName());
	
	public passionpage(WebDriver driver) {
		this.driver=driver;
	}
	
	
	private By teaimage = By.xpath("//img[contains(@src,'9413510')]");
	private By ourpassion = By.xpath("//h1");
	
	public WebElement getPic() {
		return driver.findElement(teaimage);
	}
	
	public void clickdouble() {
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(ourpassion)).build().perform();
		
	}
	
	public String getbackgroundcolor() {
		
		String bg= driver.findElement(ourpassion).getCssValue("background-color");
		log.info("Backgound color is " +bg );
		return bg;
	}
}
