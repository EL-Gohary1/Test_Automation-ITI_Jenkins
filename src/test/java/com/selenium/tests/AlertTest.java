package com.selenium.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class AlertTest {


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
    public void alertTest() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/alerts.html");
        WebElement element = getDriver().findElement(By.id("alert"));
        element.click();
        Alert alert = getDriver().switchTo().alert();
        String text =alert.getText();
        Assert.assertEquals(text, "cheese");
        alert.accept();
    }

    @Test
    public void promptAlertTest() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/alerts.html");
        WebElement element = getDriver().findElement(By.id("prompt"));
        element.click();
        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys("hello");
        alert.accept();
    }

    @Test
    public void promptAlertInFrameTest() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/alerts.html");
        getDriver().switchTo().frame("iframeWithAlert");
        WebElement element = getDriver().findElement(By.linkText("click me"));
        element.click();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals(alert.getText(), "framed cheese");
        alert.accept();
        getDriver().switchTo().defaultContent();
    }
    @Test
    public void promptAlertInNewWindowTest() {

        getDriver().navigate().to("https://www.selenium.dev/selenium/web/alerts.html");
        WebElement element = getDriver().findElement(By.id("open-window-with-onload-alert"));
        String currentWindow = getDriver().getWindowHandle();
        element.click();

        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = getDriver().getWindowHandles();
        String redirectWindow = "";
        for (String windowName : allWindows) {
            if (!windowName.equals(currentWindow)) {
                redirectWindow = windowName;
                break;
            }
        }

        getDriver().switchTo().window(redirectWindow);

        Alert alert =getDriver().switchTo().alert();
        alert.accept();

        getDriver().switchTo().defaultContent();
        element =  getDriver().findElement(By.xpath("//p"));
        Assert.assertEquals(element.getText(), "Page with onload event handler");

        getDriver().switchTo().window(currentWindow);

    }

    @Test
    public void alertInSelectTest(){
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/alerts.html");
        WebElement element = getDriver().findElement(By.id("select"));
        Select select = new Select(element);
        select.selectByValue("2");
        Alert alert = getDriver().switchTo().alert();
        String text = alert.getText();
        Assert.assertEquals(text, "changed");
        alert.accept();
        getDriver().switchTo().defaultContent();
    }

}
