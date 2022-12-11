package org.jjm.transport;

import org.jjm.enums.CoachSeatType;

import java.math.BigDecimal;

public class CoachSeat extends Seat {
    private CoachSeatType type;

    public CoachSeat(CoachSeatType type) {
        this.type = type;
    }

    public CoachSeat(int number, BigDecimal price, CoachSeatType type) {
        super(number, price);
        this.type = type;
    }

    public CoachSeatType getType() {
        return type;
    }

    public void setType(CoachSeatType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s Type: %s", super.toString(), type);
    }
}
