package org.jjm.hotel;

import org.jjm.exceptions.*;
import org.jjm.hotel.enums.RoomType;
import org.jjm.utils.Utils;
import org.jjm.enums.PlacementType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private String name;
    private int starsRating;
    private String address;
    private List<Room> rooms;

    public Hotel() {
        rooms = new ArrayList<>();
    }

    public Hotel(String name, int starsRating, String address, List<Room> rooms) {
        this.name = name;
        this.starsRating = starsRating;
        this.address = address;
        this.rooms = rooms;
    }

    public Room getRoom(int roomNumber) throws NoPlacementException {
        return rooms.stream()
                .filter(p -> p.getNumber() == roomNumber)
                .findFirst()
                .orElseThrow(() -> new NoPlacementException(PlacementType.ROOM, roomNumber));
    }

    public Room getRoomWithLambda(int roomNumber) throws NoPlacementException {
        Room room = Utils.findFirstMatchingInList(rooms, p -> p.getNumber() == roomNumber);
        if (room == null) {
            throw new NoPlacementException(String.format("There is no room with number %d in the hotel %s.",
                    roomNumber, name));
        } else return room;
    }

    public Room bookRoom(int roomNumber) throws NoPlacementException, PlacementAlreadyBooked {
        if (getRoom(roomNumber).book())
            throw new PlacementAlreadyBooked(PlacementType.ROOM, roomNumber);
        else return getRoom(roomNumber);
    }

    public List<Room> getAllAvailableRoomsWithLambda() {
        return Utils.findAllMatchingInList(rooms, p -> !p.isBooked());
    }

    public Room getFirstAvailableRoom() throws NoPlacementAvailableException {
        return rooms.stream()
                .filter(p -> !p.isBooked())
                .findFirst()
                .orElseThrow(() -> new NoPlacementAvailableException(PlacementType.ROOM));
    }

    public Room getRoomByType(RoomType roomType) throws NoPlacementAvailableException {
        return rooms.stream()
                .filter(p -> !p.isBooked() && p.getType().equals(roomType))
                .findFirst()
                .orElseThrow(() -> new NoPlacementAvailableException(PlacementType.ROOM));
    }

    public static Hotel getHotelFromFile(String hotelFile) throws InvalidDataException, IOException {
        List<String> lines = Utils.readDataFromFile(hotelFile);
        return parseHotelFromStrings(lines, hotelFile);
    }

    public static Hotel parseHotelFromStrings(List<String> hotelStrings, String hotelFile) throws InvalidDataException {
        if (hotelStrings.size() < 2 || hotelStrings.get(0).isBlank() || hotelStrings.get(1).isBlank()) {
            throw new InvalidDataException(InvalidIDataType.INVALID_HOTEL_FILE_FORMAT, hotelFile);
        }
        Hotel hotel = new Hotel();
        try {
            String[] hotelInfo = hotelStrings.get(0).split(",");
            hotel.setName(hotelInfo[0].trim());
            hotel.setStarsRating(Integer.parseInt(hotelInfo[1].trim()));
            hotel.setAddress(hotelInfo[2].trim());
        } catch (Exception e) {
            throw new InvalidDataException(InvalidIDataType.INVALID_HOTEL_DATA_IN_FILE, hotelFile, e);
        }
        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 1; i < hotelStrings.size(); i++) {
            rooms.add(Room.parseRoomFromString(hotelStrings.get(i), hotelFile));
        }
        hotel.setRooms(rooms);
        return hotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStarsRating() {
        return starsRating;
    }

    public void setStarsRating(int starsRating) {
        this.starsRating = starsRating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return String.format("Hotel: %s Number of stars: %d Address: %s", name, starsRating, address);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Hotel h = (Hotel) o;
        boolean nameEquals = (this.name == null && h.name == null) ||
                (this.name != null && this.name.equals(h.name));
        boolean numberOfStarsEquals = this.starsRating == h.starsRating;
        boolean addressEquals = (this.address == null && h.address == null)
                || (this.address != null && this.address.equals(h.address));
        boolean roomsEquals = (this.rooms == null && h.rooms == null)
                || (this.rooms != null && this.rooms.equals(h.rooms));
        return nameEquals && numberOfStarsEquals && addressEquals && roomsEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, starsRating, address, rooms);
    }
}
