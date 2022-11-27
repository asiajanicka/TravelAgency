package bookings;

import enums.BoardType;
import hotel.Board;
import hotel.Hotel;
import hotel.Room;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class HotelBooking extends Booking {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Hotel hotel;
    private Room room;
    private Board board;

    public HotelBooking() {
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, Hotel hotel, boolean isForAdult, Room room, BoardType boardType) {
        super(isForAdult);
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.hotel = hotel;
        this.room = room;
        this.board = new Board(boardType);
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, Hotel hotel, Room room, BoardType boardType) {
        this(dateFrom, dateTo, hotel, false, room, boardType);
    }

    private int getLengthOfStaying() {
        Period period = Period.between(dateFrom, dateTo);
        return period.getDays();
    }

    public BigDecimal calculatePrice() {
        BigDecimal totalPrice = (room.getPrice().add(board.getPrice())).multiply(new BigDecimal(getLengthOfStaying()));
        return isForAdult() ? totalPrice : totalPrice.divide(new BigDecimal(2));
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

    public Board getBoard() {
        return board;
    }

    public void setBoard(BoardType boardType) {
        this.board = new Board(boardType);
    }

    public String toString() {
        return String.format("Hotel: %s from %s to %s Is for adult? %b %s Board: %s Total price: %.2f",
                hotel.getName(),
                DateFormat.format(dateFrom),
                DateFormat.format(dateTo),
                isForAdult(),
                room,
                board.getType().getDisplayName(),
                calculatePrice());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        HotelBooking h = (HotelBooking) o;
        boolean dateFromEquals = (this.dateFrom == null && h.dateFrom == null)
                || (this.dateFrom != null && this.dateFrom.equals(h.dateFrom));
        boolean dateToEquals = (this.dateTo == null && h.dateTo == null)
                || (this.dateTo != null && this.dateTo.equals(h.dateTo));
        boolean hotelEquals = (this.hotel == null && h.hotel == null)
                || (this.hotel != null && this.hotel.equals(h.hotel));
        boolean roomEquals = (this.room == null && h.room == null) ||
                (this.room != null && this.room.equals(h.room));
        boolean boardEquals = this.board == h.board;
        return dateFromEquals && dateToEquals && hotelEquals && roomEquals && boardEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, room, hotel);
    }
}
