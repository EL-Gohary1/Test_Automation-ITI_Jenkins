package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class PageWithFrameTest {

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
    public void switchToFrameThenToDefaultThenToNewWindow()
    {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");

        getDriver().switchTo().frame("myframe");
        WebElement element = getDriver().findElement(By.xpath("//div"));
        Assert.assertTrue(element.getText().contains("Simple page with simple test."));

        getDriver().switchTo().defaultContent();
        element = getDriver().findElement(By.linkText("Open new window"));
        element.click();

        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(20));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        String windowId = getDriver().getWindowHandle();
        for(String windowName : getDriver().getWindowHandles()) {
            String title = getDriver().switchTo().window(windowName).getTitle();
            if(Objects.equals(title, "Simple Page"))
            {
                windowId = windowName;
            }
        }

        getDriver().switchTo().window(windowId);
        element = getDriver().findElement(By.xpath("//div"));
        Assert.assertTrue(element.getText().contains("Simple page with simple test."));
    }



}
