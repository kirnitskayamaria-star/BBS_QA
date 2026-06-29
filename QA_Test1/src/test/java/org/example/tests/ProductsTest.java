package org.example.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

public class ProductsTest extends BaseTest{
    @Test
    public void checkGoodsAdded() throws InterruptedException {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products", "Заголовок страницы не соответствует");
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(productsPage.isCounterDisplayed(), true, "Не появился индикатор количества товара");
        Assert.assertEquals(productsPage.checkCounterColor(),"rgba(226, 35, 26, 1)", "Неверный цвет корзины");
        Assert.assertEquals(productsPage.checkCounterValue(), "1", "Неверное количество товара");
    }
}
