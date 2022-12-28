package org.jjm.exceptions;

public enum InvalidIDataType {
    INVALID_HOTEL_FILE_FORMAT("Hotel can't be created due to wrong data format in file. File should have at last" +
            " two lines: first with hotel description and second or more with rooms description"),
    INVALID_HOTEL_DATA_IN_FILE("Hotel can't be created due to wrong data format in file. First line in file " +
            "should contain hotel description"),
    INVALID_ROOM_DATA_IN_FILE("Room can't be created due to wrong data format in file. Starting from 2nd line " +
            "in hotel file, lines should contain room description");

    private String description;

    InvalidIDataType(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
