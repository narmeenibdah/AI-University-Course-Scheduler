package application;

public class Room {

    private String roomId;
    private int capacity;
    private String type;

    public Room(String roomId, int capacity, String type) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.type = type;
    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return roomId + " - Capacity: " + capacity + " - " + type;
    }
}