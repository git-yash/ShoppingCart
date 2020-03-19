package Cart.java;

public class Item {
    public int quantity;
    public String title;
    public String description;
    public double price;

    public Item(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
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
