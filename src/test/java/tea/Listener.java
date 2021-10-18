package tea;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tea.qa.utilities.utility;

public class Listener implements ITestListener {

	ExtentReports ext= utility.getExtentReport();
	ExtentTest tst;
	
	@Override
	public void onTestStart(ITestResult result) {
		tst= ext.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		tst.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		tst.fail(result.getThrowable());
		try {
			utility.getss(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = System.getProperty("user.dir")+"\\reports\\"+result.getName()+".png";
		tst.addScreenCaptureFromPath(path);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
	ext.flush();	
	}

}
