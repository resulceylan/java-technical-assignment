package kata.supermarket.helpers;

import kata.supermarket.Item;
import kata.supermarket.Product;
import kata.supermarket.WeighedProduct;

import java.math.BigDecimal;

public class TestHelper {
    public static final Integer MILK_PRODUCT_ID = 1;
    public static final Integer DIGESTIVES_PRODUCT_ID = 2;
    public static final Integer AMERICAN_SWEET_PRODUCT_ID = 3;
    public static final Integer PICK_AND_MIX_PRODUCT_ID = 4;

    public static Item aPintOfMilk() {
        return new Product(MILK_PRODUCT_ID, new BigDecimal("0.49")).oneOf();
    }

    public static Item aPackOfDigestives() {
        return new Product(DIGESTIVES_PRODUCT_ID, new BigDecimal("1.55")).oneOf();
    }

    public static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(AMERICAN_SWEET_PRODUCT_ID, new BigDecimal("4.99"));
    }

    public static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    public static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(PICK_AND_MIX_PRODUCT_ID, new BigDecimal("2.99"));
    }

    public static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}
