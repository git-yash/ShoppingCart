package Cart.java;

import java.util.ArrayList;
import java.util.List;

public class ItemsPurchased {
    private List<Item> itemsPurchased = new ArrayList<Item>();

    public double getTotalWithoutTax() {
        double totalWithoutTax = 0;
        for (int i = 0; i < this.itemsPurchased.size(); i++) {
            Item item = this.itemsPurchased.get(i);
            totalWithoutTax += item.getTotalPrice();
        }
        return totalWithoutTax;
    }

    public double getDiscountTotal() {
        double total = 0;
        for (int i = 0; i < this.itemsPurchased.size(); i++) {
            Item item = this.itemsPurchased.get(i);
            total += item.getDiscount();
        }
        return total;
    }

    public void printItems() {
        this.itemsPurchased.forEach((item -> item.printCheckoutItems()));
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

    private DiscountCoupons getDiscountCoupon(String coupon) {
        try {
            return DiscountCoupons.valueOf(coupon);
        } catch (Exception ex) {
            return DiscountCoupons.NONE;
        }
    }
}
