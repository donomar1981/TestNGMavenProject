package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class SaucedemoPage {
	
	public SaucedemoPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id = "user-name")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(name = "login-button")
	public WebElement loginButton;
	
	@FindBy(xpath = "//h3[@data-test='error']")
	public WebElement errorMessage;
	
	
	
	

}
