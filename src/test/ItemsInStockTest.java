package test;

import main.ItemsInStock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ItemsInStockTest {
    private ItemsInStock itemsInStock;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        this.itemsInStock = new ItemsInStock();
    }

    @Test
    public void printItems() {
        // exercise
        itemsInStock.printItems();

        // post-conditions
        assertEquals("1. Gel Pen: This is a gel pen | $3.99\r\n" +
                "2. Stapler: This is a stapler | $5.99\r\n" +
                "3. Notepad: This is a notepad | $6.99\r\n" +
                "4. Iphone Charger: This is an Iphone Charger | $12.99\r\n", outContent.toString());
    }

    @Test
    public void getItem() {
        // exercise
        assertEquals(itemsInStock.getItem(1).title, "Gel Pen");
        assertEquals(itemsInStock.getItem(2).title, "Stapler");
        assertEquals(itemsInStock.getItem(3).title, "Notepad");
        assertEquals(itemsInStock.getItem(4).title, "Iphone Charger");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
