package kata.supermarket.discounts;

import kata.supermarket.DiscountComponent;
import kata.supermarket.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static kata.supermarket.helpers.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class SuperMarketDiscountComponentTest {

    @Test
    public void shouldCalculateDiscountForSingleItemType() {
        final DiscountComponent discountComponent = new SuperMarketDiscountComponent();
        discountComponent.addDiscount(MILK_PRODUCT_ID, DiscountType.BUY_ONE_GET_ONE);
        final List<Item> items = Arrays.asList(aPintOfMilk(), aPintOfMilk());
        assertEquals(new BigDecimal("0.49"), discountComponent.calculateTotalDiscount(items));
    }

    @Test
    public void shouldCalculateDiscountForMultipleItemsType() {
        final DiscountComponent discountComponent = new SuperMarketDiscountComponent();
        discountComponent.addDiscount(MILK_PRODUCT_ID, DiscountType.BUY_ONE_GET_ONE);
        discountComponent.addDiscount(DIGESTIVES_PRODUCT_ID, DiscountType.BUY_ONE_GET_ONE);
        final List<Item> items = Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPackOfDigestives(), aPackOfDigestives());
        assertEquals(new BigDecimal("2.04"), discountComponent.calculateTotalDiscount(items));
    }

}
