package org.example.pages;

import io.qameta.allure.Step;
import org.example.user.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private final By usernameField = By.xpath(DATA_TEST_PATTERN.formatted("username"));
    private final By passwordField = By.xpath("//*[@placeholder='Password']");
    private final By submitButton = By.xpath("//input[@type='submit']");
    private final By errorMessage = By.xpath(DATA_TEST_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу авторизации.")
    public LoginPage open()  {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Логинимся под кредами пользователя.")
    public LoginPage login(User user) {
        fillInLoginField(user.getName());
        fillInPasswordField(user.getPassword());
        driver.findElement(submitButton).click();
        return this;
    }

    @Step("Проверяем отображение сообщения об ошибке.")
    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Получаем сообщение об ошибке.")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Заполняем поле ввода логина.")
    public void fillInLoginField(String login) {
        driver.findElement(usernameField).sendKeys(login);
    }

    @Step("Заполняем поле ввода пароля.")
    public void fillInPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
}
