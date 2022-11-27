package hotel;

import enums.BoardType;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class HotelBooking {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private boolean forAdult;
    private Room room;
    private Board board;

    public HotelBooking() {
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, Room room, BoardType boardType) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.forAdult = false;
        this.room = room;
        this.board = new Board(boardType);
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, boolean forAdult, Room room, BoardType boardType) {
        this(dateFrom, dateTo, room, boardType);
        this.forAdult = forAdult;
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

    public boolean isForAdult() {
        return forAdult;
    }

    public void setForAdult(boolean forAdult) {
        this.forAdult = forAdult;
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
        return String.format("Hotel: from %s to %s Is for adult? %b %s Board: %s Total price: %.2f",
                DateFormat.format(dateFrom),
                DateFormat.format(dateTo),
                isForAdult(),
                room,
                board.getType().getDisplayName(),
                calculatePrice());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        HotelBooking h = (HotelBooking) o;
        boolean dateFromEquals = (this.dateFrom == null && h.dateFrom == null)
                || (this.dateFrom != null && this.dateFrom.equals(h.dateFrom));
        boolean dateToEquals = (this.dateTo == null && h.dateTo == null)
                || (this.dateTo != null && this.dateTo.equals(h.dateTo));
        boolean forAdultEquals = this.forAdult == h.forAdult;
        boolean roomEquals = (this.room == null && h.room == null) ||
                (this.room != null && this.room.equals(h.room));
        boolean boardEquals = this.board == h.board;
        return dateFromEquals && dateToEquals && forAdultEquals && roomEquals && boardEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo, room);
    }
}
