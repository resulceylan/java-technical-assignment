package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;
    private final Integer id;

    public WeighedProduct(final Integer id, final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
        this.id = id;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }

    public Integer getId() {
        return id;
    }
}
