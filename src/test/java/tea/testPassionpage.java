package tea;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tea.qa.base.base;
import com.tea.qa.pages.passionpage;
import com.tea.qa.pages.welcomepage;

public class testPassionpage extends base {
	
	private static final String priority = null;
	welcomepage wc;
	passionpage pp;

	@BeforeClass
	public void getSetup() throws IOException {
		driver= setup();
		wc = new welcomepage(driver);
		pp = wc.clickPassion();
	}
	
	@Test(priority= 1)
	public void testPageload() {
		WebElement pic = pp.getPic();
		Assert.assertTrue(pic.isDisplayed());
	}
	
	@Test(priority= 2)
	public void testdoubleclick() {
		String bg1= pp.getbackgroundcolor();
		pp.clickdouble();
		String bg2= pp.getbackgroundcolor();
		Assert.assertTrue(bg1!=bg2);
		
	}
	
	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
}
