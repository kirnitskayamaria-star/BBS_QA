package org.example.tests;

import org.example.enums.TitleNaming;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

import static org.example.enums.TitleNaming.CARTS;
import static org.example.enums.TitleNaming.CHECKOUT_FIRST;
import static org.example.user.UserFactory.withAdminPermission;

public class BasketTest extends BaseTest{
    @Test
    public void switchToBasket() {
        loginPage
                .open()
                .login(withAdminPermission());
        List<String> goodsList = List.of("Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Test.allTheThings() T-Shirt (Red)" );
        String productName = "Sauce Labs Bolt T-Shirt";
        for(String good : goodsList) {
            productsPage.addToCart(good);
        }
        productsPage.navigationPanel.switchToCart();
        Assert.assertEquals(basketPage.getTitle(),CARTS.getDisplayName(),"Заголовок корзины не соответствует");
        Assert.assertEquals(goodsList, basketPage.getProductsName(), "Список добавленных товаров не совпадает.");
        String formattedName = productName.toLowerCase().replace(" ", "-");
        basketPage.removeProduct(formattedName);
        Assert.assertFalse(basketPage.isProductDisplayed(productName), "Товар не был удален.");
        productsPage.navigationPanel.switchToCheckout();
        Assert.assertEquals(checkoutPage.getTitle(), CHECKOUT_FIRST.getDisplayName(), "Заголовок страницы оформления заказа не соответствует");
    }
}
