package main;

public class Item {
    public String title;
    public String description;
    public double price;
    public int quantity;
    private int discountPercent;

    public Item(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public void applyDiscount(DiscountCoupons discountCoupon) {
        this.discountPercent = DiscountCoupons.getDiscount(discountCoupon);
    }

    public double getDiscount() {
        if (this.discountPercent > 0) {
            double discount = this.discountPercent * this.quantity * this.price / 100;
            return Math.round(discount * 100.0) / 100.0;
        } else {
            return 0;
        }
    }

    public double getTotalPrice() {
        return this.quantity * this.price;
    }

    public String getItemDetail(int itemNumber) {
        return itemNumber + ". " + this.title + ": " + this.description + " | $" + this.price;
    }

    public String getCheckoutDetail(int itemNumber) {
        return String.format(itemNumber + ". (" + this.quantity + " x " + this.price + ") %.2f --- " + this.title, this.getTotalPrice());
    }
}
