package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;
    private final Integer id;

    public Product(final Integer id, final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.id = id;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    public Integer getId() {
        return id;
    }
}
