package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class SignUpPage {
	public SignUpPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(xpath = "//h2[text()='Sign Up']")
	public WebElement signupText;
	
	@FindBy(xpath = "//label[text()='Email Address']")
	public WebElement emailFieldLabel;
	
	@FindBy(name = "email")
	public WebElement emailField;
	
	@FindBy(xpath = "//label[text()='First Name']")
	public WebElement firstNameLabel;
	
	@FindBy(name = "firstName")
	public WebElement firstNameField;
	
	@FindBy(xpath = "//label[text()='Last Name']")
	public WebElement lastNameLabel;
	
	@FindBy(name = "lastName")
	public WebElement lastNameField;
	
	@FindBy(xpath = "//label[text()='Password']")
	public WebElement passwordLabel;
	
	@FindBy(name = "password")
	public WebElement passwordField;
	
	@FindBy(xpath = "//span[text()='Login with Google']")
	public WebElement signWithGoogleLink;
	
	@FindBy(xpath = "//span[text()='Login with Facebook']")
	public WebElement signWithFacebookLink;
	
	@FindBy(xpath = "//hr")
	public WebElement underLine;
	
	@FindBy(id = "subscribe")
	public WebElement subscribecheckBox;
	
	@FindBy(xpath = "//label[text()='Subscribe To Our Newsletter']")
	public WebElement subcribeToNewsletter;
	
	@FindBy(xpath = "//span[text()='Sign Up']")
	public WebElement signUpBtn;
	
	@FindBy(linkText = "Back To Login")
	public WebElement backToLoginLink;
	

}
