package RoomieRoster.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


@IgnoreExtraProperties
public class User {

    public String name;
    public String email;
    public String phone;
    public String house_id;
    public String payment_acct;
    public Map<String, String> preferences;
    public Map<String, Boolean> chores;
    public Map<String, Boolean> payments;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String email, String phone, String house_id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.house_id = house_id;
        this.payment_acct = "";
        this.preferences = new HashMap<>();
        this.preferences.put("location", "on");
        this.chores = new HashMap<>();
        this.payments = new HashMap<>();
    }

    public void changeHouse(String house_id){
        this.house_id = house_id;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", this.name);
        result.put("email", this.email);
        result.put("phone", this.phone);
        result.put("house", this.house_id);
        result.put("payment_acct", this.payment_acct);
        result.put("preferences", this.preferences);
        result.put("chores", this.chores);
        result.put("payments", this.payments);


        return result;
    }



}
