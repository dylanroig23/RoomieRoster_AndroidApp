package RoomieRoster.model;

import java.util.HashMap;
import java.util.Map;

public class Model_Chore {
    public String house;
    public String name;
    public String description;
    public String due_date;
    public String status;
    public String assigned_to;
    public String frequency;
    public String type;
    public boolean completed;
    public String completedBy;

    public Model_Chore() {
        // Default constructor required for calls to DataSnapshot.getValue(Model_Chore.class)
    }

    // only using house, name, completed, and assigned_to right now as this is all that is used in the
    // chore tabs for the RecyclerView
    public Model_Chore(String house, String name, boolean completed, String assigned_to) {
        this.house = house;
        this.name = name;
        this.completed = completed;
        this.assigned_to = assigned_to;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("house", this.house);
        result.put("name", this.name);
        result.put("completed", this.completed);
        result.put("assigned_to", this.assigned_to);

        return result;
    }
}
