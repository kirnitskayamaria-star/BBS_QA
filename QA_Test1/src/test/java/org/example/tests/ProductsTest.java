package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import org.testng.Assert;
import static org.example.enums.TitleNaming.PRODUCTS;
import static org.example.user.UserFactory.withAdminPermission;

@Epic("Тестирование онлайн-магазина Saucedemo")
@Feature("Каталог товаров (Products)")
public class ProductsTest extends BaseTest{

    @Story("Добавление товара в корзину с главной витрины")
    @TmsLink("BBS_QA")
    @Test(description = "Тест проверяет позитивный сценарий товара из списка", priority = 1)
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
