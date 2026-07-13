package org.example.pages;

import org.example.user.User;
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

    public LoginPage open()  {
        driver.get(BASE_URL);
        return this;
    }

    public LoginPage login(User user) {
        fillInLoginField(user.getName());
        fillInPasswordField(user.getPassword());
        driver.findElement(submitButton).click();
        return this;
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void fillInLoginField(String login) {
        driver.findElement(usernameField).sendKeys(login);
    }

    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
}
