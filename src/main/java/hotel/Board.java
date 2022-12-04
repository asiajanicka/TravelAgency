package hotel;

import enums.BoardType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public class Board {
    private static final String PRICE_FOR_BB = "100";
    private static final String PRICE_FOR_HB = "125";
    private static final String PRICE_FOR_FB = "250";
    private static final String PRICE_FOR_ALL_INCLUSIVE = "500";
    private BoardType type;
    private static final Logger logger = LogManager.getLogger(Board.class);

    public Board() {
    }

    public Board(BoardType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        if (type == null) {
            logger.debug("Board type set to null so price is 0");
            return BigDecimal.ZERO;
        }
        BigDecimal result;
        switch (type) {
            case BB: {
                result = new BigDecimal(PRICE_FOR_BB);
                break;
            }
            case HB: {
                result = new BigDecimal(PRICE_FOR_HB);
                break;
            }
            case FB: {
                result = new BigDecimal(PRICE_FOR_FB);
                break;
            }
            case ALL_INCLUSIVE: {
                result = new BigDecimal(PRICE_FOR_ALL_INCLUSIVE);
                break;
            }
            default: {
                result = BigDecimal.ZERO;
            }
        }
        logger.debug(String.format("Board type chosen: %s - price %,.2f", type, result));
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
