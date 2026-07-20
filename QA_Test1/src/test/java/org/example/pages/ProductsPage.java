package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage{
    public static final String ADD_TO_CART =
            "//*[text() = '%s']//ancestor::div[@class = 'inventory_item']" +
                    "//child::button[text()='Add to cart']";
    private final By counter = By.xpath(DATA_TEST_PATTERN.formatted("shopping-cart-badge"));

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем заголовок страницы.")
    public String getTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }

    @Step("Добавляем товар в корзину.")
    public void addToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    @Step("Добавляем товар в корзину.")
    public void addToCart(int index) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(index).click();
    }

    @Step("Проверяем цвет фона счетчика.")
    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    @Step("Проверяем текущее значение счетчика корзины.")
    public String checkCounterValue() {
        return driver.findElement(counter).getText();
    }

    @Step("Проверяем, отображается ли счетчик товаров в корзине на странице.")
    public Boolean isCounterDisplayed() {
        return driver.findElement(counter).isDisplayed();
    }
}
