package test;

import main.CheckoutOptions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckoutOptionsTest {

    @Test
    public void createConfirmPurchaseOptions() {
        assertEquals(CheckoutOptions.createConfirmPurchaseOptions(), "Enter 1 to purchase more\nEnter 2 to apply a discount code\nEnter 3 to confirm purchase\nEnter 4 to remove an item\nEnter 5 to change the quantity of an item");
    }

}
