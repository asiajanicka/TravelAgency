package hotel;

import enums.BoardType;
import utils.DateFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class HotelBooking {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    public boolean forAdult;
    private Room room;
    private Board board;

    public HotelBooking() {
    }

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, boolean forAdult, Room room, BoardType boardType) {
        if (dateFrom == null) {
            throw new IllegalArgumentException("Date 'from' can't be null");
        }
        if (dateTo == null) {
            throw new IllegalArgumentException("Date 'to' can't be null");
        }
        if (dateTo.isBefore(dateFrom) || dateFrom.isEqual(dateTo)) {
            throw new IllegalArgumentException("Date 'to' can't be before date 'from'");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room can't be null");
        }
        if (boardType == null) {
            throw new IllegalArgumentException("Board type can't be null");
        }
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.forAdult = forAdult;
        this.room = room;
        this.board = new Board(boardType);
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
        if (dateFrom == null) {
            throw new IllegalArgumentException("Date 'from' can't be null");
        }
        if (dateTo.isBefore(dateFrom) || dateFrom.isEqual(dateTo)) {
            throw new IllegalArgumentException("Date 'from' can't be after date 'to'");
        }
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        if (dateTo == null) {
            throw new IllegalArgumentException("Date 'to' can't be null");
        }
        if (dateTo.isBefore(dateFrom) || dateFrom.isEqual(dateTo)) {
            throw new IllegalArgumentException("Date 'to' can't be before date 'from'");
        }
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
        if (room == null) {
            throw new IllegalArgumentException("Room can't be null");
        }
        this.room = room;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(BoardType boardType) {
        if (boardType == null) {
            throw new IllegalArgumentException("Board type can't be null");
        }
        this.board = new Board(boardType);
    }

    public String toString() {
        return String.format("Hotel: from %s to %s Is for adult? %b Board: %s Total price: %.2f",
                DateFormat.format(dateFrom),
                DateFormat.format(dateTo),
                isForAdult(),
                board.getType().getDisplayName(),
                calculatePrice());
    }
}
