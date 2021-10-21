package kata.supermarket.discounts;

import kata.supermarket.Item;
import kata.supermarket.ItemByUnit;
import kata.supermarket.ItemByWeight;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class BuyOneKiloHalfKiloFree implements Discount{
    private final List<ItemByWeight> items;

    public BuyOneKiloHalfKiloFree(List<Item> items, int productId){
        this.items = items
                .stream()
                .filter(item -> item.itemProductId() == productId)
                .filter(item -> item instanceof ItemByWeight)
                .map(item -> (ItemByWeight)item)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal getAmount() {
        if(this.items.isEmpty()) {
            return new BigDecimal("0.00");
        }
        int kilos = this.items.stream().map(item -> item.weightInKilos()).reduce(BigDecimal::add).get().intValue();
        return items.get(0).pricePerKilo().divide(new BigDecimal(2)).multiply(new BigDecimal(kilos)).setScale(2, RoundingMode.HALF_UP);
    }
}
