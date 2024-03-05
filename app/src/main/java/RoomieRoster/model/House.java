package RoomieRoster.model;

import java.util.HashMap;
import java.util.Map;

public class House {

    public String address;
    public String city;
    public String state;
    public String zipcode;
    public String code;
    public Map<String, Boolean> users = new HashMap<>();
    public Map<String, Boolean> events = new HashMap<>();
    public Map<String, Boolean> chores = new HashMap<>();
    public Map<String, Boolean> expenses = new HashMap<>();

    public House() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public House(String address, String city, String state, String zipcode, String code) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.code = code;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", this.address);
        result.put("city", this.city);
        result.put("state", this.state);
        result.put("zipcode", this.zipcode);
        //result.put("code", this.code);


        return result;
    }

    public void addUser(String user_id){
        users.put(user_id, true);
    }
}
