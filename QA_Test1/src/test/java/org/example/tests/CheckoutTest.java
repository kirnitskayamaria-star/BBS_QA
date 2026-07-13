package org.example.tests;

import org.example.pages.LoginPage;
import org.example.user.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.enums.TitleNaming.CHECHOUT_SECOND;
import static org.example.enums.TitleNaming.CHECHOUT_THIRD;
import static org.example.user.UserFactory.getValidCustomer;

public class CheckoutTest extends BaseTest{
    @Test
    public void continueShopping() {
        new LoginPage(driver).open().login(UserFactory.withAdminPermission());
        checkoutPage
                .open()
                .checkoutCustomer(getValidCustomer());
        Assert.assertEquals(checkoutPage.getTitle(), CHECHOUT_SECOND.getDisplayName(), "Заголовок страницы подтверждения товара не соответствует");
        checkoutPage.navigationPanel.switchToFinish();
        Assert.assertEquals(checkoutPage.getTitle(), CHECHOUT_THIRD.getDisplayName(), "Заголовок страницы успешного оформления товара не соответствует");
    }
}
