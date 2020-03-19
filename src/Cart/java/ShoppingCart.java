package Cart.java;

public class ShoppingCart {
    private ItemsPurchased itemsPurchased = new ItemsPurchased();
    private ItemsInStock itemsInStock = new ItemsInStock();

    private void checkOut() {
        this.itemsPurchased.printCheckout();
        this.confirmPurchase();
    }

    public void askItems() {
        this.itemsInStock.printItems();

        System.out.println("------------------------------------------------");
        int itemNumber = GatherInput.gatherIntInput("Enter item number to purchase: ", 4, 1);
        Item item = this.itemsInStock.getItem(itemNumber);
        item.quantity = GatherInput.gatherIntInput("Quantity: ", null, 1);
        this.itemsPurchased.purchaseItem(item);

        boolean confirmCheckout = GatherInput.gatherBooleanInput("Enter 1 to buy more items or Enter 2 to go to checkout: ");
        if (confirmCheckout) {
            askItems();
        } else {
            checkOut();
        }
    }

    private void confirmPurchase() {
        int checkoutOption = GatherInput.gatherIntInput(CheckoutOptions.createConfirmPurchaseOptions(), 4, 1);
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
