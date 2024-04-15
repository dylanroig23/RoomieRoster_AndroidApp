package RoomieRoster.example.roomieroster;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import RoomieRoster.model.Chore;


public class ChoreTest {

    private Chore chore;

    @Before
    public void setUp() {
        chore = new Chore("HouseA", "Clean", false, "John", "chore123");
    }

    @Test
    public void testDefaultConstructor() {
        Chore defaultChore = new Chore();
        assertNotNull(defaultChore);
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("HouseA", chore.house);
        assertEquals("Clean", chore.name);
        assertFalse(chore.completed);
        assertEquals("John", chore.assigned_to);
        assertEquals("chore123", chore.choreID);
    }

    @Test
    public void testToMap() {
        Map<String, Object> map = chore.toMap();

        assertEquals(4, map.size());
        assertEquals("HouseA", map.get("house"));
        assertEquals("Clean", map.get("name"));
        assertEquals(false, map.get("completed"));
        assertEquals("John", map.get("assigned_to"));
    }

    @Test
    public void testGetChoreTitle() {
        assertEquals("Clean", chore.getChoreTitle());
    }

    @Test
    public void testGetAssignedTo() {
        assertEquals("John", chore.getAssignedTo());
    }

    @Test
    public void testGetChoreID() {
        assertEquals("chore123", chore.getChoreID());
    }

    @Test
    public void testGetChoreHouse() {
        assertEquals("HouseA", chore.getChoreHouse());
    }
}

