package com.selenium.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

public class CookieBasedTest {


    private final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    private WebDriver getDriver() {
        return driverThread.get();
    }

    @BeforeMethod
    public void setUp() {
        // Create a new instance of the ChromeDriver and set it to the ThreadLocal variable
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driverThread.set(driver);
    }

    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver instance to close the browser after each test method
        getDriver().quit();
    }

    @Test
    public void getCookies() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/cookie-background.html");
        WebElement element = getDriver().findElement(By.id("green-btn"));
        element.click();
        Cookie cookie = getDriver().manage().getCookieNamed("theme");
        Assert.assertEquals(cookie.getValue(), "lightgreen");

        ChromeDriver chromeDriver = (ChromeDriver) getDriver();
        try {
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "/screenshot/new.png");
            out.write(chromeDriver.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
