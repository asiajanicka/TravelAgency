package hotel;

import interfaces.IBook;
import interfaces.IFindPlacement;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hotel implements IFindPlacement {
    private String name;
    private int numberOfStars;
    private String address;
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(String name, int numberOfStars, String address, List<Room> rooms) {
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.address = address;
        this.rooms = rooms;
    }

    public IBook find(int num) {
        return rooms.stream().filter(p -> p.getNumber() == num).collect(Collectors.toList()).get(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(byte numberOfStars) {
        this.numberOfStars = numberOfStars;
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

    public String toString() {
        return String.format("Hotel: %s Number of stars: %d Address: %s", name, numberOfStars, address);
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Hotel h = (Hotel) o;
        boolean nameEquals = (this.name == null && h.name == null) ||
                (this.name != null && this.name.equals(h.name));
        boolean numberOfStarsEquals = this.numberOfStars == h.numberOfStars;
        boolean addressEquals = (this.address == null && h.address == null)
                || (this.address != null && this.address.equals(h.address));
        boolean roomsEquals = (this.rooms == null && h.rooms == null)
                || (this.rooms != null && this.rooms.equals(h.rooms));
        return nameEquals && numberOfStarsEquals && addressEquals && roomsEquals;
    }

    public int hashCode() {
        return Objects.hash(name, numberOfStars, address, rooms);
    }
}
