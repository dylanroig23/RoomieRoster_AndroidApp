package RoomieRoster.example.roomieroster;


import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import RoomieRoster.model.User;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User("John Doe", "john@example.com", "1234567890", "uid123", "house123");
    }

    @Test
    public void testDefaultConstructor() {
        User defaultUser = new User();
        assertNotNull(defaultUser);
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("John Doe", user.name);
        assertEquals("john@example.com", user.email);
        assertEquals("1234567890", user.phone);
        assertEquals("uid123", user.uid);
        assertEquals("house123", user.house_id);
        assertEquals("", user.payment_acct);
        assertNotNull(user.preferences);
        assertNotNull(user.chores);
        assertNotNull(user.payments);
    }

    @Test
    public void testChangeHouse() {
        user.changeHouse("newHouse123");
        assertEquals("newHouse123", user.house_id);
    }

    @Test
    public void testToMap() {
        Map<String, Object> map = user.toMap();

        assertEquals(8, map.size());
        assertEquals("John Doe", map.get("name"));
        assertEquals("john@example.com", map.get("email"));
        assertEquals("1234567890", map.get("phone"));
        assertEquals("house123", map.get("house"));
        assertEquals("", map.get("payment_acct"));
        assertNotNull(map.get("preferences"));
        assertNotNull(map.get("chores"));
        assertNotNull(map.get("payments"));
    }
}
