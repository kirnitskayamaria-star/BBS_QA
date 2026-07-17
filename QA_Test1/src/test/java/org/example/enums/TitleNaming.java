package org.example.enums;

public enum TitleNaming {
    PRODUCTS("Products"),
    CART("Your Cart"),
    CHECKOUT("Checkout: Your Information"),
    CHECHOUT_OVERVIEW("Checkout: Overview"),
    CHECHOUT_COMPLETE("Checkout: Complete!");

    private final String displayName;

    TitleNaming(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
