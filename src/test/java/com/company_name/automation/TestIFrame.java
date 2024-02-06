package com.company_name.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TestIFrame {

    // go to the website & validate that the title is ""

    // create an instance of chrome
    // full screen browser (maximize ())
    // driver. get https://www.w3schools.com/html/tryit.asp?filename=tryhtml_default
    // locate a header
    //check if a header is inside the iframe
    // switch to iframe
    // get the header (locate the el;emnt by tag or xpath)
    // validate exp header with actual (assertion)

    public static void getAHeader(String url){

        WebDriver driver  = new ChromeDriver();
       // driver.manage().window().maximize();

        // get to the website
        driver.navigate().to(url);
        driver.switchTo().frame("iframeResult");
        WebElement header = driver.findElement(By.tagName("h1"));
        String actualHeader = header.getText();
        driver.switchTo().defaultContent();

        String expectedResult = "This is a Heading";

        Assert.assertEquals(expectedResult, actualHeader);

        driver.quit();
    }

    public static void main(String[] args) {
        getAHeader("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_default");
    }


}
