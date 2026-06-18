package org.example;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private final By usernameField = By.xpath("//*[@placeholder='Username']");
    private final By passwordField = By.xpath("//*[@placeholder='Password']");
    private final By button = By.xpath("//input[@type='submit']");
    private final By productsTitle = By.xpath("(//span[@class='title'])");
    private final By errorMessage = By.xpath("(//h3[@data-test='error'])");

    @Test
    public void positiveLoginTest() {
        driver.findElement(usernameField).sendKeys("standard_user");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(button).click();
        Assert.assertEquals(driver.findElement(productsTitle).getText(), "Products", "Products tab did not open.");
    }

    @Test
    public void negativeLoginTest() {
        driver.findElement(usernameField).sendKeys("");
        driver.findElement(passwordField).sendKeys("secret_sauce");
        driver.findElement(button).click();
        Assert.assertEquals(driver.findElement(errorMessage).getText(), "Epic sadface: Username is required", "Incorrect or missing error message");
    }
}
