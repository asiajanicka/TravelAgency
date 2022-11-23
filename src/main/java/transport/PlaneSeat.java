package transport;

import enums.PlaneSeatType;

import java.math.BigDecimal;

public class PlaneSeat extends Seat {
    private PlaneSeatType type;

    public PlaneSeat() {
    }

    public PlaneSeat(int number, PlaneSeatType type, BigDecimal price) {
        super(number, price);
        if (type == null) {
            throw new IllegalArgumentException("Plane seat type can't be null");
        }
        this.type = type;
    }

    public PlaneSeatType getType() {
        return type;
    }

    public void setType(PlaneSeatType type) {
        if (type == null) {
            throw new IllegalArgumentException("Plane seat type can't be null");
        }
        this.type = type;
    }

    public String toString() {
        return String.format("%s Type: %s", super.toString(), type);
    }
}
