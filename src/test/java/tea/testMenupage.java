package tea;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tea.qa.base.base;
import com.tea.qa.pages.menupage;
import com.tea.qa.pages.welcomepage;
import com.tea.qa.utilities.utility;

public class testMenupage extends base {

	menupage mp;
	
	@BeforeClass
	public void getSetup() throws IOException {
		driver= setup();
		welcomepage wp =new welcomepage(driver);
		menupage mp = wp.clickMenu();
	}
	
	@Test(dataProvider="getTeaname")
	public void testteaselection(String name) {
		mp.clickCheckout(name);
	}
	
	
	@DataProvider
	public Object[][] getTeaname() {
		Object[][] data = utility.getExcelarray("menu");
		return data;
	}
	
	
}
