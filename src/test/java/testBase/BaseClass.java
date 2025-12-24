package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public Logger logger;
    public static WebDriver driver;
    public Properties p;

    @Parameters({ "os", "browser" })
    @BeforeClass(groups = { "sanity", "regression", "master", "datadriven" })
    public void setup(String os, String br) throws Exception {

        // Load config.properties
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        // ================= GRID / LOCAL =================
        if (p.getProperty("execution_env").equalsIgnoreCase("remote"))
        
        {

            DesiredCapabilities cap = new DesiredCapabilities();
/*
            // OS
            if (os.equalsIgnoreCase("windows"))
            {
                cap.setPlatform(Platform.WIN11);
            }
            else if (os.equalsIgnoreCase("mac"))
            {
                cap.setPlatform(Platform.MAC);
            } 
            else if (os.equalsIgnoreCase("linux")) 
            {
                cap.setPlatform(Platform.LINUX);
            }
          */

            // Browser
            switch (br.toLowerCase()) {
            case "chrome":
                cap.setBrowserName("chrome");
                break;
            case "edge":
                cap.setBrowserName("MicrosoftEdge");
                break;
            case "firefox":
                cap.setBrowserName("firefox");
                break;
            default:
                System.out.println("Invalid browser");
                return;
            }
            

            // IMPORTANT: assign to CLASS driver
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444"), cap);
           
        }

        
        else if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

            switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser");
                return;
            }
        }

        // ================= COMMON STEPS =================
        driver.get(p.getProperty("browserURL"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @AfterClass(groups = { "sanity", "regression", "master", "datadriven" })
    public void teardown() {
        driver.quit();
    }

    // ================= UTILITIES =================

    public String randomeString() {
        return RandomStringUtils.randomAlphabetic(6);
    }

    public String randomenumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String generatedpassword() {
        return RandomStringUtils.randomNumeric(4) + RandomStringUtils.randomNumeric(5);
    }

    public String captureScreen(String name) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String target = System.getProperty("user.dir") + "\\Screenshots\\" + name + "_" + timeStamp + ".png";
        File targetFile = new File(target);

        source.renameTo(targetFile);
        return target;
    }
}
