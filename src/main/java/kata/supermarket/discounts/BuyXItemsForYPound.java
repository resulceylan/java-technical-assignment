package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class BuyXItemsForYPound implements Discount{
    private final List<ItemByUnit> items;
    private final int x;
    private final BigDecimal y;
    public BuyXItemsForYPound(List<Item> items, int productId, int x, BigDecimal y) {
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
        if (this.items.size() < x) {
            return new BigDecimal("0.00");
        }
        int discounted = this.items.size() / x;
        BigDecimal totalAmount = this.items.get(0).price().multiply(new BigDecimal(x));
        BigDecimal discountAmount = totalAmount.subtract(new BigDecimal("1.00")).multiply(new BigDecimal(discounted));
        if (BigDecimal.ZERO.compareTo(discountAmount) > 0) {
            return new BigDecimal("0.00");
        }
        return discountAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
