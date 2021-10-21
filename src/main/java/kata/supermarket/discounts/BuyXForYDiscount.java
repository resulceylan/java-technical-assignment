package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class BuyXForYDiscount implements Discount{
    private final List<ItemByUnit> items;
    private final int x;
    private final int y;
    public BuyXForYDiscount(List<Item> items, int productId, int x, int y) {
        this.x = x;
        this.y = y;
        this.items = items
                .stream()
                .filter(item -> item.itemProductId() == productId)
                .filter(item -> item instanceof ItemByUnit)
                .map(item -> (ItemByUnit)item)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getAmount() {
        if (this.items.isEmpty()) {
            return new BigDecimal("0.00");
        }
        int freeCount = (this.items.size() / x) * (x - y);
        return this.items.get(0).price().multiply(new BigDecimal(freeCount)).setScale(2, RoundingMode.HALF_UP);
    }
}
