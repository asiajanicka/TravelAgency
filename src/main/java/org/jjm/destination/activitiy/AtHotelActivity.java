package org.jjm.destination.activitiy;

import org.jjm.enums.ActivityType;

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
        this(date, name, price, type);
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

    @Override
    public String toString() {
        return String.format("%s Location: at the hotel Cost: %.2f", super.toString(), getPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        AtHotelActivity a = (AtHotelActivity) o;
        boolean typeEquals = this.type == a.type;
        boolean isOwnEquipmentRequiredEquals = this.isOwnEquipmentRequired == a.isOwnEquipmentRequired;
        return typeEquals && isOwnEquipmentRequiredEquals;
    }

    @Override
    public int hashCode() {
        int typeHash = 0;
        int isOwnEquipmentRequiredHash = isOwnEquipmentRequired ? 43 : 7;
        if (!(this.type == null)) {
            typeHash = type.hashCode();
        }
        return 13 * super.hashCode() + 3 * isOwnEquipmentRequiredHash + 19 * typeHash;
    }
}
