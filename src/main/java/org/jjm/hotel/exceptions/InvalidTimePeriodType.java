package org.jjm.hotel.exceptions;

public enum InvalidTimePeriodType {
    PERIOD_NEGATIVE("End date [%s] is before start date [%s]");

    private final String description;

    InvalidTimePeriodType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
