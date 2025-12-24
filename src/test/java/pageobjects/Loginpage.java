package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends BasePage {

	public Loginpage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@id=\"input-email\"]")
	WebElement emailid;
	@FindBy(xpath = "//input[@id=\"input-password\"]")
	WebElement password;
	@FindBy(xpath = "//input[@value=\"Login\"]")
	WebElement btnlogin;

	public void setemailid(String email) {
		emailid.sendKeys(email);

	}

	public void setpassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void clicklogin() {
		btnlogin.click();
	}

	
}
