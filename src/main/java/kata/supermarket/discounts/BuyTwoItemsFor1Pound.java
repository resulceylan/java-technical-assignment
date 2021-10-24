package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class BuyTwoItemsFor1Pound implements Discount {

    private final List<ItemByUnit> items;

    public BuyTwoItemsFor1Pound(Integer discountedProductId, List<Item> items) {
        this.items = items
                .stream()
                .filter(item -> item.itemProductId() == discountedProductId)
                .filter(item -> item instanceof ItemByUnit)
                .map(item -> (ItemByUnit)item)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getAmount() {
        if (this.items.size() < 2) {
            return new BigDecimal("0.00");
        }
        final BigDecimal totalAmount = this.items.stream().map(ItemByUnit::price).reduce(BigDecimal::add).get();
        return totalAmount.subtract(new BigDecimal(this.items.size() / 2)).setScale(2, RoundingMode.HALF_UP);
    }
}
