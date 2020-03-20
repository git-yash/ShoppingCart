package main;

public enum DiscountCoupons {
    DISCOUNT10,
    DISCOUNT20,
    DISCOUNT30,
    NONE;

    public static int getDiscount(DiscountCoupons discountCoupons) {
        switch (discountCoupons) {
            case DISCOUNT10:
                return 10;
            case DISCOUNT20:
                return 20;
            case DISCOUNT30:
                return 30;
            case NONE:
            default:
                return 0;
        }
    }
}
