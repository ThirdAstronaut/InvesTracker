package com.example.demo.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import com.example.demo.service.CoinMarketService;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;

public class SearchSiteTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private List<String> coinNames;



    @Before
    public void setUp() throws Exception {
        coinNames = new ArrayList<>();
        System.setProperty("webdriver.gecko.driver","D:\\Spring Boot\\geckodriver.exe");
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8088/";
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        coinNames.add("bitcoin");
        coinNames.add("xrp");
        coinNames.add("ethereum");
        coinNames.add("Stellar");
        coinNames.add("Litecoin");

    }

    @Test
    public void testSearchBar() {
        for(String name : coinNames) {
            driver.get("http://localhost:8088/search");
            assertEquals("InvesTracker - search", driver.getTitle());
            driver.findElement(By.id("coinName")).click();
            driver.findElement(By.id("coinName")).clear();
            driver.findElement(By.id("coinName")).sendKeys(name);
            driver.findElement(By.id("goBtn")).click();
            assertEquals("http://localhost:8088/coin?coinName="+name, driver.getCurrentUrl());
        }
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
 /*       String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }*/
//        driver.manage().deleteAllCookies();

    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}