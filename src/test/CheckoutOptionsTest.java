package test;

import main.CheckoutOptions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckoutOptionsTest {

    @Test
    public void createConfirmPurchaseOptions() {
        assertEquals(CheckoutOptions.createConfirmPurchaseOptions(), "Enter 1 to purchase more | Enter 2 to apply a discount code | Enter 3 to confirm purchase | Enter 4 to remove an item: ");
    }

}
