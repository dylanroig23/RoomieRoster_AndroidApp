package RoomieRoster.model;

import java.util.HashMap;
import java.util.Map;

public class MapPoint {

    private String name;
    private float latitude;
    private float longitude;


    public MapPoint(String name, float latitude, float longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", this.name);
        result.put("latitude", this.latitude);
        result.put("longitude", this.longitude);

        return result;
    }

    public String getRoommateName() { return name; }
    public float getLat() { return latitude; }
    public float getLong() { return longitude; }

}
