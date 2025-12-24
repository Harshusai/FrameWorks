package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions.Transport;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    // 1.🔹 Before execution

    public void onStart(ITestContext testContext) {
    	
    	/*
    	SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    	  Date dt = new Date();
    	  String Currentdatetimestamp = df.format(dt);
	*/
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                .format(new Date());

        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/Reports/" + repName); //specify location of the 

        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //title of report
        sparkReporter.config().setReportName("Opencart Functional Testing"); //name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");
        
        
      //values passing through xml file
        
        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }

    }

    // 2. 🔹Test start
   
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
    }

    // 🔹 if test PASS
   
    public void onTestSuccess(ITestResult result) {
        test.assignCategory(result.getMethod().getGroups()); //to display groups in report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }

    //3. 🔹 FAIL + Screenshot
   
    public void onTestFailure(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass()
                    .captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 4.🔹 SKIP
    
    public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // 5. 🔹 After execution (OPEN REPORT + EMAIL)
   
    public void onFinish(ITestContext testContext) {

        extent.flush();

        // 🔹 Open report automatically
        String pathOfExtentReport =
                System.getProperty("user.dir") + "/Reports/" + repName;

        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        // 🔹 Send report via email
       try {
        URL url = new URL( "file:///" + System.getProperty("user.dir") + "\\Reports\\" + repName);

    // Create the email message
    ImageHtmlEmail email = new ImageHtmlEmail();
    email.setDataSourceResolver(new DataSourceUrlResolver(url));
    email.setHostName("smtp.googlemail.com");
    email.setSmtpPort(465);
    email.setAuthenticator(
            new DefaultAuthenticator("harshu@gmail.com", "password"));
    email.setSSLOnConnect(true);

    email.setFrom("harshithanaidu25@gmail.com");     // Sender
    email.setSubject("Test Results");
    email.setMsg("Please find attached report...");
    email.addTo("bunty@gmail.com");       // Receiver

    email.attach(url, "extent report", "please check report...");
    email.send();   // send the email

} catch (Exception e) {
    e.printStackTrace();
}
*/

    }
}
