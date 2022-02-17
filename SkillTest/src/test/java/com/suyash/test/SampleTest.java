package com.suyash.test;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import pageEvents.CheckoutEvents;
import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import pageObjects.HomePageElements;
import utils.CommonFunctions;
import utils.ExcelColumns;
import utils.InputData;

public class SampleTest extends baseTest {
	
	CommonFunctions cmf = new CommonFunctions();
	InputData inputData = new InputData();
	LoginPageEvents loginPageEvents = new LoginPageEvents();
	HomePageEvents homePageEvents = new HomePageEvents();
	CheckoutEvents checkOutEvents = new CheckoutEvents();
	ExtentTest test;
	
	@Test
	public void sampleMethodLogin() {
		try {
			List<Integer> tCases = getRowArray(parameter,"TestCase=>");
			for(Integer startRow: tCases) {
				
				swagTest(startRow);
				
			}
			System.out.println(tCases);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
	public void swagTest(int iteration) {
		
		
			
			try {
				HashMap<String,String> swagLabData = cmf.fetchXLDataNStoreInHashMap("InputDataRow=>"+iteration,"Data","InputDataRow","All");
				System.out.println(swagLabData);
				translateEnvCheck(swagLabData);
				launchURL(inputData.getTestURL());
				loginPageEvents.loginTest(inputData,driver);
				homePageEvents.homePageTest(inputData, driver);
				checkOutEvents.checkOutTest(inputData, driver);
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		
	}
	
	private void translateEnvCheck(HashMap<String, String> swagLabData)
			throws Exception {
		try {
			if (null != swagLabData) {
				

				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.TESTURL))) {
					inputData.setTestURL(swagLabData.get(ExcelColumns.TESTURL));
					
				}
				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.USER))) {
					inputData.setUser(swagLabData.get(ExcelColumns.USER));
					
				}
				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.PASSWORD))) {
					inputData.setPassword(swagLabData.get(ExcelColumns.PASSWORD));
					
				}
				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.SORT))) {
					inputData.setSort(swagLabData.get(ExcelColumns.SORT));
					
				}
				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.FIRSTNAME))) {
					inputData.setFirstName(swagLabData.get(ExcelColumns.FIRSTNAME));
					
				}
				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.LASTNAME))) {
					inputData.setLastName(swagLabData.get(ExcelColumns.LASTNAME));
					
				}
				if (StringUtils.isNotBlank(swagLabData.get(ExcelColumns.ZIP))) {
					inputData.setZip(swagLabData.get(ExcelColumns.ZIP));
					
				}
				
				} 
		}catch (Exception objException) {
					
				}
	
		
		}
	
	

}
