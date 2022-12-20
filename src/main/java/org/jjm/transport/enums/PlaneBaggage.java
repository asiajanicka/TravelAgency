package org.jjm.transport.enums;

import java.math.BigDecimal;

public enum PlaneBaggage {
    CHECKED("20.5"),
    HAND("10.5");

    private final BigDecimal price;

    PlaneBaggage(String price) {
        this.price = new BigDecimal(price);
    }

    public BigDecimal getPrice() {
        return price;
    }
}
