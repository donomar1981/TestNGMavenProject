package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class AmazonPage {
	
	public AmazonPage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy(id = "twotabsearchtextbox")
	public WebElement searchBox;
	
	@FindBy(id = "nav-search-submit-button")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']")
	public WebElement searchText;
	
	@FindBy(xpath = "//span[@class=\"a-color-state a-text-bold\"]")
	public WebElement searchedItemText;

}
