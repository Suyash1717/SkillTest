package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageElements {

	@FindBy(xpath = "//select[@class='product_sort_container']")
	public static WebElement filterOptions;

	@FindBy(xpath = "//div[@class='inventory_item_price']")
	public static WebElement priceList;

	@FindBy(xpath = "//span[@class='title']")
	public static WebElement title;

	@FindBy(xpath = "//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']")
	public static WebElement cartLink;

	@FindBy(xpath = "//button[@id='checkout']")
	public static WebElement checkout;

	@FindBy(xpath = "//span[text()='Your Cart']")
	public static WebElement cartHeader;

	public static List aList = new ArrayList();
	public WebElement addToCart(WebDriver driver, String Price) {

	return driver.findElement(By.xpath("//div[text()='"+Price+"']/following-sibling::button[text()='Add to cart']"));

	}

	public String getCheapestProduct(WebDriver driver) {

	List<WebElement> productPrice = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
	float Min = Float.parseFloat(productPrice.get(0).getText().replace("$",""));

	for (WebElement objList : productPrice) {
	String strText = (objList.getText()).replace("$","");
	float price = Float.parseFloat (strText);
	aList.add(price);
	if(price<Min) {
	Min = price;
	}
	}

	String lowestValue = String.valueOf(Min);
	return lowestValue;
	}

	@SuppressWarnings("unchecked")
	public String getSecondHighest() {
	Collections.sort(aList);
	String secondHighest = String.valueOf(aList.get(aList.size()-2));
	return secondHighest;

	}

	
	
	
}
