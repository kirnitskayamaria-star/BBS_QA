package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final By usernameField = By.xpath("//*[@placeholder='Username']");
    private final By passwordField = By.xpath("//*[@placeholder='Password']");
    private final By submitButton = By.xpath("//input[@type='submit']");
    private final By errorMessage = By.xpath("(//h3[@data-test='error'])");
    WebDriver driver;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open()  {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String login, String password) {
        driver.findElement(usernameField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
