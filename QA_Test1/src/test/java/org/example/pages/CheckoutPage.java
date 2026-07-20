package org.example.pages;

import io.qameta.allure.Step;
import org.example.user.UserCheckout;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    private final By firstNameField = By.xpath(DATA_TEST_PATTERN.formatted("firstName"));
    private final By lastNameField = By.xpath(DATA_TEST_PATTERN.formatted("lastName"));
    private final By zipCodeField = By.xpath(DATA_TEST_PATTERN.formatted("postalCode"));
    private final By continueButton = By.xpath(DATA_TEST_PATTERN.formatted("continue"));
    private final By cancelButton = By.xpath(DATA_TEST_PATTERN.formatted("cancel"));
    private final By errorMessage = By.xpath(DATA_TEST_PATTERN.formatted("error"));
    private final String pagePath = "checkout-step-one.html";

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываем страницу оформления заказа.")
    public CheckoutPage open() {
        super.open(pagePath);
        return this;
    }

    @Step("Получаем заголовок страницы.")
    public String getTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Вводим данные покупателя.")
    public CheckoutPage checkoutCustomer(UserCheckout userCheckout) {
        fillInFirstNameField(userCheckout.getFirstName());
        fillInLastNameField(userCheckout.getLastName());
        fillInZipCodeField(userCheckout.getZipCode());
        driver.findElement(continueButton).click();
        return this;
    }

    @Step("Отменяем заказ.")
    public void cancelCheckout() {
        driver.findElement(cancelButton).click();
    }

    @Step("Заполняем поле ввода имени.")
    public void fillInFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    @Step("Заполняем поле ввода фамилии.")
    public void fillInLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    @Step("Заполняем поле ввода почтового индекса.")
    public void fillInZipCodeField(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    @Step("Проверяем цвет кнопки.")
    public String checkButtonColor() {
        return driver.findElement(continueButton).getCssValue("background-color");
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
}
