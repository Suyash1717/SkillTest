package pageEvents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;
import utils.InputData;

public class HomePageEvents {
	
HomePageElements homePageElements = new HomePageElements();
	
	public void homePageTest(InputData inputData,WebDriver driver) throws Exception{
		
		homePageElements = PageFactory.initElements(driver, HomePageElements.class);
		Select objSelectOption = new Select(homePageElements.filterOptions);
		objSelectOption.selectByVisibleText(inputData.getSort());
		String lowestPrice = homePageElements.getCheapestProduct(driver);
		homePageElements.addToCart(driver, lowestPrice).click();
		String secondHighest = homePageElements.getSecondHighest();
		homePageElements.addToCart(driver, secondHighest).click();
		homePageElements.cartLink.click();
		homePageElements.checkout.click();
		
			
	}
	
	

}
