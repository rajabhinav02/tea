package com.tea.qa.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class welcomepage {
	
	public static Logger log = LogManager.getLogger(welcomepage.class.getName());

	WebDriver driver;
	
	public welcomepage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	private By welcomelink = By.xpath("//a[text()='Welcome']");
	private By seecollbutton = By.xpath("//span[contains(text(),'Collection')]");
	private By teaname = By.cssSelector("[class*='editor_collections']");
	private By passion = By.xpath("//a[contains(text(),'Our')]");
	private By menu = By.xpath("//a[contains(@data-url,'menu.html')]");
	private By talk = By.xpath("//a[contains(text(),\"Let's\")]");
	private By checkout = By.xpath("//a[contains(text(),\"Out\")]");
	@FindBy (xpath="//a[contains(text(),'Passion')]")
	private WebElement passionk;
	private By alllinksleft = By.xpath("//ul[contains(@class,'navigation')]//a");
	
	
	
	public void clickCollection(String itemname) {
		List<WebElement> items = driver.findElements(teaname);
		List<WebElement> collbuttons = driver.findElements(seecollbutton);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",collbuttons.get(1));
		
		
		for (int i=0; i<items.size(); i++) {
			if (items.get(i).getText().equalsIgnoreCase(itemname)) {
				collbuttons.get(i).click();
				log.info("Item selected");
				break;
				
			}
		}
	}
	
	public passionpage clickPassion() {
		passionk.click();
		log.info("Passion clicked");
		passionpage pp = new passionpage(driver);
		return pp;
	}
	
	public void talktea() {
		driver.findElement(talk).click();
		log.info("Talk clicked");
	}
	
	public void clickchkout() {
		driver.findElement(checkout).click();
		log.info("Check Out clicked");
	}
	
	public void clickWelcome() {
		driver.findElement(welcomelink).click();
		log.info("Welcome clicked");
	}
	
	public menupage clickMenu() {
		driver.findElement(menu).click();
		menupage mp = new menupage(driver);
		log.info("Menu clicked");
		return mp;
	}
	
	public HashSet<String> checktitleofalllinks() throws InterruptedException {
		
		String parent=driver.getWindowHandle();
		
		HashSet<String> al = new HashSet<String>();
		List<WebElement> links = driver.findElements(alllinksleft);
		for (WebElement link:links) {
			link.sendKeys(Keys.chord(Keys.CONTROL, Keys.ENTER));
			Thread.sleep(2000);
		}
		
		
		
		Set windows = driver.getWindowHandles();
		
		Iterator<String> window =windows.iterator();
		
		while(window.hasNext()) {
			
			driver.switchTo().window(window.next());
			/*String win=window.next();
			
			if (!win.equals(parent)) {
				
				driver.switchTo().window(win);
				al.add(driver.getTitle());
				System.out.println(driver.getTitle());
			}else {
				window.next();
			}*/
			
			log.info("Title is "+ driver.getTitle());
			al.add(driver.getTitle());
			
		}
		System.out.println(al.size());
		return al;
	}
}
