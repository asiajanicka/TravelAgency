package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hotel {
    private String name;
    private int numberOfStars;
    private String address;
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(String name, int numberOfStars, String address) {
        if (name == null) {
            throw new IllegalArgumentException("Hotel name can't be null");
        }
        if (numberOfStars <= 0 || numberOfStars > 5) {
            throw new IllegalArgumentException("Number of stars must be between 1 and 5 inclusive");
        }
        if (address == null) {
            throw new IllegalArgumentException("Hotel address can't be null");
        }
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.address = address;
        this.rooms = new ArrayList<>();
    }

    public Hotel(String name, int numberOfStars, String address, List<Room> rooms) {
        this(name, numberOfStars, address);
        if (rooms == null) {
            throw new IllegalArgumentException("List of rooms can't be null");
        }
        if (rooms.size() > 0) {
            throw new IllegalArgumentException("List of rooms should have at least one room");
        }
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.address = address;
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room can't be null");
        }
        rooms.add(room);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Hotel name can't be null");
        }
        this.name = name;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(byte numberOfStars) {
        if (numberOfStars <= 0 || numberOfStars > 5) {
            throw new IllegalArgumentException("Number of stars must be between 1 and 5 inclusive");
        }
        this.numberOfStars = numberOfStars;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("Hotel address can't be null");
        }
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        if (rooms == null) {
            throw new IllegalArgumentException("List of rooms can't be null");
        }
        if (rooms.size() > 0) {
            throw new IllegalArgumentException("List of rooms should have at least one room");
        }
        this.rooms = rooms;
    }

    public String toString() {
        return String.format("Hotel: %s Number of stars: %d Address: %s", name, numberOfStars, address);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Hotel h = (Hotel) o;
        boolean nameEquals = this.name.equals(h.name);
        boolean numberOfStarsEquals = this.numberOfStars == h.numberOfStars;
        boolean addressEquals = this.address.equals(h.address);
        boolean roomsEquals = this.rooms.equals(h.rooms);
        return nameEquals && numberOfStarsEquals && addressEquals && roomsEquals;
    }

    public int hashCode() {
        return Objects.hash(name, numberOfStars, address, rooms);
    }
}
