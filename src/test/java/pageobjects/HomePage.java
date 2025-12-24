package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
	}

@FindBy (xpath="//a[@title=\"My Account\"]")
WebElement lnkMyaccount;
@FindBy (xpath="//a[normalize-space()=\"Register\"]")
WebElement lnkRegister;
@FindBy (xpath="(//a[normalize-space()='Login'])[1]")
WebElement lnklogin;

public void clickMyAccount() {
	lnkMyaccount.click();
	
}
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clicklogin() {
		lnklogin.click();
	}
	
	
}
