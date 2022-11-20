package hotel;

import java.util.List;
import java.util.Map;

public class Hotel {
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

    public String toString(){
        return String.format("HOTEL: %s Number of stars: %d Address: %s", name, numberOfStars, address);
    }
}
