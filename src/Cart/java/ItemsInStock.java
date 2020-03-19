package Cart.java;

import java.util.ArrayList;
import java.util.List;

public class ItemsInStock {
    private List<Item> itemsInStock = new ArrayList<Item>();

    public ItemsInStock() {
        this.addItemsInStock();
    }

    private void addItemsInStock() {
        this.itemsInStock.add(new Item("Gel Pen", "This is a gel pen", 3.99));
        this.itemsInStock.add(new Item("Stapler", "This is a stapler", 5.99));
        this.itemsInStock.add(new Item("Notepad", "This is a notepad", 6.99));
        this.itemsInStock.add(new Item("Iphone Charger", "This is an Iphone Charger", 12.99));
    }

    public void printItems() {
        for (int i = 0; i < this.itemsInStock.size(); i++) {
            this.itemsInStock.get(i).printItem(i + 1);
        }
    }

    public Item getItem(int itemNumber) {
        return this.itemsInStock.get(itemNumber - 1);
    }
}
