package org.jjm.transport;

import org.jjm.interfaces.IBook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Objects;

public class Seat <T> implements IBook {
    private int number;
    private BigDecimal price;
    private boolean isBooked;
    private T seatType;
    private static final Logger logger = LogManager.getLogger(Seat.class);

    public Seat() {
    }

    public Seat(int number, T seatType, BigDecimal price) {
        this.number = number;
        this.seatType = seatType;
        this.price = price;
    }

    @Override
    public boolean book() {
        if (!isBooked) {
            isBooked = true;
            logger.debug(String.format("Seat %d has been booked successfully", number));
            return true;
        }
        logger.debug(String.format("Seat %d can't be booked as its status is already booked", number));
        return false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public T getSeatType() {
        return seatType;
    }

    public void setSeatType(T seatType) {
        this.seatType = seatType;
    }

    @Override
    public String toString() {
        return String.format("Seat: %d Type: %s", number, seatType.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Seat s = (Seat) o;
        return number == s.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
