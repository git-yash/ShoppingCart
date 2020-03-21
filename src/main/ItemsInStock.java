package main;

import java.util.ArrayList;
import java.util.List;

public class ItemsInStock {
    private List<Item> itemsInStock = new ArrayList<>();

    public ItemsInStock() {
        this.addItemsInStock();
    }

    public void printItems() {
        for (int i = 0; i < this.itemsInStock.size(); i++) {
            System.out.println(this.itemsInStock.get(i).getItemDetail(i + 1));
        }
    }

    public Item getItem(int itemNumber) {
        return this.itemsInStock.get(itemNumber - 1);
    }

    private void addItemsInStock() {
        this.itemsInStock.add(new Item("Gel Pen", "This is a gel pen", 3.99));
        this.itemsInStock.add(new Item("Stapler", "This is a stapler", 5.99));
        this.itemsInStock.add(new Item("Notepad", "This is a notepad", 6.99));
        this.itemsInStock.add(new Item("Iphone Charger", "This is an Iphone Charger", 12.99));
    }
}
