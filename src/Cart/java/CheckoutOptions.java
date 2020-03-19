package Cart.java;

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
}
