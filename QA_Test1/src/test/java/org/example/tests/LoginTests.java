package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    private static final String login = "standard_user";
    private static final String password = "secret_sauce";

    @Test
    public void positiveLoginTest() {
        loginPage.open();
        loginPage.login(login, password);
        Assert.assertEquals(productsPage.getTitle(), "Products", "Products tab did not open.");
    }

    @DataProvider(name = "IncorrectLoginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"standard_user", "password", "Epic sadface: Username and password do not match any user in this service" },
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."  },
        };
    }

    @Test(dataProvider = "IncorrectLoginData")
    public void negativeLoginTests(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
