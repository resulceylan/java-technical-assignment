package kata.supermarket;

import kata.supermarket.discounts.DiscountType;

import java.math.BigDecimal;
import java.util.List;

public interface DiscountComponent {
    BigDecimal calculateTotalDiscount(List<Item> items);
    void addDiscount(Integer productId, DiscountType discountType);
}
