package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private final By usernameField = By.xpath(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordField = By.xpath("//*[@placeholder='Password']");
    private final By submitButton = By.xpath("//input[@type='submit']");
    private final By errorMessage = By.xpath(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open()  {
        driver.get(BASE_URL);
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
