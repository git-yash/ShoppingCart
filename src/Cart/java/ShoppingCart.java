package Cart.java;

import java.util.*;

public class ShoppingCart {
    List<Item> itemsPurchased = new ArrayList<Item>();
    List<Item> itemsInStock = new ArrayList<Item>();

    public ShoppingCart() {
        this.addItemsInStock();
    }

    public void askItems() {
        this.printItems();

        System.out.println("------------------------------------------------");
        int itemNumber = this.gatherIntInput("Enter item number to purchase: ", 4, 1);
        Item item = this.itemsInStock.get(itemNumber - 1);
        item.quantity = this.gatherIntInput("Quantity: ", null, 1);
        this.itemsPurchased.add(item);

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

    private void checkOut() {
        System.out.println("==========Checkout==========");

        double totalWithoutTax = 0;
        for (int i = 0; i < this.itemsPurchased.size(); i++) {
            Item item = this.itemsPurchased.get(i);
            item.printCheckoutItems();
            totalWithoutTax += item.getTotalPrice();
        }

        System.out.println();
        System.out.println("----------------------------");
        System.out.printf("Subtotal: $%.2f", totalWithoutTax);
        System.out.println();

        double taxTotal = totalWithoutTax * 0.0825;
        System.out.printf("Tax: $%.2f", taxTotal);
        System.out.println();

        System.out.printf("Total: $%.2f", totalWithoutTax + taxTotal);
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
}
