package com.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MouseInteractionTest {

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
    public void dragAndDrop() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        Actions actions = new Actions(getDriver());
        WebElement src = getDriver().findElement(By.id("draggable"));
        WebElement dest = getDriver().findElement(By.id("droppable"));
        actions.dragAndDrop(src, dest).perform();
        WebElement status = getDriver().findElement(By.id("drop-status"));
        Assert.assertTrue(status.getText().contains("dropped"));
    }

    @Test
    public void moveMouse() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        Actions actions = new Actions(getDriver());
        WebElement dest = getDriver().findElement(By.id("mouse-tracker"));
        actions.moveToElement(dest, 5,5).perform();
        WebElement loc = getDriver().findElement(By.id("relative-location"));
        Assert.assertTrue(loc.getText().contains("5, 5"));
    }

    @Test
    public void hoverMouse() {
        getDriver().navigate().to("https://www.selenium.dev/selenium/web/mouse_interaction.html");
        Actions actions = new Actions(getDriver());
        WebElement dest = getDriver().findElement(By.id("hover"));
        actions.moveToElement(dest).perform();
        WebElement status = getDriver().findElement(By.id("move-status"));
        Assert.assertTrue(status.getText().contains("hovered"));
    }
}
