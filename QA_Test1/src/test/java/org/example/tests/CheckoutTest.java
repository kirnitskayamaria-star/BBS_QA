package org.example.tests;

import io.qameta.allure.*;
import org.example.user.UserCheckout;
import org.example.user.UserFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.example.enums.TitleNaming.*;
import static org.example.user.UserFactory.*;

@Epic("Тестирование онлайн-магазина Saucedemo")
@Feature("Оформление заказа (Checkout)")
public class CheckoutTest extends BaseTest{

    @Story("Успешное заполнение данных и завершение покупки")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("BBS_QA")
    @Test(description = "Ввод валидных данных покупателя и успешный переход на финальный экран подтверждения")
    public void positiveCheckout() {
        loginPage
                .open()
                .login(UserFactory.withAdminPermission());
        checkoutPage
                .open();
        Assert.assertEquals(checkoutPage.checkButtonColor(),"rgba(61, 220, 145, 1)", "Неверный цвет кнопки");
        checkoutPage.checkoutCustomer(UserFactory.getValidCustomer());
        checkoutPage.navigationPanel.switchToFinish();
        Assert.assertEquals(checkoutPage.getTitle(), CHECHOUT_COMPLETE.getDisplayName(), "Заголовок страницы успешного оформления товара не соответствует");
    }

    @Story("Ограничение доступа к оформлению без авторизации")
    @TmsLink("BBS_QA")
    @Test(description = "Проверка безопасности: попытка открыть страницу чекаута напрямую по URL", priority = 1)
    public void checkoutWithoutLogin() {
        checkoutPage.open();
        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Epic sadface: You can only access '/checkout-step-one.html' when you are logged in.", "Сообщение об ограничении доступа не совпадает");
    }

    @DataProvider(name = "IncorrectCheckoutData")
    public Object[][] checkoutData() {
        return new Object[][] {
                {getEmptyFirstNameCustomer(), "Error: First Name is required"},
                {getEmptyLastNameCustomer(), "Error: Last Name is required"},
                {getEmptyZipCodeCustomer(), "Error: Postal Code is required" },
        };
    }

    @Story("Валидация обязательных полей формы покупателя")
    @TmsLink("BBS_QA")
    @Test(dataProvider = "IncorrectCheckoutData", description = "Ввод невалидных данных покупателя")
    public void negativeLoginTests(UserCheckout user, String errorMessage) {
        loginPage
                .open()
                .login(UserFactory.withAdminPermission());
        checkoutPage
                .open()
                .checkoutCustomer(user);
        Assert.assertTrue(checkoutPage.isErrorDisplayed());
        Assert.assertEquals(checkoutPage.getErrorMessage(), errorMessage);
    }

    @Story("Отмена оформления заказа")
    @TmsLink("BBS_QA")
    @Test(description = "Проверка логики отказа от покупки", priority = 1)
    public void cancelCheckoutTest() {
        loginPage
                .open()
                .login(withAdminPermission());
        checkoutPage
                .open()
                .cancelCheckout();
        Assert.assertEquals(basketPage.getTitle(), CART.getDisplayName(),"Заголовок корзины не соответствует");
    }
}
