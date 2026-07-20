package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static org.example.enums.TitleNaming.*;
import static org.example.user.UserFactory.withAdminPermission;

@Epic("Тестирование онлайн-магазина Saucedemo")
@Feature("Управление корзиной покупок")
public class BasketTest extends BaseTest{

    @Story("Добавление, удаление товаров и переход к оформлению заказа")
    @TmsLink("BBS_QA")
    @Test(description = "Тест проверяет последовательное добавление трех товаров в корзину,удаление одного из них и успешный переход на экран ввода данных")
    public void basketTest() {
        loginPage
                .open()
                .login(withAdminPermission());
        List<String> goodsList = List.of("Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Test.allTheThings() T-Shirt (Red)" );
        String productName = "Sauce Labs Bolt T-Shirt";
        for(String good : goodsList) {
            productsPage.addToCart(good);
        }
        productsPage.navigationPanel.switchToCart();
        Assert.assertEquals(basketPage.getTitle(),CART.getDisplayName(),"Заголовок корзины не соответствует");
        Assert.assertEquals(goodsList, basketPage.getProductsName(), "Список добавленных товаров не совпадает.");
        String formattedName = productName.toLowerCase().replace(" ", "-");
        basketPage.removeProduct(formattedName);
        Assert.assertFalse(basketPage.isProductDisplayed(productName), "Товар не был удален.");
        productsPage.navigationPanel.switchToCheckout();
        Assert.assertEquals(checkoutPage.getTitle(), CHECKOUT.getDisplayName(), "Заголовок страницы оформления заказа не соответствует");
    }
}
