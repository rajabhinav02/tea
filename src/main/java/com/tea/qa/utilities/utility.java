package com.tea.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tea.qa.base.base;

public class utility extends base {

	static ExtentReports ext;
	public static String excelpath= System.getProperty("user.dir")+"\\testdata.xlsx";
	static FileInputStream fi;
	static Workbook wb;
	
	public static void getss(String tcname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dst = System.getProperty("user.dir")+"\\reports\\"+tcname+".png";
		FileUtils.copyFile(src, new File(dst));
	}
	
	public static ExtentReports getExtentReport() {
		
		String path = System.getProperty("user.dir")+"\\reports\\reports.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
		reporter.config().setReportName("Tea");
		
		ext = new ExtentReports();
		ext.attachReporter(reporter);
		return ext;
	}
	
	public ArrayList<String> excelarraylist(String sheetname, String tcname) throws IOException {
		fi = new FileInputStream(excelpath);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		int Sheetsnum = wb.getNumberOfSheets();
		int k=0;
		int column = 0;
		ArrayList<String>al = new ArrayList<String>();
		
		for (int i=0; i<Sheetsnum; i++) {
			XSSFSheet sheet=wb.getSheetAt(i);
			if (sheet.getSheetName().equals(sheetname)) {
				Iterator<Row> row=sheet.rowIterator();
				Row fr=row.next();
				
				Iterator<Cell> cell=fr.cellIterator();
				while (cell.hasNext()) {
					if (cell.next().getStringCellValue().equals("Test Case")) {
						column=k;
					}k++;
				}
				
				while (row.hasNext()) {
					if (row.next().getCell(column).getStringCellValue().equals(tcname)) {
						Iterator<Cell>cells=row.next().cellIterator();
						while (cells.hasNext()) {
							if (cells.next().getCellType().equals(CellType.STRING)) {
								al.add(cells.next().getStringCellValue());
							}else {
								String value=NumberToTextConverter.toText(cells.next().getNumericCellValue());
								al.add(value);
							}
						}
					}
				}
				
			}
		}return al;
	}
	
	public static Object[][] getExcelarray(String sheetname) {
		try {
			fi = new FileInputStream(excelpath);
		try {
			wb=WorkbookFactory.create(fi);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sheet sheet=wb.getSheet(sheetname);
		
		Object[][]data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for (int i=0; i<sheet.getLastRowNum(); i++) {
			for (int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j]= sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}
	
	
	public boolean verifybrokenlink(String linkurl) throws MalformedURLException {
	
		try {
			URL url = new URL(linkurl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.connect();
			
			if (con.getResponseCode()== HttpURLConnection.HTTP_BAD_REQUEST) {
				return true;
			}else {
				return false;
			}
		
		}catch (Exception e) {
			return false;
		}
		
	}
	
	public static Object[] excelsinglearray(String sheetname) {
		try {
			fi = new FileInputStream(excelpath);
			try {
				wb = WorkbookFactory.create(fi);
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sheet sheet=wb.getSheet(sheetname);
		
		Object[] obj = new Object[sheet.getLastRowNum()];
		
		for (int i=0; i< obj.length; i++) {
			obj[i]= sheet.getRow(i+1).getCell(i).getStringCellValue();
		}
		return obj;
	}
	
	
}
