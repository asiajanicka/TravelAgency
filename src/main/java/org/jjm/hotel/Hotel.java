package org.jjm.hotel;

import org.jjm.enums.RoomType;
import org.jjm.exceptions.NoPlacementAvailableException;
import org.jjm.exceptions.NoPlacementException;
import org.jjm.interfaces.IBook;
import org.jjm.interfaces.IFindAvailablePlacement;
import org.jjm.interfaces.IFindPlacementByType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hotel implements IFindAvailablePlacement, IFindPlacementByType<RoomType> {
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

    @Override
    public IBook find(int num) throws NoPlacementException {
        return rooms.stream()
                .filter(p -> p.getNumber() == num)
                .findFirst()
                .orElseThrow(() -> new NoPlacementException(String.format("There is no room with number %d in the " +
                        "hotel %s.", num, name)));
    }

    @Override
    public List<IBook> findAllAvailable() {
        return rooms.stream().filter(p -> !p.isBooked()).collect(Collectors.toList());
    }

    @Override
    public IBook findFirstAvailable() throws NoPlacementAvailableException {
        if (findAllAvailable().size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free room in the hotel %s. All rooms are booked.", name));
        } else
            return findAllAvailable().get(0);
    }


    @Override
    public <RoomType> Room findByType(RoomType t) throws NoPlacementAvailableException {
        List<Room> roomsOfGivenType = rooms.stream()
                .filter(p -> !p.isBooked() && p.getType().equals(t))
                .collect(Collectors.toList());
        if (roomsOfGivenType.size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free room of type %s in the hotel %s. All rooms are booked.",
                            t, name));
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
