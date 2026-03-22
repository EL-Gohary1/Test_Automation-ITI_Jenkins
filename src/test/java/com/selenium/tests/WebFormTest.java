package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebFormTest {

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
    public void checkFormSubmittedMessage() {
        // Navigate to the web form page
        getDriver().get("https://www.selenium.dev/selenium/web/web-form.html");
        // Create a new WebElement instance to interact with the form elements
        WebElement webElement;
        // Find the input field using its XPath and assign it to the webElement variable
        webElement = getDriver().findElement(By.xpath("//input[@id='my-text-id']"));
        // Send the text "AHMED" to the input field
        webElement.sendKeys("AHMED");
        // Find the password field using its cssSelector and assign it to the webElement variable
        webElement = getDriver().findElement(By.cssSelector("input[name='my-password']"));
        // Send the text "password" to the password field
        webElement.sendKeys("password");
        // Find the select dropdown using its cssSelector and assign it to the webElement variable
        webElement = getDriver().findElement(By.cssSelector("select[name='my-select']"));
        // Create a new Select instance using the webElement and select the option with value "3"
        Select dropDownSelect = new Select(webElement);
        dropDownSelect.selectByValue("3");
        // Find the text input using its cssSelector and assign it to the webElement variable
        webElement = getDriver().findElement(By.cssSelector("input[list='my-options']"));
        webElement.sendKeys("New York");
        // Find the checkbox using its id and assign it to the webElement variable
        webElement = getDriver().findElement(By.id("my-check-1"));
        if (webElement.isSelected()) {
            webElement.click();
        }
        // Find the checkbox using its id and assign it to the webElement variable
        webElement = getDriver().findElement(By.id("my-check-2"));
        if (!webElement.isSelected()) {
            webElement.click();
        }
        // Find the radio button using its id and assign it to the webElement variable
        webElement = getDriver().findElement(By.id("my-radio-2"));
        if (webElement.isEnabled()) {
            webElement.click();
        }

        webElement = getDriver().findElement(By.cssSelector("input[name='my-colors']"));
        webElement.sendKeys("#00ff00");

        webElement = getDriver().findElement(By.cssSelector("input[name='my-range']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        jsExecutor.executeScript("arguments[0].value = 10;", webElement);

        Actions actions = new Actions(getDriver());
        actions.clickAndHold(webElement).moveByOffset(100, 0).release().perform();

        // Find the submit button using its XPath and assign it to the webElement variable
        webElement = getDriver().findElement(By.xpath("//button[text()='Submit']"));
        webElement.click();
        // Find the heading element using its XPath and assign it to the webElement variable

        WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleContains("target page"));
        webElement = getDriver().findElement(By.xpath("//h1"));
        String myString = webElement.getText();
        // Assert that the text of the heading element is "Form submitted"
        Assert.assertEquals(myString, "Form submitted");

    }

    @Test
    public void checkDisabledInputIsDisable() {
        // Navigate to the web form page
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/web-form.html");
        // Find the input field using its XPath and assign it to the webElement variable
        WebElement webElement = getDriver().findElement(By.xpath("//input[@name='my-disabled']"));
        // Assert that the input field is disabled
        Assert.assertFalse(webElement.isEnabled());

    }

    @Test
    public void checkReadonlyInput() {
        // Navigate to the web form page
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/web-form.html");
        // Find the input field using its XPath and assign it to the webElement variable
        WebElement webElement = getDriver().findElement(By.xpath("//input[@name='my-readonly']"));
        // Assert that the input field is read-only by checking if the "readonly" attribute is present
        Assert.assertNotNull(webElement.getAttribute("readonly"));
    }

}
