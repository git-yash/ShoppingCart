package main;

import java.util.ArrayList;

public enum CheckoutOptions {
    PURCHASE_MORE(1),
    DISCOUNT_CODE(2),
    CONFIRM_CHECKOUT(3),
    REMOVE_ITEM(4);

    private final int option;

    CheckoutOptions(int option) {
        this.option = option;
    }

    public int getLevelCode() {
        return this.option;
    }

    public static String createConfirmPurchaseOptions() {
        ArrayList<String> options = new ArrayList<>();

        options.add("Enter " + CheckoutOptions.PURCHASE_MORE.getLevelCode() + " to purchase more");
        options.add("Enter " + CheckoutOptions.DISCOUNT_CODE.getLevelCode() + " to apply a discount code");
        options.add("Enter " + CheckoutOptions.CONFIRM_CHECKOUT.getLevelCode() + " to confirm purchase");
        options.add("Enter " + CheckoutOptions.REMOVE_ITEM.getLevelCode() + " to remove an item: ");

        return String.join(" | ", options);
    }
}
