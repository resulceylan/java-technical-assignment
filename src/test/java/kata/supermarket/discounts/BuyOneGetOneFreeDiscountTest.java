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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BuyOneGetOneFreeDiscountTest {

    private static final Integer BUY_ONE_GET_ONE_DISCOUNTED_PRODUCT_ID = MILK_PRODUCT_ID;


    @DisplayName("should apply buy one get one discount...")
    @MethodSource("basketProvidesTotalValue")
    @ParameterizedTest(name = "{0}")
    public void testBuyOneGetOneDiscount(String description, BigDecimal expectedDiscount, List<Item> items) {
        final Discount discount = new BuyOneGetOneFreeDiscount(BUY_ONE_GET_ONE_DISCOUNTED_PRODUCT_ID, items);
        assertEquals(expectedDiscount, discount.getAmount());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(noItems(),
                aSingleItemPricedPerUnit(),
                twoDiscountedItemPricedPerUnit(),
                threeDiscountedItemPricedPerUnit(),
                fourDiscountedItemPricedPerUnit(),
                twoNonDiscountedItemPricedPerUnit(),
                twoNonDiscountedOneDiscountedItemPricedPerUnit(),
                twoNonDiscountedTwoDiscountedItemPricedPerUnit(),
                allItemByWeight(),
                mixedItems());
    }

    private static Arguments noItems() {
        return Arguments.of("no items", new BigDecimal("0.00"), emptyList());
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single discounted item priced per unit", new BigDecimal("0.00"), singletonList(aPintOfMilk()));
    }

    private static Arguments twoDiscountedItemPricedPerUnit() {
        return Arguments.of("two discounted item priced per unit", new BigDecimal("0.49"), Arrays.asList(aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments threeDiscountedItemPricedPerUnit() {
        return Arguments.of("three discounted item priced per unit", new BigDecimal("0.49"), Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
    }

    private static Arguments fourDiscountedItemPricedPerUnit() {
        return Arguments.of("four discounted item priced per unit", new BigDecimal("0.98"), Arrays.asList(aPintOfMilk(), aPintOfMilk(), aPintOfMilk(), aPintOfMilk()));
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
