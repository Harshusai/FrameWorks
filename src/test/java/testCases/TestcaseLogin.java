package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.Loginpage;
import pageobjects.MyAccountPage;
import testBase.BaseClass;

public class TestcaseLogin extends BaseClass {
	
	@Test(groups= {"sanity", "master"})
	public void verifyLogin() {
		
		logger.info("starting TestCase for loginpage");
	
	try
	{
//HomePage login
		HomePage hp = new HomePage(driver);
		logger.info("clicked on myaccount");
		hp.clickMyAccount();
		logger.info("MyAccount Page is  Opened");
		hp.clicklogin();
		
//Login page
			Loginpage lp = new Loginpage(driver);
				
				lp.setemailid(p.getProperty("email"));
				logger.info("mailid generated from config.properties file");
				//lp.setpassword(p.getProperty("password"));
				lp.setpassword("buntyguru@");
				logger.info("password generated from config.properties file");
				lp.clicklogin();
				logger.info("clicked on myaccount");
				
//MyAccount page
				MyAccountPage acc = new MyAccountPage(driver);
				boolean targetpage = acc.myaccountexists();
				logger.info("title displayes is true");
				Assert.assertTrue(targetpage);
				
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info("Closing testcaselogin");
	}
	
}
				
		


