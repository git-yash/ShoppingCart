package Cart.java;

import java.util.Scanner;

public class ShoppingCart {
    public Item[] itemsPurchased;
    private Item[] itemsInStock;
    private int itemsPurchasedCount;
    private int maxItemsToPurchase = 10;

    public ShoppingCart() {
        this.itemsInStock = this.createItemsInStock();
        this.itemsPurchased = new Item[maxItemsToPurchase];
        this.itemsPurchasedCount = 0;
    }

    public void askItems() {
        this.printItems();

        int itemNumber = this.gatherIntInput("Enter item number to purchase: ", 4, 1);
        Item item = this.itemsInStock[itemNumber - 1];
        item.quantity = this.gatherIntInput("Quantity: ", null, 1);
        this.addItemToPurchase(item);

        boolean confirmCheckout = this.gatherBooleanInput("Enter 1 to buy more items or Enter 2 to go to checkout: ");
        if (confirmCheckout) {
            askItems();
        } else {
            checkOut();
        }
    }

    private boolean gatherBooleanInput(String message) {
        int result = this.gatherIntInput(message, 2, 1);
        return result == 1;
    }

    private int gatherIntInput(String message) {
        return this.gatherIntInput(message, null, null);
    }

    private int gatherIntInput(String message, Integer max) {
        return this.gatherIntInput(message, max, null);
    }

    private int gatherIntInput(String message, Integer max, Integer min) {
        Scanner kb = new Scanner(System.in);
        System.out.println(message);

        int result = 0;
        try {
            result = kb.nextInt();
        } catch (Exception ex) {
            this.showErrorMessage("Enter valid value");
            result = this.gatherIntInput(message, max, min);
        }

        if (max != null && result > max) {
            this.showErrorMessage("Enter a value less than or equal to " + max);
            result = this.gatherIntInput(message, max, min);
        }

        if (min != null && result < min) {
            this.showErrorMessage("Enter a value more than " + min);
            result = this.gatherIntInput(message, max, min);
        }

        return result;
    }

    private void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    private void printItems() {
        for (int i = 0; i < this.itemsInStock.length; i++) {
            this.itemsInStock[i].printItem(i + 1);
        }
    }

    private Item[] createItemsInStock() {
        Item[] items = new Item[4];

        items[0] = new Item("Gel Pen", "This is a gel pen", 3.99);
        items[1] = new Item("Stapler", "This is a stapler", 5.99);
        items[2] = new Item("Notepad", "This is a notepad", 6.99);
        items[3] = new Item("Iphone Charger", "This is an Iphone Charger", 12.99);

        return items;
    }

    private void checkOut() {
        System.out.println("==========Checkout==========");

        double totalWithoutTax = 0;
        for (int i = 0; i < this.itemsPurchasedCount; i++) {
            Item item = this.itemsPurchased[i];
            item.printCheckoutItems();
            totalWithoutTax += item.getTotalPrice();
        }

        System.out.println();
        System.out.println("----------------------------");
        System.out.printf("Subtotal: %.2f", totalWithoutTax);
        System.out.println();

        double taxTotal = totalWithoutTax * 0.0825;
        System.out.printf("Tax: %.2f", taxTotal);
        System.out.println();

        System.out.printf("Total: %.2f", totalWithoutTax + taxTotal);
        System.out.println();
        System.out.println("----------------------------");
        System.out.println();

        int confirmPurchase = this.gatherIntInput("Enter 1 to confirm purchase or Enter 2 to purchase more: ", 2, 1);
        if (confirmPurchase == 1) {
            System.out.println("Thank You for Shopping!");
        } else {
            askItems();
        }
    }

    private void addItemToPurchase(Item item) {
        if (itemsPurchasedCount == maxItemsToPurchase) {
            return;
        }
        this.itemsPurchased[itemsPurchasedCount] = item;
        this.itemsPurchasedCount++;
    }
}
