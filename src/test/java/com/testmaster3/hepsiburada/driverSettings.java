package com.testmaster3.hepsiburada;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.CapabilityType;


import java.util.concurrent.TimeUnit;

public abstract class driverSettings {
    WebDriver driver;
    public static RemoteWebDriver driver;
    public static final String USERNAME = "demirelahmet";
    public static final String ACCESS_KEY = "bba815c75f0c90da38b258e1df762c06";
    public static final String KEY = USERNAME + ":" + ACCESS_KEY;
    public static final String URL = "http://hub.testinium.io/wd/hub";

    @Before
    public void beforeCreateDriver(){ //Driver'i burada calistirdik her method icin tekrar calistirmak gerekmiycek

        /*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\ahmet\\IdeaProjects\\hepsiburadalogin\\driver\\chromedriver.exe"); // chromedriver.exe nin yolunu belirttik

        driver = new ChromeDriver();*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("key", KEY);

        capabilities.setCapability(CapabilityType.PLATFORM, "WIN10");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability(CapabilityType.VERSION, "LATEST");
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        capabilities.setCapability("recordsVideo", true);
        capabilities.setCapability("screenResolution", "SXGA");
        
        WebDriver driver = new RemoteWebDriver(new URL(URL), capabilities);
     

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS); // 60 saniye siteye girmezse timeout ver demek
        driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);


    }

    @After
    public void afterQuitWebDriver(){

        //driver.quit();
    }

}
