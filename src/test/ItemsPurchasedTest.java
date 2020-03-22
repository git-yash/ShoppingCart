package test;

import main.DiscountCoupons;
import main.Item;
import main.ItemsPurchased;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ItemsPurchasedTest {
    private ItemsPurchased itemsPurchased;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        this.itemsPurchased = new ItemsPurchased();
    }

    @Test
    public void getTotalWithoutTax() {
        // setup
        this.itemsPurchased.purchaseItem(createMockItem(9.99, 2));
        this.itemsPurchased.purchaseItem(createMockItem(19.99, 4));

        // exercise
        double totalWithoutTax = this.itemsPurchased.getTotalWithoutTax();
        assertEquals(totalWithoutTax, 99.94, 0);
    }

    @Test
    public void askToRemoveTest() {
        // setup
        this.itemsPurchased.purchaseItem(createMockItem(9.99, 2));
        this.itemsPurchased.purchaseItem(createMockItem(19.99, 1));

        // exercise
    }

    @Test
    public void applyDiscountTest() {
        // setup
        this.itemsPurchased.purchaseItem(createMockItem(9.99, 2));
        this.itemsPurchased.purchaseItem(createMockItem(19.99, 1));

        // pre-conditions
        assertEquals(0, this.itemsPurchased.getDiscountTotal(), 0);

        // exercise
        this.itemsPurchased.applyDiscount("INVALID_DISCOUNT10");

        // post-conditions
        assertEquals(0, this.itemsPurchased.getDiscountTotal(), 0);

        // exercise
        this.itemsPurchased.applyDiscount("DISCOUNT10");

        // post-conditions
        assertEquals(4, this.itemsPurchased.getDiscountTotal(), 0);
    }

    @Test
    public void printCheckoutTest() {
        // setup
        this.itemsPurchased.purchaseItem(createMockItem(9.99, 2));
        Item item2 = createMockItem(19.99, 4);
        item2.applyDiscount(DiscountCoupons.DISCOUNT20);
        this.itemsPurchased.purchaseItem(item2);

        // exercise
        this.itemsPurchased.printCheckout();

        // post-conditions
        assertEquals(outContent.toString(), "==========Checkout==========\n" +
                "(2 x 9.99) 19.98 --- Item\n" +
                "(4 x 19.99) 79.96 --- Item\n" +
                "\n" +
                "----------------------------\n" +
                "Subtotal: $99.94\n" +
                "Discount: - $15.99\n" +
                "Tax: $6.93\n" +
                "Total: $90.88\n" +
                "----------------------------\n" +
                "\n");
    }

    @Test
    public void getDiscountTotalTest() {
        // setup
        this.itemsPurchased.purchaseItem(createMockItem(9.99, 2));
        Item item2 = createMockItem(19.99, 4);
        item2.applyDiscount(DiscountCoupons.DISCOUNT20);
        this.itemsPurchased.purchaseItem(item2);

        // exercise
        double discountTotal = this.itemsPurchased.getDiscountTotal();
        assertEquals(discountTotal, 15.99, 0);
    }

    private Item createMockItem(double price, int quantity) {
        Item item = new Item("Item", "Desc", price);
        item.quantity = quantity;
        return item;
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
