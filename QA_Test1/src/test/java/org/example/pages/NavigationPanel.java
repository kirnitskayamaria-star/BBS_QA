package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.example.pages.BasePage.DATA_TEST_PATTERN;

public class NavigationPanel {
    private final WebDriver driver;
    private final By cartLink = By.xpath("//a[@class='shopping_cart_link']");
    private final By continueShoppingButton = By.xpath(DATA_TEST_PATTERN.formatted("checkout"));
    private final By finishShoppingButton = By.xpath(DATA_TEST_PATTERN.formatted("finish"));

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Переходим на страницу корзины.")
    public void switchToCart() {
        driver.findElement(cartLink).click();
    }

    @Step("Переходим на страницу оформления заказа.")
    public void switchToCheckout() {
        driver.findElement(continueShoppingButton).click();
    }

    @Step("Переходим к финальной странице оформления заказа.")
    public void switchToFinish() {
        driver.findElement(finishShoppingButton).click();
    }
}
