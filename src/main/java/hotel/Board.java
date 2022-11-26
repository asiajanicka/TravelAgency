package hotel;

import enums.BoardType;

import java.math.BigDecimal;

public class Board {
    private BoardType type;

    public Board() {
    }

    public Board(BoardType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        BigDecimal result;
        switch (type) {
            case BB: {
                result = new BigDecimal(100);
                break;
            }
            case HB: {
                result = new BigDecimal(125);
                break;
            }
            case FB: {
                result = new BigDecimal(250);
                break;
            }
            case ALL_INCLUSIVE: {
                result = new BigDecimal(500);
                break;
            }
            default: {
                result = BigDecimal.ZERO;
            }
        }
        return result;
    }

    public BoardType getType() {
        return type;
    }

    public void setType(BoardType type) {
        this.type = type;
    }

    public String toString() {
        return "Board type: " + type;
    }
}
