package test;

import main.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    private Item item;

    @Before
    public void setup() {
        item = new Item("Gel Pen", "Gel pen desc", 10);
    }

    @Test
    public void itemDetail() {
        assertEquals(item.getItemDetail(1), "1. Gel Pen: Gel pen desc | $10.0");
    }

    @Test
    public void checkoutDetail() {
        // setup
        item.quantity = 1;

        // exercise
        assertEquals(item.getCheckoutDetail(), "(1 x 10.0) 10.00 --- Gel Pen");
    }
}

