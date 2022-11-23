package destination;

import enums.ActivityType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class AtHotelActivity extends Activity {
    private ActivityType type;
    private boolean isOwnEquipmentRequired;

    public AtHotelActivity() {
    }

    public AtHotelActivity(LocalDateTime date, String name, BigDecimal price, ActivityType type) {
        super(date, name, price);
        if (type == null) {
            throw new IllegalArgumentException("Activity type can't be null");
        }
        this.type = type;
        this.isOwnEquipmentRequired = false;
    }

    public AtHotelActivity(LocalDateTime date, String name, BigDecimal price, ActivityType type,
                           boolean isOwnEquipmentRequired) {
        this(date, name, price, type);
        this.isOwnEquipmentRequired = isOwnEquipmentRequired;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        if (type == null) {
            throw new IllegalArgumentException("Activity type can't be null");
        }
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

    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        AtHotelActivity a = (AtHotelActivity) o;
        boolean typeEquals = (this.type == a.type);
        boolean isOwnEquipmentRequiredEquals = (this.isOwnEquipmentRequired == a.isOwnEquipmentRequired);
        return typeEquals && isOwnEquipmentRequiredEquals;
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), type, isOwnEquipmentRequired);
    }
}
