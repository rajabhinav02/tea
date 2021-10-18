package com.tea.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class talkteapage {

	WebDriver driver;
	
	public talkteapage(WebDriver driver) {
		this.driver=driver;
	}
	
	private By name = By.cssSelector("[name='name']");
	private By email = By.cssSelector("[name='email']");
	private By subject = By.cssSelector("[name='subject']");
	private By message = By.cssSelector("[name='message']");
	private By submit = By.cssSelector("[type='submit']");
}
