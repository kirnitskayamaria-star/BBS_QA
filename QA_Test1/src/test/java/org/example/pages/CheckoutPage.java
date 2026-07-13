package org.example.pages;

import org.example.user.UserCheckout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{
    private final By firstNameField = By.xpath(DATA_TEST_PATTERN.formatted("firstName"));
    private final By lastNameField = By.xpath(DATA_TEST_PATTERN.formatted("lastName"));
    private final By zipCodeField = By.xpath(DATA_TEST_PATTERN.formatted("postalCode"));
    private final By continueButton = By.xpath(DATA_TEST_PATTERN.formatted("continue"));
    private final String pagePath = "checkout-step-one.html";

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage open() {
        super.open(pagePath);
        return this;
    }

    public String getTitle() {
        return navigationPanel.getElementTitle(PAGE_TITLE);
    }

    public CheckoutPage checkoutCustomer(UserCheckout userCheckout) {
        fillInFirstNameField(userCheckout.getFirstName());
        fillInLastNameField(userCheckout.getLastName());
        fillInZipCodeField(userCheckout.getZipCode());
        driver.findElement(continueButton).click();
        return this;
    }

    public void fillInFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void fillInLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void fillInZipCodeField(String zipCode) {
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

}
