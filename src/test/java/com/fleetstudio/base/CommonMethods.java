package com.fleetstudio.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CommonMethods extends SetUp{

    public static void scrollToPixel(int pixelValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+pixelValue+")","");

    }

    public static void scrollToElement(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement element = driver.findElement(By.xpath("provide the xpath of element here"));
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollPageTillBottom(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

}
