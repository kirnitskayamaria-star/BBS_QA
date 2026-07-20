package org.example.tests;

import io.qameta.allure.*;
import org.example.user.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static org.example.enums.TitleNaming.CHECHOUT_OVERVIEW;
import static org.example.user.UserFactory.withAdminPermission;

@Epic("Тестирование онлайн-магазина Saucedemo")
@Feature("Оформление заказа (Checkout)")
public class CheckoutOverviewTest extends BaseTest{

    @Story("Проверка состава и итоговой стоимости заказа перед оплатой")
    @TmsLink("BBS_QA")
    @Test(description = "Тест проверяет корректность данных на странице Checkout Overview.", priority = 1)
    public void checkoutShouldContainCorrectItemsFromCart() {
        loginPage
                .open()
                .login(withAdminPermission());
        List<String> goodsList = List.of("Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light", "Test.allTheThings() T-Shirt (Red)" );
        for(String good : goodsList) {
            productsPage.addToCart(good);
        }
        productsPage.navigationPanel.switchToCart();
        productsPage.navigationPanel.switchToCheckout();
        checkoutPage
                .checkoutCustomer(UserFactory.getValidCustomer());
        Assert.assertEquals(checkoutOverviewPage.getTitle(), CHECHOUT_OVERVIEW.getDisplayName(), "Заголовок страницы подтверждения товара не соответствует");
        Assert.assertEquals(goodsList, checkoutOverviewPage.getProductsName(), "Список добавленных товаров не совпадает.");
        Assert.assertEquals(checkoutOverviewPage.getPrices(), checkoutOverviewPage.getTotalPrice(), "Сумма товаров не соответствует");
    }
}
