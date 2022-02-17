package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageElements {
	
	@FindBy(xpath = "//input[@id='login-button']")
	public static WebElement loginButton;

	@FindBy(xpath = "//input[@id='user-name']")
	public static WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	public static WebElement password;

}
