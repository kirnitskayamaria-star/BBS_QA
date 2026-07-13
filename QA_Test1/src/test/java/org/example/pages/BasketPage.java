package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends BasePage{
    private final By goodsTitle = By.cssSelector(".inventory_item_name");


    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return navigationPanel.getElementTitle(PAGE_TITLE);
    }

    public ArrayList<String> getProductsName() {
        List<WebElement> allProducts = driver.findElements(goodsTitle);

        ArrayList<String> names = new ArrayList<>();

        for(WebElement product: allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    public void removeProduct(String productName) {
        driver.findElement(By.xpath(REMOVE_BUTTON.formatted(productName))).click();
    }

    public boolean isProductDisplayed(String productName) {
        for(String name : getProductsName()) {
            name.equalsIgnoreCase(productName);
            if (name.equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

}
