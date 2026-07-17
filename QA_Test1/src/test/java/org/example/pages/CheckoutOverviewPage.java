package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private final By goodsTitle = By.cssSelector(".inventory_item_name");
    private final By goodsPrice = By.cssSelector(".inventory_item_price");
    private final By totalPrice = By.cssSelector(".summary_subtotal_label");
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получаем названия товаров")
    public ArrayList<String> getProductsName() {
        List<WebElement> allProducts = driver.findElements(goodsTitle);
        ArrayList<String> names = new ArrayList<>();
        for(WebElement product: allProducts) {
            names.add(product.getText());
        }
        return names;
    }

    @Step("Подсчитываем суммарную стоимость всех товаров на странице.")
    public Double getPrices() {
        List<WebElement> allProducts = driver.findElements(goodsPrice);
        double totalSum = 0;
        for (int i = 0; i< allProducts.size(); i++) {
            String text = allProducts.get(i).getText();
            String cleanedText = text.replaceAll("[^0-9.]", "");
            Double price = Double.parseDouble(cleanedText);
            totalSum = price + totalSum;
        }
        return new BigDecimal(totalSum)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    @Step("Получаем финальную сумму заказа из корзины/чека.")
    public Double getTotalPrice() {
        String text = driver.findElement(totalPrice).getText();
        String cleanedText = text.replaceAll("[^0-9.]", "");
        return Double.parseDouble(cleanedText);
    }

    @Step("Получаем заголовок страницы.")
    public String getTitle() {
        return driver.findElement(PAGE_TITLE).getText();
    }
}
