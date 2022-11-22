package destination;

import enums.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AtHotelActivity extends Activity {
    private ActivityType type;
    private boolean isOwnEquipmentRequired;

    public AtHotelActivity() {
    }

    public AtHotelActivity(LocalDateTime date, String name, BigDecimal price, ActivityType type) {
        super(date, name, price);
        this.type = type;
        this.isOwnEquipmentRequired = false;
    }

    public AtHotelActivity(LocalDateTime date, String name, BigDecimal price, ActivityType type,
                           boolean isOwnEquipmentRequired) {
        super(date, name, price);
        this.type = type;
        this.isOwnEquipmentRequired = isOwnEquipmentRequired;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public boolean isOwnEquipmentRequired() {
        return isOwnEquipmentRequired;
    }

    public void setOwnEquipmentRequired(boolean ownEquipmentRequired) {
        isOwnEquipmentRequired = ownEquipmentRequired;
    }

    public String toString() {
        return String.format("%s Location: at the hotel", super.toString());
    }
}
