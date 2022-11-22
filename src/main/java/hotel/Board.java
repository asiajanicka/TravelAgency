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
            case BB : {
                result = new BigDecimal(123);
                break;
            }
            case HB : {
                result = new BigDecimal(234);
                break;
            }
            case FB : {
                result = new BigDecimal(250);
                break;
            }
            case ALL_INCLUSIVE : {
                result = new BigDecimal(500);
                break;
            }
            default : {
                throw new IllegalArgumentException("Incorrect board type. Price can not be calculated");
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

    public String toString(){
        return "Board Type: " + type;
    }
}
