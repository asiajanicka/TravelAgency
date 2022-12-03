package hotel;

import exceptions.NoPlacementAvailableException;
import interfaces.IBook;
import interfaces.IFindPlacement;
import exceptions.NoPlacementException;

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
        return rooms.stream().filter(p->p.isBooked()==true).collect(Collectors.toList());
    }

    @Override
    public IBook findFirstAvailable() throws NoPlacementAvailableException {
        if (findAllAvailable().size() == 0) {
            throw new NoPlacementAvailableException(
                    String.format("There is no free room in the hotel %s. All rooms are booked.", name));
        } else
            return findAllAvailable().get(0);
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
