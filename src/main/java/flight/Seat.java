package flight;

import enums.PlaneSeatType;

import java.math.BigDecimal;

public class Seat {
    private int number;
    private PlaneSeatType type;

    private BigDecimal price;

    public Seat() {
    }

    public Seat(int number, PlaneSeatType type, BigDecimal price) {
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PlaneSeatType getType() {
        return type;
    }

    public void setType(PlaneSeatType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString(){
        return String.format("Seat: %d %s", number, type);
    }
}
