package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicPageTest {

    // Create a ThreadLocal variable to hold the WebDriver instance for each thread to ensure thread safety when running tests in parallel
    private final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    // Getter method to retrieve the WebDriver instance from the ThreadLocal variable to be used in the test methods
    public WebDriver getDriver() {
        return driverThread.get();
    }

    // The setUp method is annotated with @BeforeMethod,
    // which means it will be executed before each test method in the class.
    // It initializes a new instance of the ChromeDriver and sets it to the ThreadLocal variable, ensuring that each test method has its own WebDriver instance.
    // reduce code duplication by centralizing the WebDriver initialization logic in one place,
    // making it easier to manage and maintain the WebDriver instances across multiple test methods.
    @BeforeMethod
    public void setUp() {
        // Create a new instance of the ChromeDriver and set it to the ThreadLocal variable
        driverThread.set(new ChromeDriver());
    }

    // The tearDown method is annotated with @AfterMethod, which means it will be executed after each test method in the class.
    // It retrieves the WebDriver instance from the ThreadLocal variable and calls the quit() method to close the browser after each test method.
    @AfterMethod
    public void tearDown() {
        // Quit the WebDriver instance to close the browser after each test method
        getDriver().quit();
    }

    @Test
    public void checkButtonAddBox() {   //input[name='my-password']
        // Navigate to the dynamic page
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");
        // Set an implicit wait of 10 seconds for the WebDriver
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Create a new WebElement instance to interact with the page elements and find the button with id "adder" using its XPath
        WebElement webElement = getDriver().findElement(By.id("adder"));
        // Click the button to add a new box to the page
        webElement.click();

        // Create a new WebDriverWait instance with a timeout of 5 seconds and wait until the new box with id "box0" is present on the page
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        // Wait until the new box with id "box0" is present on the page and assign it to the webElement variable
        webElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.id("box0"))
        );
        // Assert that the new box is displayed on the page
        String color = webElement.getCssValue("background-color");
        Color color1 = Color.fromString(color);
        // Assert that the background color of the new box is red
        Assert.assertEquals(color1.asHex(), "#ff0000");

    }

    @Test
    public void checkRevealNewInput() {
        // Navigate to the dynamic page
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/dynamic.html");
        // Set an implicit wait of 10 seconds for the WebDriver
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Create a new WebElement instance to interact with the page elements and find the button with id "reveal" using its id
        WebElement webElement;
        webElement = getDriver().findElement(By.id("reveal"));
        // Click the button to reveal a new input field on the page
        webElement.click();
        // Create a new WebDriverWait instance with a timeout of 5 seconds and wait until the new input field with id "revealed" is present on the page
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        webElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"revealed\"]"))
        );
        // Send the text "Ahmed" to the newly revealed input field
        webElement.sendKeys("Ahmed");
        // Assert that the value of the input field is "Ahmed"
        Assert.assertEquals(webElement.getAttribute("value"), "Ahmed");
    }

}
