package Cart.java;

import java.util.*;

public class ShoppingCart {
    List<Item> itemsInStock = new ArrayList<Item>();
    private ItemsPurchased itemsPurchased = new ItemsPurchased();

    public ShoppingCart() {
        this.addItemsInStock();
    }

    private void checkOut() {
        System.out.println("==========Checkout==========");

        double totalWithoutTax = itemsPurchased.getTotalWithoutTax();
        double discountTotal = itemsPurchased.getDiscountTotal();
        itemsPurchased.printItems();

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

        this.confirmPurchase();
    }

    public void askItems() {
        this.printItems();

        System.out.println("------------------------------------------------");
        int itemNumber = GatherInput.gatherIntInput("Enter item number to purchase: ", 4, 1);
        Item item = this.itemsInStock.get(itemNumber - 1);
        item.quantity = GatherInput.gatherIntInput("Quantity: ", null, 1);
        this.itemsPurchased.purchaseItem(item);

        boolean confirmCheckout = GatherInput.gatherBooleanInput("Enter 1 to buy more items or Enter 2 to go to checkout: ");
        if (confirmCheckout) {
            askItems();
        } else {
            checkOut();
        }
    }

    private void printItems() {
        for (int i = 0; i < this.itemsInStock.size(); i++) {
            this.itemsInStock.get(i).printItem(i + 1);
        }
    }

    private void addItemsInStock() {
        this.itemsInStock.add(new Item("Gel Pen", "This is a gel pen", 3.99));
        this.itemsInStock.add(new Item("Stapler", "This is a stapler", 5.99));
        this.itemsInStock.add(new Item("Notepad", "This is a notepad", 6.99));
        this.itemsInStock.add(new Item("Iphone Charger", "This is an Iphone Charger", 12.99));
    }

    private String createConfirmPurchaseOptions() {
        ArrayList<String> options = new ArrayList<String>();

        options.add("Enter " + CheckoutOptions.PURCHASE_MORE.getLevelCode() + " to purchase more");
        options.add("Enter " + CheckoutOptions.DISCOUNT_CODE.getLevelCode() + " to apply a discount code");
        options.add("Enter " + CheckoutOptions.CONFIRM_CHECKOUT.getLevelCode() + " to confirm purchase");
        options.add("Enter " + CheckoutOptions.REMOVE_ITEM.getLevelCode() + " to remove an item: ");

        return String.join(" | ", options);
    }

    private void confirmPurchase() {
        int checkoutOption = GatherInput.gatherIntInput(this.createConfirmPurchaseOptions(), 4, 1);
        if (checkoutOption == CheckoutOptions.CONFIRM_CHECKOUT.getLevelCode()) {
            System.out.println("Thank You for Shopping!");
        } else if (checkoutOption == CheckoutOptions.PURCHASE_MORE.getLevelCode()) {
            this.askItems();
        } else if (checkoutOption == CheckoutOptions.REMOVE_ITEM.getLevelCode()) {
            this.itemsPurchased.askToRemove();
            this.checkOut();
        } else {
            String discountCoupon = GatherInput.gatherStringInput("Enter discount coupon:");
            this.itemsPurchased.applyDiscount(discountCoupon);
            this.checkOut();
        }
    }
}
