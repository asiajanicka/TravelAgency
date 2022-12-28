package org.jjm.bookings;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jjm.hotel.enums.BoardType;
import org.jjm.hotel.Hotel;
import org.jjm.hotel.Room;
import org.jjm.hotel.exceptions.InvalidTimePeriodException;
import org.jjm.hotel.exceptions.InvalidTimePeriodType;
import org.jjm.interfaces.ICost;
import org.jjm.utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class HotelBooking implements ICost {
    private static int counter = 0;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Hotel hotel;
    private Room room;
    private BoardType board;
    private boolean isForAdult;
    private static final Logger logger = LogManager.getLogger(HotelBooking.class);

    public HotelBooking() {
        ++counter;
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, Hotel hotel, boolean isForAdult, Room room,
                        BoardType boardType) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.hotel = hotel;
        this.room = room;
        this.board = boardType;
        this.isForAdult = isForAdult;
        ++counter;
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, Hotel hotel, Room room, BoardType boardType) {
        this(dateFrom, dateTo, hotel, false, room, boardType);
    }

    private int getLengthOfStaying() throws InvalidTimePeriodException {
        Period period = Period.between(dateFrom, dateTo);
        if (period.isNegative()) {
            logger.error(String.format("Hotel Booking - length of staying has negative value: from %s to %s,",
                    DateFormat.format(dateFrom), DateFormat.format(dateTo)));
            throw new InvalidTimePeriodException(dateFrom, dateTo, InvalidTimePeriodType.PERIOD_NEGATIVE);
        }
        logger.debug(String.format("Hotel Booking - calculated length of staying: %d", period.getDays()));
        return period.getDays();
    }

    @Override
    public final BigDecimal calculatePrice() {
        BigDecimal priceForHotel;
        try {
            BigDecimal totalPrice = (room.getPrice().add(board.getPrice())).multiply(new BigDecimal(getLengthOfStaying()));
            priceForHotel = isForAdult ? totalPrice : totalPrice.divide(new BigDecimal(2));
            logger.debug(String.format("Hotel booking - calculated price [%,.2f] as sum of room price [%,.2f] and board" +
                            "[%,.2f] multiplied by length of staying [if not for adult -> divided by 2]",
                    priceForHotel, room.getPrice(), board.getPrice()));
        } catch (InvalidTimePeriodException e) {
            priceForHotel = null;
            logger.error("Hotel Booking - total price set to null", e);
        }
        return priceForHotel;
    }

    public static int getCounter() {
        return counter;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BoardType getBoard() {
        return board;
    }

    public void setBoard(BoardType boardType) {
        this.board = boardType;
    }

    public boolean isForAdult() {
        return isForAdult;
    }

    public void setForAdult(boolean forAdult) {
        isForAdult = forAdult;
    }

    @Override
    public String toString() {
        return String.format("Hotel: %s from %s to %s Is for adult? %b %s Board: %s Total price: %.2f",
                hotel.getName(), DateFormat.format(dateFrom), DateFormat.format(dateTo), isForAdult, room,
                board.getDisplayName(), calculatePrice());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        HotelBooking h = (HotelBooking) o;
        boolean isForAdultEquals = this.isForAdult == h.isForAdult;
        boolean dateFromEquals = (this.dateFrom == null && h.dateFrom == null)
                || (this.dateFrom != null && this.dateFrom.equals(h.dateFrom));
        boolean dateToEquals = (this.dateTo == null && h.dateTo == null)
                || (this.dateTo != null && this.dateTo.equals(h.dateTo));
        boolean hotelEquals = (this.hotel == null && h.hotel == null)
                || (this.hotel != null && this.hotel.equals(h.hotel));
        boolean roomEquals = (this.room == null && h.room == null) ||
                (this.room != null && this.room.equals(h.room));
        boolean boardEquals = this.board == h.board;
        return isForAdultEquals && dateFromEquals && dateToEquals && hotelEquals && roomEquals && boardEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, room, hotel);
    }
}
