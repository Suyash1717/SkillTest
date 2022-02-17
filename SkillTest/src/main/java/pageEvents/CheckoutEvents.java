package pageEvents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageObjects.CheckoutElements;
import pageObjects.HomePageElements;
import utils.InputData;

public class CheckoutEvents {
	
CheckoutElements checkOutElements = new CheckoutElements();
	
	public void checkOutTest(InputData inputData,WebDriver driver) throws Exception{
		
		checkOutElements = PageFactory.initElements(driver, CheckoutElements.class);
		checkOutElements.firstName.sendKeys(inputData.getFirstName());
		checkOutElements.lastName.sendKeys(inputData.getLastName());
		checkOutElements.zip.sendKeys(inputData.getZip());
		checkOutElements.continueBtn.click();
		checkOutElements.finishBtn.click();
		
		
		
			
	}
	
	

}
