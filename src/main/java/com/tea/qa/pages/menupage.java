package com.tea.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class menupage {

	WebDriver driver;
	
	public menupage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By teaname = By.xpath("//strong");
	private By checkoutbutton = By.xpath("//span[text()='Check Out']");
	
	public void clickCheckout(String teanametoselect) {
		List<WebElement> teanames= driver.findElements(teaname);
		List<WebElement> checkoutbutons= driver.findElements(checkoutbutton);
		
		for (int i=0; i<teanames.size(); i++) {
			if (teanames.get(i).getText().equals(teanametoselect)) {
				checkoutbutons.get(i).click();
			}
		}
	}
}
