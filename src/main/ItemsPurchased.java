package main;

import java.util.ArrayList;
import java.util.List;

public class ItemsPurchased {
    private List<Item> itemsPurchased = new ArrayList<>();

    public double getTotalWithoutTax() {
        double totalWithoutTax = 0;
        for (Item item : this.itemsPurchased) {
            totalWithoutTax += item.getTotalPrice();
        }
        return totalWithoutTax;
    }

    public double getDiscountTotal() {
        double total = 0;
        for (Item item : this.itemsPurchased) {
            total += item.getDiscount();
        }
        return total;
    }

    public void printItems() {
        this.itemsPurchased.forEach((item -> System.out.println(item.getCheckoutDetail())));
    }

    public void purchaseItem(Item item) {
        this.itemsPurchased.add(item);
    }

    public void applyDiscount(String coupon) {
        this.itemsPurchased.forEach((item -> item.applyDiscount(this.getDiscountCoupon(coupon))));
    }

    public void askToRemove() {
        int itemNumber = GatherInput.gatherIntInput("Which item in the cart would you like to remove?: ", this.itemsPurchased.size(), 1);
        this.itemsPurchased.remove(itemNumber - 1);
    }

    public void printCheckout() {
        System.out.println("==========Checkout==========");

        double totalWithoutTax = this.getTotalWithoutTax();
        double discountTotal = this.getDiscountTotal();
        this.printItems();

        System.out.println();
        System.out.println("----------------------------");
        System.out.printf("Subtotal: $%.2f", totalWithoutTax);
        System.out.println();

        if (discountTotal > 0) {
            System.out.printf("Discount: - $%.2f", discountTotal);
            System.out.println();
        }

        double taxTotal = (totalWithoutTax - discountTotal) * 0.0825;
        System.out.printf("Tax: $%.2f", taxTotal);
        System.out.println();

        System.out.printf("Total: $%.2f", (totalWithoutTax - discountTotal) + taxTotal);
        System.out.println();
        System.out.println("----------------------------");
        System.out.println();
    }

    private DiscountCoupons getDiscountCoupon(String coupon) {
        try {
            return DiscountCoupons.valueOf(coupon);
        } catch (Exception ex) {
            return DiscountCoupons.NONE;
        }
    }
}
