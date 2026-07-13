package org.example.pages;

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

    public void switchToCart() {
        driver.findElement(cartLink).click();
    }

    public String getElementTitle(By titleLocator) {
        return driver.findElement(titleLocator).getText();
    }

    public void switchToCheckout() {
        driver.findElement(continueShoppingButton).click();
    }

    public void switchToFinish() {
        driver.findElement(finishShoppingButton).click();
    }
}
