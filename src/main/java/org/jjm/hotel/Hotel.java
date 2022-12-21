package org.jjm.hotel;

import org.jjm.exceptions.InvalidDataException;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;
import org.jjm.exceptions.PlacementAlreadyBooked;
import org.jjm.hotel.enums.RoomType;
import org.jjm.utils.Utils;

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
                .orElseThrow(() -> new NoPlacementException(String.format("There is no room with number %d in the " +
                        "hotel %s.", roomNumber, name)));
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
            throw new PlacementAlreadyBooked(String.format("Room %d is already booked. Sorry.", roomNumber));
        else return getRoom(roomNumber);
    }

    public List<Room> getAllAvailableRoomsWithLambda() {
        return Utils.findAllMatchingInList(rooms, p -> !p.isBooked());
    }

    public Room getFirstAvailableRoom() throws NoPlacementAvailableException {
        return rooms.stream()
                .filter(p -> !p.isBooked())
                .findFirst()
                .orElseThrow(() -> new NoPlacementAvailableException(String.format("There is no free room in the hotel " +
                        "%s. All rooms are booked.", name)));
    }

    public Room getRoomByType(RoomType roomType) throws NoPlacementAvailableException {
        return rooms.stream()
                .filter(p -> !p.isBooked() && p.getType().equals(roomType))
                .findFirst()
                .orElseThrow(() -> new NoPlacementAvailableException(String.format("There is no free room of type %s " +
                        "in the hotel %s. All rooms are booked.", roomType, name)));
    }

    public static Hotel getHotelFromFile(String hotelFile) throws InvalidDataException, IOException {
        List<String> lines = Utils.readDataFromFile(hotelFile);
        return parseHotelFromStrings(lines, hotelFile);
    }

    public static Hotel parseHotelFromStrings(List<String> hotelStrings, String hotelFile) throws InvalidDataException {
        if (hotelStrings.size() < 2 || hotelStrings.get(0).isBlank() || hotelStrings.get(1).isBlank()) {
            throw new InvalidDataException(String.format("Hotel can't be created due to wrong data format in file %s. " +
                    "File should have at last two lines: first with hotel description and second or more with rooms " +
                    "description", hotelFile));
        }
        try {
            Hotel hotel = new Hotel();
            String[] hotelInfo = hotelStrings.get(0).split(",");
            hotel.setName(hotelInfo[0].trim());
            hotel.setStarsRating(Integer.parseInt(hotelInfo[1].trim()));
            hotel.setAddress(hotelInfo[2].trim());
            ArrayList<Room> rooms = new ArrayList<>();
            for (int i = 1; i < hotelStrings.size(); i++) {
                rooms.add(Room.parseRoomFromString(hotelStrings.get(i), hotelFile));
            }
            hotel.setRooms(rooms);
            return hotel;
        } catch (RuntimeException e) {
            throw new InvalidDataException(String.format("Hotel can't be created due to wrong data format in file %s." +
                    "First line in file should contain hotel description", hotelFile), e);
        }
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
