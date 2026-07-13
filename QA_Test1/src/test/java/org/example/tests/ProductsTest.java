package org.example.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import static org.example.enums.TitleNaming.PRODUCTS;
import static org.example.user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest{

    @Test
    public void checkGoodsAdded() throws InterruptedException {
        loginPage
                .open()
                .login(withAdminPermission());
        Assert.assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(), "Заголовок страницы не соответствует");
        productsPage.addToCart(0);
        Assert.assertEquals(productsPage.isCounterDisplayed(), true, "Не появился индикатор количества товара");
        Assert.assertEquals(productsPage.checkCounterColor(),"rgb(226, 35, 26)", "Неверный цвет корзины");
        Assert.assertEquals(productsPage.checkCounterValue(), "1", "Неверное количество товара");
    }
}
