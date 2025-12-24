package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;
import testBase.BaseClass;

public class TestcaseHome extends BaseClass {
	
	
	
	
	@Test(groups = { "regression", "master"})
	public void verify() {
		
		logger.info("starting TestCase");
		
		HomePage hp = new HomePage(driver);
		logger.info("clicked on myaccount");
		
		hp.clickMyAccount();
		logger.info("Registartion now ");
		
		hp.clickRegister();
		
		AccountRegistrationPage page = new AccountRegistrationPage(driver);
		logger.info("Entered your first and last name");
		
		page.setfirstname("harshu");
		page.setlastname("bunty");
		
		logger.info("MailID entered");
		
		page.setmail(randomeString() + "@gmail.com");
		
		String password = generatedpassword();
		
		logger.info("password generated ");
		
		page.setpassword(password);
		page.setconpassword(password);
		page.settelephone(randomenumber());
		page.setbtn();
		
		logger.info("Ending TestCase");
		
		logger.error("i'm error message");
		
		logger.debug("hi i'm Debug hehehe..");
	}
		
		

}
