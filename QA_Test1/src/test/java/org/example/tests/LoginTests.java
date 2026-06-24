package org.example.tests;

import org.testng.Assert;
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

    @Test
    public void negativeEmptyLoginTest() {
        loginPage.open();
        loginPage.login("", password);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required", "Incorrect or missing error message");
    }

    @Test
    public void negativeEmptyPasswordTest() {
        loginPage.open();
        loginPage.login(login, "");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required", "Incorrect or missing error message");
    }

    @Test
    public void negativeIncorrectPasswordTest() {
        loginPage.open();
        loginPage.login(login, "password");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service", "Incorrect or missing error message");
    }

    @Test
    public void negativeLockedUserTest() {
        loginPage.open();
        loginPage.login("locked_out_user", password);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.", "Incorrect or missing error message");
    }
}
