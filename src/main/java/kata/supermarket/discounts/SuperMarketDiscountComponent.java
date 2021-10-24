package kata.supermarket.discounts;

import kata.supermarket.DiscountComponent;
import kata.supermarket.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class SuperMarketDiscountComponent implements DiscountComponent {
    private final Map<Integer, Set<DiscountType>> discounts = new HashMap<>();

    @Override
    public BigDecimal calculateTotalDiscount(List<Item> items) {
        final List<Discount> discountList = new ArrayList<>();
        discounts.forEach((productId, discountTypes) -> {
            discountTypes.forEach(discountType -> {
                if (discountType == DiscountType.BUY_ONE_GET_ONE) {
                    discountList.add(new BuyOneGetOneFreeDiscount(productId, items));
                } else if (discountType == DiscountType.BUY_TWO_FOR_1_POUND) {
                    discountList.add(new BuyTwoItemsFor1Pound(productId, items));
                }
            });
        });
        return discountList
                .stream()
                .map(Discount::getAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .setScale(2, RoundingMode.HALF_UP);
    }
    @Override
    public void addDiscount(Integer productId, DiscountType discountType) {
        this.discounts.computeIfAbsent(productId, k -> new HashSet<>()).add(discountType);
    }
}
