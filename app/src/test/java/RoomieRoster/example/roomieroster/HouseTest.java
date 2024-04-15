package RoomieRoster.example.roomieroster;


import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import RoomieRoster.model.House;

public class HouseTest {

    private House house;

    @Before
    public void setUp() {
        house = new House("123 Main St", "Anytown", "CA", "12345", "house123");
        house.addUser("user123");
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("123 Main St", house.address);
        assertEquals("Anytown", house.city);
        assertEquals("CA", house.state);
        assertEquals("12345", house.zipcode);
        assertEquals("house123", house.code);
        assertTrue(house.users.containsKey("user123"));
    }

    @Test
    public void testAddUser() {
        house.addUser("newUser123");
        assertTrue(house.users.containsKey("newUser123"));
    }

    @Test
    public void testToMap() {
        Map<String, Object> map = house.toMap();

        assertEquals(6, map.size());
        assertEquals("123 Main St", map.get("address"));
        assertEquals("Anytown", map.get("city"));
        assertEquals("CA", map.get("state"));
        assertEquals("12345", map.get("zipcode"));
        assertTrue(((Map<String, Boolean>) map.get("users")).containsKey("user123"));
        assertTrue(((Map<String, Boolean>) map.get("chores")).isEmpty());
    }
}

