package kata.supermarket.discounts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;

public class BuyOneGetOneFreeDiscount implements Discount {

    private final List<Item> items;
    private final Integer discountedProductId;

    public BuyOneGetOneFreeDiscount(Integer discountedProductId, List<Item> items) {
        assert discountedProductId != null;
        assert items != null;
        this.items = items;
        this.discountedProductId = discountedProductId;
    }

    @Override
    public BigDecimal getAmount() {
        final List<ItemByUnit> discountedItems = items
                .stream()
                .filter(item -> item instanceof ItemByUnit)
                .map(item -> (ItemByUnit) item)
                .filter(item -> item.itemProductId().equals(discountedProductId))
                .collect(Collectors.toList());
        if (discountedItems.size() < 2) {
            return new BigDecimal("0.00");
        }
        return discountedItems.get(0).price().multiply(new BigDecimal(discountedItems.size() / 2)).setScale(2, RoundingMode.HALF_UP);
    }
}
