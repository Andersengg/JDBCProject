package domain;

/**
 * Created by suman on 4/18/2017.
 */
public class Room {
    int roomNumber;
    String roomSize, description;
    double price;

    public Room(int roomNumber, String roomSize, String description, double price) {
        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        this.description = description;
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(String roomSize) {
        this.roomSize = roomSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
