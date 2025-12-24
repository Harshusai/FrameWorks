package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public  MyAccountPage(WebDriver driver)
	{
		super(driver);

	}


	@FindBy(xpath = "//h2[normalize-space()=\"My Account\"]") //my account page heading
	WebElement msg;
	
	@FindBy(xpath = "//a[@class=\"list-group-item\"][normalize-space()=\"Logout\"]") //click logout btn
	WebElement logout;
	

	public boolean myaccountexists() {
		try
		{
			return(msg.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
 public void clicklogout() {
	 logout.click();
 }









}
