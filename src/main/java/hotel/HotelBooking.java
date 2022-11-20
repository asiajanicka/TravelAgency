package hotel;

import enums.BoardType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class HotelBooking {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    public boolean forAdult;
    private Room room;
    private Board board;

    public HotelBooking(){
    };

    public HotelBooking(LocalDate dateFrom, LocalDate dateTo, boolean forAdult, Room room, BoardType boardType){
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.forAdult = forAdult;
        this.room = room;
        this.board = new Board(boardType);
    }
    
    public int lengthOfStaying(){
        Period period = Period.between(dateFrom, dateTo);
        return period.getDays();
    }
    
    public BigDecimal calculatePrice(){
        BigDecimal totalPrice = (room.getPrice().add(board.getPrice())).multiply(new BigDecimal(lengthOfStaying()));
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

    public void setBoard(Board board) {
        this.board = board;
    }

    public String toString(){
        return String.format("Hotel: from %tB %te, %tY to %tB %te, %tY Is for adult? %b Board: %s Total price: %.2f",
                dateFrom, dateFrom, dateFrom,
                dateTo, dateTo, dateTo,
                isForAdult(),
                board.getType(),
                calculatePrice());
    }
}
