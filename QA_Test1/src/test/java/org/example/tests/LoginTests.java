package org.example.tests;

import org.example.user.User;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.example.enums.TitleNaming.PRODUCTS;
import static org.example.user.UserFactory.*;

public class LoginTests extends BaseTest {
    @Test
    public void positiveLoginTest() {
        loginPage
                .open()
                .login(withAdminPermission());
        Assert.assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName(), "Заголовок страницы не соответствует.");
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

    @Test(dataProvider = "IncorrectLoginData")
    public void negativeLoginTests(User user, String errorMessage) {
        loginPage
                .open()
                .login(user);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
