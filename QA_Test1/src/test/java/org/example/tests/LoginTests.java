package org.example.tests;
import io.qameta.allure.*;
import org.example.user.User;
import org.example.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.example.enums.TitleNaming.PRODUCTS;
import static org.example.user.UserFactory.*;

@Epic("Тестирование онлайн-магазина Saucedemo")
@Feature("Тестирование страницы авторизации.")
public class LoginTests extends BaseTest {

    @Story("Ввод персональных данных")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("BBS_QA")
    @Test(description = "Проверка корректной авторизации", priority = 1)
    public void positiveLoginTest() {
        loginPage
                .open()
                .login(withAdminPermission());
        Assert.assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(), "Заголовок страницы не соответствует");
    }

    @DataProvider(name = "IncorrectLoginData")
    public Object[][] loginData() {
        return new Object[][] {
                {withEmptyLoginPermission(), "Epic sadface: Username is required"},
                {withEmptyPasswordPermission(), "Epic sadface: Password is required"},
                {withIncorrectPasswordPermission(), "Epic sadface: Username and password do not match any user in this service" },
                {withLockedUserPermission(), "Epic sadface: Sorry, this user has been locked out."  },
        };
    }

    @Story("Ввод персональных данных")
    @TmsLink("BBS_QA")
    @Test(dataProvider = "IncorrectLoginData", description = "Проверка корректной авторизации", priority = 1)
    public void negativeLoginTests(User user, String errorMessage) {
        loginPage
                .open()
                .login(user);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
