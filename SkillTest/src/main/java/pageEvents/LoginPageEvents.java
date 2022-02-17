package pageEvents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.LoginPageElements;

import utils.InputData;

public class LoginPageEvents {

	LoginPageElements loginPageElements = new LoginPageElements();
	
	public void loginTest(InputData inputData,WebDriver driver) throws Exception{
		
		loginPageElements = PageFactory.initElements(driver, LoginPageElements.class);
		loginPageElements.userName.sendKeys(inputData.getUser());
		loginPageElements.password.sendKeys(inputData.getPassword());
		loginPageElements.loginButton.click();
			
	}

}
