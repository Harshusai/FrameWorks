package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.Loginpage;
import pageobjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TestcaseDataDriven extends BaseClass {
	
	
	
	@Test(dataProvider="LoginData" , dataProviderClass = DataProviders.class, groups="datadriven") //getting dataprovides from different class
	public void verifyDataDrivenTest(String email, String pwd, String exp) throws InterruptedException {
		
		logger.info("starting TestCase for DataDrivenTestcase");
	
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
				
				lp.setemailid(email);
				logger.info("MailID generated from Excel");
				lp.setpassword(pwd);
				logger.info("password generated from Excel");
				lp.clicklogin();
				logger.info("clicked on myaccount");
				
//MyAccount page
				MyAccountPage acc = new MyAccountPage(driver);
				boolean targetpage = acc.myaccountexists();
				
				
//Data from Excel is valid - login success - test pass - logout
						// - login failed - test fail
				
				if(exp.equalsIgnoreCase("valid"))
				{
					if(targetpage==true)
					{
						acc.clicklogout();
						Assert.assertTrue(true);
					}
					else
					{
						Assert.assertTrue(false);
					}
					
				}
				
				
				
//Data from Excel is invalid - login success - test fail - logout
				// - login failed - test pass
		
				if(exp.equalsIgnoreCase("invalid"))
				{
					if(targetpage==true)
					{
						acc.clicklogout();
						Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
					
				}
	}catch(Exception e) {
		Assert.fail();
	}
	Thread.sleep(3000);
	logger.info("Ending TestCase for DataDrivenTestcase");
			

}
}