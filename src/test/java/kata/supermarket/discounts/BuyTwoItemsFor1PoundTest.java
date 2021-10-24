package kata.supermarket.discounts;

import kata.supermarket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static kata.supermarket.helpers.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

public class BuyTwoItemsFor1PoundTest {

    private static final Integer DISCOUNTED_PRODUCT_ID = DIGESTIVES_PRODUCT_ID;

    @DisplayName("should apply buy two for 1£ discount...")
    @MethodSource("getArguments")
    @ParameterizedTest(name = "{0}")
    public void testBuyTwoItemsFor1Pound(String description, BigDecimal expectedDiscount, List<Item> items) {
        final Discount discount = new BuyTwoItemsFor1Pound(DISCOUNTED_PRODUCT_ID, items);
        assertEquals(expectedDiscount, discount.getAmount());
    }

    static Stream<Arguments> getArguments() {
        return Stream.of(noItems(),
                aSingleItemPricedPerUnit(),
                twoDiscountedItemPricedPerUnit());
    }

    private static Arguments noItems() {
        return Arguments.of("no items", new BigDecimal("0.00"), emptyList());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single discounted item priced per unit", new BigDecimal("0.00"), singletonList(aPackOfDigestives()));
    }

    private static Arguments twoDiscountedItemPricedPerUnit() {
        return Arguments.of("two discounted item priced per unit", new BigDecimal("2.10"), Arrays.asList(aPackOfDigestives(), aPackOfDigestives()));
    }

}
