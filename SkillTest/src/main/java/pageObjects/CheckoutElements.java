package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutElements {
	
	@FindBy(xpath = "//span[text()='Checkout: Your Information']")
	public static WebElement checkoutHeader;

	@FindBy(xpath = "//input[@id='first-name']")
	public static WebElement firstName;

	@FindBy(xpath = "//input[@id='last-name']")
	public static WebElement lastName;

	@FindBy(xpath = "//input[@id='postal-code']")
	public static WebElement zip;

	@FindBy(xpath = "//input[@id='continue']")
	public static WebElement continueBtn;

	@FindBy(xpath = "//span[text()='Checkout: Overview']")
	public static WebElement checkoutConfirm;

	@FindBy(xpath = "//button[@id='finish']")
	public static WebElement finishBtn;

	@FindBy(xpath = "//div[@id='checkout_complete_container']/h2[text()='THANK YOU FOR YOUR ORDER']")
	public static WebElement finalConfirmation;

}
