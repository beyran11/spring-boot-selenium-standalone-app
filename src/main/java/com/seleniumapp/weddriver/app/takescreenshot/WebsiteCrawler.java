package com.seleniumapp.weddriver.app.takescreenshot;

import com.google.common.io.Files;
import com.google.common.util.concurrent.Uninterruptibles;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class WebsiteCrawler {


    private WebDriver driver;
    private int hashcode=1;



    public void initialDelay(){
        //intentionally added this as chrome/firefox containers take few ms to register
        //to hub - test fails saying hub does not have node!!
        //very rare
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
    }



    public int setUp(String url) throws MalformedURLException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");

        DesiredCapabilities dc = DesiredCapabilities.chrome();
        //DesiredCapabilities dc = DesiredCapabilities.firefox();
        //dc.setBrowserName("chrome");
        //dc.setBrowserName("firefox");
        //dc.setPlatform(Platform.LINUX);
        dc.setCapability("max-duration","120");
        dc.setCapability("command-timeout","120");
        dc.setCapability("idle-timeout","120");

        //if (System.getProperty("browser").equals("firefox"))
        //    dc = DesiredCapabilities.firefox();

        //String host = System.getProperty("seleniumHubHost");
        //System.out.println(host);

        //Headless chrome driver
        //ChromeOptions options = new ChromeOptions();

        //options.setHeadless(true);
        //options.merge(dc);

        LoggingPreferences logP = new LoggingPreferences();
        //logP.enable(LogType.BROWSER, Level.ALL);
        //logP.enable(LogType.PERFORMANCE, Level.ALL);
        //logP.enable(LogType.PROFILER, Level.ALL);
        //logP.enable(LogType.BROWSER, Level.ALL);
        //logP.enable(LogType.CLIENT, Level.ALL);
        logP.enable(LogType.DRIVER, Level.ALL);
        //logP.enable(LogType.SERVER, Level.ALL);
        //dc.setCapability(CapabilityType.LOGGING_PREFS, logP);
        //dc.setCapability("console", true); // To capture console logs
        dc.setCapability(CapabilityType.LOGGING_PREFS, logP );

        //driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
        //driver = new RemoteWebDriver(new URL("https://localhost:4444/wd/hub"), dc);
        //String nodeUrl = "http://192.168.160.3:5555/wd/hub";
        //driver = new RemoteWebDriver(new URL(nodeUrl), dc);
        WebDriver driver = new RemoteWebDriver(new URL("http://my-chrome:4444/wd/hub/"), dc );

        //System.out.println("Session Id: " + ((RemoteWebDriver) driver).getSessionId());
        hashcode=driver.hashCode();
        //System.out.println(hashcode);

        //}

        //public void crawl(String url) {
        try {
            // Navigate to the specified directory
            driver.navigate().to(url);
            // Sleep for 5 seconds in case the website has not fully loaded
            Thread.sleep(10000);
            // Take the screenshot and copy to the specified directory
            File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            //FileUtils.copyFile(src, new File("./src/main/aquiredscreenshots/"+url+".png"));
            //Files.copy(src, new File("/usr/local/bin/" + url + ".png"));
            File target = new File("./src/main/aquiredscreenshots/"+url+".png") ;
            Files.copy(src, target);

            //System.out.println(driver.switchTo().alert());
            System.out.println("HHHHHHHHH");
            //System.out.println(driver);

            System.out.println(driver.getCurrentUrl());
            System.out.println(driver);
            //System.out.println(driver.getWindowHandle());
            hashcode=0;




        } catch (Exception e) {
            e.printStackTrace();
        }

        return hashcode;
    }



    public void tearDown(){
        //driver.quit();
        driver.close();
    }

    public static void showLogs(int hashcode) {
        if (hashcode == 0) {
            System.out.println("Done");
        }

        else {
            if (hashcode == 1) {
                System.out.println("Internal Error");
            }
            else {
                System.out.println("Given URL is Invalid");
            }
        }
    }
}


