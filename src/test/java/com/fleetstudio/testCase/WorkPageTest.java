package com.fleetstudio.testCase;

import com.fleetstudio.base.SetUp;
import com.fleetstudio.pageObject.EmilcottPage;
import com.fleetstudio.pageObject.WorkPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WorkPageTest extends SetUp {

    @Test(enabled = true)
    public void testTitleOfWorkPage(){

        WorkPage workPage = new WorkPage(driver);
        workPage.clickOnWorkPage();
        String expectedWorkPageTitle = "Works - Fleet Studio";
        String actualWorkPageTitle = driver.getTitle();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualWorkPageTitle, expectedWorkPageTitle, "Work page title doesn't match");
        softAssert.assertAll();

    }

    @Test(enabled = true)
    public void testFilterName(){

        WorkPage workPage = new WorkPage(driver);
        String expectedFilterName = "ALL TAGS";
        String actualFilterName = workPage.nameOfFilter();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualFilterName, expectedFilterName, "Name of filter doesn't maich");
        softAssert.assertAll();

    }

    @Test(enabled = true)
    public void testFilterFirstOption(){
        WorkPage workPage = new WorkPage(driver);
        workPage.clickOnFilter();

        Assert.assertTrue(workPage.clickOnFistFilterOption(), "Not able to click on first option of filter");
    }

    @Test(enabled = true)
    public void testFilterSecondOption(){
        WorkPage workPage = new WorkPage(driver);
        workPage.clickOnFilter();

        Assert.assertTrue(workPage.clickOnSecondfilterOption(), "Not able to click on second option of filter");
    }

    @Test(enabled = true)
    public void testFirstCardOnWorkPage(){
        WorkPage workPage = new WorkPage(driver);
        String expectedEmilcottPageTitle = "Greenlight™ Environmental Monitoring System - Fleet Studio";
        EmilcottPage emilcottPage = workPage.clickOnFirstCard();

        String actualEmilcottPageTitle = emilcottPage.emilcottPageTitle();

        Assert.assertEquals(actualEmilcottPageTitle, expectedEmilcottPageTitle, "Emilcott page is not being displayed");

    }

}
