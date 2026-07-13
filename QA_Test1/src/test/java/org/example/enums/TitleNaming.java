package org.example.enums;

public enum TitleNaming {
    PRODUCTS("Products"),
    CARTS("Your Cart"),
    CHECKOUT_FIRST("Checkout: Your Information"),
    CHECHOUT_SECOND("Checkout: Overview"),
    CHECHOUT_THIRD("Checkout: Complete!");

    private final String displayName;

    TitleNaming(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
