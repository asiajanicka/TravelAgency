package org.jjm.transport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.interfaces.IBook;

import java.math.BigDecimal;
import java.util.Objects;

public class Seat implements IBook {
    private int number;
    private BigDecimal price;
    private boolean isBooked;
    private static final Logger logger = LogManager.getLogger(Seat.class);

    public Seat() {
    }

    public Seat(int number, BigDecimal price) {
        this.number = number;
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

    @Override
    public boolean unbook() {
        if (isBooked) {
            isBooked = false;
            logger.debug(String.format("Seat %d has been unbooked successfully", number));
            return true;
        }
        logger.debug(String.format("Seat %d can't be unbooked as its status is already free", number));
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

    @Override
    public String toString() {
        return String.format("Seat: %d", number);
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
