package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
		
	}

@FindBy(xpath="//input[@id=\"input-firstname\"]")
WebElement txtfirstname;

@FindBy(xpath="//input[@id=\"input-lastname\"]")
WebElement txtlastname;	


@FindBy(xpath="//input[@id=\"input-email\"]")
WebElement txtmail;	


@FindBy(xpath="//input[@id=\"input-telephone\"]")
WebElement telephone;	


@FindBy(xpath="//input[@id=\"input-password\"]")
WebElement password;	


@FindBy(xpath="//input[@id=\"input-confirm\"]")
WebElement passwordconfirm;	

@FindBy(xpath="//input[@value=\"Continue\"]")
WebElement btncountinue;

//Action method

public void setfirstname(String fname) {
	txtfirstname.sendKeys(fname);
}
public void setlastname(String lname) {
	txtlastname.sendKeys(lname);
}	
public void setmail(String mail) {
	txtmail.sendKeys(mail);
}
public void settelephone(String phone) {
	telephone.sendKeys(phone);
}
public void setpassword(String pwd) {
	password.sendKeys(pwd);
}

public void setconpassword(String pwd) {
	passwordconfirm.sendKeys(pwd);
}
public void setbtn() {
	btncountinue.click();;
}

}
