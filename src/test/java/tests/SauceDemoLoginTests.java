package tests;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.SaucedemoPage;
import utils.Driver;

public class SauceDemoLoginTests {
	
	@Test(dataProvider = "credential")
	public void credentialTest(String username, String password) throws InterruptedException {
		Driver.getDriver().get("https://saucedemo.com");
		System.out.println(username + "   " + password);
		
		SaucedemoPage page=new SaucedemoPage();
		page.username.sendKeys(username);
		page.password.sendKeys(password);
		page.loginButton.click();
		
		Thread.sleep(1000);
		Assert.assertTrue(page.errorMessage.getText().contains("Epic"));
		
	}
	
	@DataProvider
	public String [][] credential(){
		String [][] names =new String [2] [2];
		names [0] [0]="standard_user";
		names [0] [1]="standardhhfvajk";
		
		names [1] [0]="standard_user11";
		names [1] [1]="secret_sauce";
		
		return names;
		
		//row 0|0|1|
		//row 1|0|1|
		
	}
	
	@BeforeMethod
	public void setup() {
		Driver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void quitDriver() {
		Driver.quitDriver();
	}

}
