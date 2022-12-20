package org.jjm.enums;

import java.math.BigDecimal;

public enum BoardType {
    BB("Bed&Breakfast", "100"),
    HB("Half Board", "125"),
    FB("Full Board", "250"),
    ALL_INCLUSIVE("All inclusive", "500");

    private final String displayName;
    private final BigDecimal price;

    BoardType(String displayName, String price) {
        this.displayName = displayName;
        this.price = new BigDecimal(price);
    }

    public String getDisplayName() {
        return displayName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Board type: %s", displayName);
    }
}
