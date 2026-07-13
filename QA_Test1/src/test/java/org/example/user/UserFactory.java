package org.example.user;

import org.example.utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.password"));
    }
    public static User withLockedUserPermission() {
        return new User(PropertyReader.getProperty("saucedemo.locked_user"),
                PropertyReader.getProperty("saucedemo.password"));
    }
    public static User withEmptyLoginPermission() {
        return new User("",
                PropertyReader.getProperty("saucedemo.incorrect_password"));
    }
    public static User withEmptyPasswordPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                "");
    }
    public static User withIncorrectPasswordPermission() {
        return new User(PropertyReader.getProperty("saucedemo.user"),
                PropertyReader.getProperty("saucedemo.incorrect_password"));
    }

    public static UserCheckout getValidCustomer() {
        return new UserCheckout(PropertyReader.getProperty("saucedemo.firstName"),
                PropertyReader.getProperty("saucedemo.lastName"),
                PropertyReader.getProperty("saucedemo.zipCode"));
    }
}

