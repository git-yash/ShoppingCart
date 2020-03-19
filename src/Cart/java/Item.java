package Cart.java;

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
            return this.discountPercent * this.quantity * this.price / 100;
        } else {
            return 0;
        }
    }

    public double getTotalPrice() {
        return this.quantity * this.price;
    }

    public void printItem(int itemNumber) {
        System.out.println(itemNumber + ". " + this.title + ": " + this.description + " | $" + this.price);
    }

    public void printCheckoutItems() {
        System.out.printf("(" + this.quantity + " x " + this.price + ") %.2f --- " + this.title + "\n", this.getTotalPrice());
    }
}
