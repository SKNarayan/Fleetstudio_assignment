package com.fleetstudio.pageObject;

import com.fleetstudio.base.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class WorkPage {

    WebDriver ldriver;

    public WorkPage(WebDriver rdriver){
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//a[contains(text(),'Work')]")
    @CacheLookup
    WebElement workPage;

    @FindBy(xpath = "//select[@name='filter']/child::option[1]")
    @CacheLookup
    WebElement filterBy;

    //select[@name='filter']

    @FindBy(xpath = "//option[2]")
    @CacheLookup
    WebElement filterOptions_first;

    @FindBy(xpath = "//option[3]")
    @CacheLookup
    WebElement getFilterOptions_second;

    @FindBy(xpath = "//option[contains(text(),'All Tags')]")
    @CacheLookup
    WebElement allTags;

    @FindBy(xpath = "//option[contains(text(),'Product Dev')]")
    @CacheLookup
    WebElement productDev;

    @FindBy(xpath = "//option[contains(text(),'QA')]")
    @CacheLookup
    WebElement qa;

    @FindBy(xpath = "//p[@class='md:text-xs text-black text-lg mb-2']/ancestor::div[3]/descendant::p[1]")
    @CacheLookup
    WebElement card_emilcott;


    public void clickOnWorkPage(){
        workPage.click();
    }

    public String nameOfFilter(){
        CommonMethods.scrollToPixel(100);
         return filterBy.getText();
    }

    public void clickOnFilter(){
        CommonMethods.scrollToPixel(100);
        filterBy.click();
    }

    public WebElement filterByDropDown(){
        CommonMethods.scrollToPixel(100);
        return filterBy;
    }

    public boolean clickOnFistFilterOption(){
        return filterOptions_first.isDisplayed();

    }

    public boolean clickOnSecondfilterOption(){
        return getFilterOptions_second.isDisplayed();
    }

    public EmilcottPage clickOnFirstCard(){
        card_emilcott.click();

        EmilcottPage emilcottPage = new EmilcottPage(ldriver);
        return emilcottPage;


    }



}
