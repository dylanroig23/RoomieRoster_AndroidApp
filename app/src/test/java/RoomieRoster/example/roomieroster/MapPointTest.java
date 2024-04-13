package RoomieRoster.example.roomieroster;


import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import RoomieRoster.model.MapPoint;

public class MapPointTest {

    private MapPoint mapPoint;

    @Before
    public void setUp() {
        mapPoint = new MapPoint("Living Room", 40.7128f, -74.0060f);
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Living Room", mapPoint.getRoommateName());
        assertEquals(40.7128f, mapPoint.getLat(), 0.001f);
        assertEquals(-74.0060f, mapPoint.getLong(), 0.001f);
    }

    @Test
    public void testToMap() {
        Map<String, Object> map = mapPoint.toMap();

        assertEquals(3, map.size());
        assertEquals("Living Room", map.get("name"));
        assertEquals(40.7128f, (float) map.get("latitude"), 0.001f);
        assertEquals(-74.0060f, (float) map.get("longitude"), 0.001f);
    }
}

