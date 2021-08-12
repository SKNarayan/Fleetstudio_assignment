package com.fleetstudio.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class SetUp {

    public static WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String nameOfBrowser) {

        ReadConfig readConfig = new ReadConfig();

        if(nameOfBrowser.equals("chrome")) {

            try {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();

                driver.manage().window().maximize();


            } catch (Exception e) {
                throw new RuntimeException("Error while setting up chrome browser");
            }

        }else if(nameOfBrowser.equals("firefox")) {
            try {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);

                driver.manage().window().maximize();

            } catch (Exception e) {
                throw new RuntimeException("Error while setting up firefox browser");
            }
        }

        driver.get(readConfig.getApplicationURL());

    }
    @AfterClass
    public void tearDown () {
        driver.quit();
    }

}



