package kata.supermarket.discounts;

import static java.util.Collections.*;
import static kata.supermarket.helpers.TestHelper.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import kata.supermarket.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BuyXForYDiscountTest {

    private static final Integer DISCOUNTED_PRODUCT_ID = MILK_PRODUCT_ID;


    @DisplayName("should apply buy one get one discount...")
    @MethodSource("buyOneGetOneValues")
    @ParameterizedTest(name = "{0}")
    public void testBuyOneGetOneDiscount(String description, BigDecimal expectedDiscount, List<Item> items) {
        final Discount buy2For1Discount = new BuyXForYDiscount(items, DISCOUNTED_PRODUCT_ID, 2, 1);
        assertEquals(expectedDiscount, buy2For1Discount.getAmount());
    }

    @DisplayName("should apply buy one get one discount...")
    @MethodSource("buy3For2DiscountValues")
    @ParameterizedTest(name = "{0}")
    public void test3For2Discount(String description, BigDecimal expectedDiscount, List<Item> items) {
        final Discount buy3For2Discount = new BuyXForYDiscount(items, DISCOUNTED_PRODUCT_ID, 3, 2);
        assertEquals(expectedDiscount, buy3For2Discount.getAmount());
    }

    @Test
    public void xxx() {
        final Discount discount = new BuyXForYDiscount(Arrays.asList(aPintOfMilk(),
                aPintOfMilk(),
                aPintOfMilk(),
                aPintOfMilk(),
                aPintOfMilk()
                ), DISCOUNTED_PRODUCT_ID, 5, 3);
        assertEquals(new BigDecimal("0.98"), discount.getAmount());
    }

    static Stream<Arguments> buyOneGetOneValues() {
        return Stream.of(noItems(),
                aSingleItemPricedPerUnit(new BigDecimal("0.00")),
                twoDiscountedItemPricedPerUnit(new BigDecimal("0.49")),
                threeDiscountedItemPricedPerUnit(new BigDecimal("0.49")),
                fourDiscountedItemPricedPerUnit(new BigDecimal("0.98")),
                twoNonDiscountedItemPricedPerUnit(),
                twoNonDiscountedOneDiscountedItemPricedPerUnit(),
                twoNonDiscountedTwoDiscountedItemPricedPerUnit(),
                allItemByWeight(),
                mixedItems());
    }

    static Stream<Arguments> buy3For2DiscountValues() {
        return Stream.of(noItems(),
                aSingleItemPricedPerUnit(new BigDecimal("0.00")),
                twoDiscountedItemPricedPerUnit(new BigDecimal("0.00")),
                threeDiscountedItemPricedPerUnit(new BigDecimal("0.49")),
                fourDiscountedItemPricedPerUnit(new BigDecimal("0.49")));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", new BigDecimal("0.00"), emptyList());
    }

    private static Arguments aSingleItemPricedPerUnit(BigDecimal expectedDiscount) {
        return Arguments.of("a single discounted item priced per unit", expectedDiscount, singletonList(aPintOfMilk()));
    }

    private static Arguments twoDiscountedItemPricedPerUnit(BigDecimal expectedDiscount) {
        return Arguments.of("two discounted item priced per unit", expectedDiscount, Arrays.asList(aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments threeDiscountedItemPricedPerUnit(BigDecimal expectedDiscount) {
        return Arguments.of("three discounted item priced per unit", expectedDiscount, Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments fourDiscountedItemPricedPerUnit(BigDecimal expectedDiscount) {
        return Arguments.of("four discounted item priced per unit", expectedDiscount, Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments twoNonDiscountedItemPricedPerUnit() {
        return Arguments.of("two non discounted item priced per unit", new BigDecimal("0.00"), Arrays.asList(aPackOfDigestives(), aPackOfDigestives()));
    }

    private static Arguments twoNonDiscountedOneDiscountedItemPricedPerUnit() {
        return Arguments.of("two non discounted one discounted item priced per unit", new BigDecimal("0.00"), Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments twoNonDiscountedTwoDiscountedItemPricedPerUnit() {
        return Arguments.of("two non discounted two discounted item priced per unit", new BigDecimal("0.49"), Arrays.asList(aPackOfDigestives(), aPackOfDigestives(), aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments allItemByWeight() {
        return Arguments.of("all item by weight", new BigDecimal("0.00"), singletonList(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments mixedItems() {
        return Arguments.of("mixed items", new BigDecimal("0.49"), Arrays.asList(
                aPintOfMilk(),
                aPackOfDigestives(),
                twoFiftyGramsOfAmericanSweets(),
                twoHundredGramsOfPickAndMix(),
                aPintOfMilk(),
                aPackOfDigestives(),
                twoFiftyGramsOfAmericanSweets(),
                twoHundredGramsOfPickAndMix()));
    }
}
