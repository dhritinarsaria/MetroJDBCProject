package metro.entity;

public class Station {
    private int stationId;
    private String stationName;

    // Constructor
    public Station(int stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    // Overloaded constructor (for inserting without id)
    public Station(String stationName) {
        this.stationName = stationName;
    }

    // Getters & Setters
    public int getStationId() { return stationId; }
    public void setStationId(int stationId) { this.stationId = stationId; }

    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", stationName='" + stationName + '\'' +
                '}';
    }
}
