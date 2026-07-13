package org.example.pages;

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

    public String getTitle() {
        return navigationPanel.getElementTitle(PAGE_TITLE);
    }

    public void addToCart(String goodsName) {
        By addToCart = By.xpath(ADD_TO_CART.formatted(goodsName));
        driver.findElement(addToCart).click();
    }

    public void addToCart(int index) {
        driver.findElements(By.xpath("//*[text()='Add to cart']")).get(index).click();
    }

    public String checkCounterColor() {
        return driver.findElement(counter).getCssValue("background-color");
    }

    public String checkCounterValue() {
        return driver.findElement(counter).getText();
    }

    public Boolean isCounterDisplayed() {
        return driver.findElement(counter).isDisplayed();
    }
}
