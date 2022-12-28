package org.jjm.hotel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.hotel.enums.RoomType;
import org.jjm.exceptions.InvalidDataException;
import org.jjm.exceptions.InvalidIDataType;
import org.jjm.interfaces.IBook;

import java.math.BigDecimal;
import java.util.Objects;

public class Room implements IBook {
    private int number;
    private RoomType type;
    private BigDecimal price;
    private boolean isBooked;
    private static final Logger logger = LogManager.getLogger(Room.class);

    public Room() {
    }

    public Room(int number, RoomType type, BigDecimal price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    @Override
    public boolean book() {
        if (!isBooked) {
            isBooked = true;
            logger.debug(String.format("Room %d has been booked successfully", number));
            return true;
        } else {
            logger.debug(String.format("Room %d can't be booked as its status is already booked", number));
            return false;
        }
    }

    public static Room parseRoomFromString(String roomString, String hotelFile) throws InvalidDataException {
        try {
            String[] roomInfo = roomString.split(",");
            Room room = new Room();
            room.setNumber(Integer.parseInt(roomInfo[0].trim()));
            room.setType(RoomType.valueOf(roomInfo[1].trim().toUpperCase()));
            room.setPrice(new BigDecimal(roomInfo[2].trim()));
            room.setBooked(Boolean.valueOf(roomInfo[3].trim()));
            return room;
        } catch (Exception e) {
            throw new InvalidDataException(InvalidIDataType.INVALID_ROOM_DATA_IN_FILE, hotelFile, e);
        }
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Room number: %d Room type: %s", number, type);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Room r = (Room) o;
        boolean numberEquals = this.number == r.number;
        boolean typeEquals = this.type == r.type;
        boolean priceEquals = (this.price == null && r.price == null)
                || (this.price != null && this.price.equals(r.price));
        return numberEquals && typeEquals && priceEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, type, price);
    }
}
