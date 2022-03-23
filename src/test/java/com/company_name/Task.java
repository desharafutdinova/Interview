package com.company_name;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Task {

    WebDriver driver;

    public void sortByCategory(String categoryName){

        driver.findElement(By.xpath("//div[.='" + categoryName + "']")).click();
    }

    public void sortByTickerOrCompanyName(String name){

        WebElement search = driver.findElement(By.tagName("input"));
        search.sendKeys(name);
        }

    public void primarySecuritiesOn(){

        WebElement yesBtn = driver.findElement(By.xpath("//div[.='Yes']"));

        if(!yesBtn.getAttribute("class").contains("activeOn")){
            yesBtn.click();
        }
    }

    public void sortByCountry(String countryName){

        driver.findElement(By.xpath("//input[@placeholder='Filter by country name']")).sendKeys(countryName);

        driver.findElement(By.xpath("//i[@class='far fa-circle']")).click();
    }

    public void primarySecuritiesOff(){

        WebElement noBtn = driver.findElement(By.xpath("//div[.='No']"));

        if(!noBtn.getAttribute("class").contains("activeOff")){
            noBtn.click();
        }
    }



    @Test
    public void searchBoxTest() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://app.koyfin.com/home");

        String[] categories = {"Equities", "ETFs", "Mutual Funds", "Indices", "Bonds", "Forex", "Futures", "Economic"};
        String[] tickers = {"AAPL", "TSLA", "NVDA", "NFLX"};
        String[] companies = {"Tesla", "Nvidia", "Netflix"};
        String[] countries = {"Canada", "Mexico", "United States"};

        driver.findElement(By.xpath("//div [@class='console__label___FRSkP']")).click();

        // testing sorting by categories
        for (String category : categories) {
            sortByCategory(category);
            List<WebElement> results = driver.findElements(By.xpath("//div[@class='console-popup__ri___EC3x2']"));

            for (WebElement result : results) {
                switch (category) {
                    case "Equities":
                        Assert.assertEquals(result.getText(), "Equity");
                        break;
                    case "ETFs":
                        Assert.assertEquals(result.getText(), "ETF");
                        break;
                    case "Indices":
                        Assert.assertEquals(result.getText(), "Index");
                        break;
                    default:
                        Assert.assertEquals(result.getText(), category);
                }
            }
        }

        driver.findElement(By.xpath("//div[.='All']")).click();
        WebElement searchBox = driver.findElement(By.tagName("input"));

        //testing sorting by tickers
        for (String ticker : tickers) {
            sortByTickerOrCompanyName(ticker);
            List<WebElement> results = driver.findElements(By.xpath("//div[@class='console-popup__key___xEgl9']"));

            for (WebElement result : results) {
                assert result.getText().contains(ticker);
            }
            searchBox.clear();
        }

        for(String company: companies){
            sortByTickerOrCompanyName(company);
            List<WebElement> results = driver.findElements(By.xpath("//div[@class='console-popup__le___qnD2R']"));

            for(WebElement result: results){
                assert result.getText().toLowerCase().contains(company.toLowerCase());
            }

            searchBox.clear();
        }

        driver.findElement(By.xpath("//button[.='Options']")).click();

        //testing primary securities
        primarySecuritiesOn();
        primarySecuritiesOff();

        //testing sorting by country
        driver.findElement(By.xpath("//span[contains(text(),'countries')]")).click();
        WebElement countrySearch = driver.findElement(By.xpath("//input[@placeholder='Filter by country name']"));

        for (String country : countries) {
            sortByCountry(country);
            countrySearch.clear();
        }


        driver.quit();
    }

}




