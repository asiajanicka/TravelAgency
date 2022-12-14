package org.jjm.hotel;

import org.jjm.enums.RoomType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;
import org.jjm.exceptions.PlacementAlreadyBooked;
import org.jjm.interfaces.IFindByType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hotel implements IFindByType<RoomType, Room> {
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

    public Room bookRoom(int roomNumber) throws NoPlacementException, PlacementAlreadyBooked {
        if (getRoom(roomNumber).book())
            throw new PlacementAlreadyBooked(String.format("Room %d is already booked. Sorry.", roomNumber));
        else return getRoom(roomNumber);
    }

    public List<Room> getAllAvailableRooms() {
        return rooms.stream().filter(p -> !p.isBooked()).collect(Collectors.toList());
    }

    public Room getFirstAvailableRoom() throws NoPlacementAvailableException {
        if (getAllAvailableRooms().size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free room in the hotel %s. All rooms are booked.", name));
        } else
            return getAllAvailableRooms().get(0);
    }

    @Override
    public Room findByType(RoomType roomType) throws NoPlacementAvailableException {
        List<Room> roomsOfGivenType = rooms.stream()
                .filter(p -> !p.isBooked() && p.getType().equals(roomType))
                .collect(Collectors.toList());
        if (roomsOfGivenType.size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free room of type %s in the hotel %s. All rooms are booked.",
                            roomType, name));
        } else
            return roomsOfGivenType.get(0);
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

    public void setStarsRating(byte starsRating) {
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
