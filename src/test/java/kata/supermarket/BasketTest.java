package kata.supermarket;

import kata.supermarket.discounts.DiscountType;
import kata.supermarket.discounts.SuperMarketDiscountComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static kata.supermarket.helpers.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource("basketProvidesTotalValue")
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket(new SuperMarketDiscountComponent());
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    @DisplayName("basket provides its total value with buy one get one discount when containing...")
    @MethodSource("basketProvidesTotalValueWithBuyOneGetOneDiscount")
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValueWithBuyOneGetOneDiscount(String description, String expectedTotal, Iterable<Item> items) {
        final DiscountComponent discountComponent = new SuperMarketDiscountComponent();
        discountComponent.addDiscount(MILK_PRODUCT_ID, DiscountType.BUY_ONE_GET_ONE);
        final Basket basket = new Basket(discountComponent);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    static Stream<Arguments> basketProvidesTotalValueWithBuyOneGetOneDiscount() {
        return Stream.of(
                noItems(),
                twoDiscountedPriceByUnitItems()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Arguments twoDiscountedPriceByUnitItems() {
        return Arguments.of("two discounted price by unit items", "0.49", Arrays.asList(aPintOfMilk(), aPintOfMilk()));
    }
}
