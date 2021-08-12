package com.fleetstudio.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmilcottPage {
    WebDriver ldriver;
    public EmilcottPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);

    }

    @FindBy(xpath = "//title[contains(text(),'Greenlight™ Environmental Monitoring System - Flee')]")
    @CacheLookup
    WebElement emilcottPageTitle;

    public String emilcottPageTitle(){

        String emilcottPageTitle = ldriver.getTitle();
        return emilcottPageTitle;
    }


}
