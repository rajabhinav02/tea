package tea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tea.qa.base.base;
import com.tea.qa.pages.welcomepage;
import com.tea.qa.utilities.utility;

public class testWelcomepage extends base {

	welcomepage wc;
	
	
	@BeforeClass
	public void getSetup() throws IOException {
		driver = setup();
		wc = new welcomepage(driver);
	}
	
	@Test (priority=1, dataProvider= "getTea")
	public void testaddproduct(String name) throws InterruptedException {
		wc.clickCollection(name);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().equals("Menu"));
	}
	
	@Test(priority = 2)
	public void testWelcome() throws InterruptedException {
		wc.clickWelcome();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().equals("Welcome"));
	}
	
	@Test(priority=3)
	public void testMenu() throws InterruptedException {
		wc.clickMenu();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().equals("Menu"));
	}
	
	@Test(priority=5)
	public void testTalk() throws InterruptedException {
		wc.talktea();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().equals("Let's Talk Tea"));
	}
	
	@Test(priority=4)
	public void testCheckout() throws InterruptedException {
		wc.clickchkout();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().equals("Check Out"));
	}
	
	@Test(priority=6)
	public void testPassion() throws InterruptedException {
		wc.clickPassion();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().equals("Our Passion"));
	}
	
	@Test(priority=7)
	public void testallLink() throws InterruptedException {
		HashSet al=wc.checktitleofalllinks();
		//Assert.assertTrue(!al.contains("Our Passion"));
		Assert.assertTrue(al.size()==5);
	}
	
	@DataProvider
	public Object[][] getTea() {
		Object[][] obj = utility.getExcelarray("welcome");
		//Object[][] obj = new Object[1][1];
		//obj[0][0]= "Loose Tea";
		return obj;
	}
	
	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
	
	/*public void getSS(ITestResult result) throws IOException {
		
		if (ITestResult.FAILURE== result.getStatus()) {
			utility.getss(result.getName());
		}
	}*/
}
