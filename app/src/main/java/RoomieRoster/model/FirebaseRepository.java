package RoomieRoster.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;


public class FirebaseRepository {

    private final DatabaseReference database;


    public FirebaseRepository(){
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void insertUser(User user){
        String key = user.uid;
        Map<String, Object> userValues = user.toMap();
        if(key != null){
            database.child("users").child(key).setValue(userValues);
        }

    }

    public void insertHouse(House house){
        String key = house.code;
        Map<String, Object> userValues = house.toMap();
        if(key != null){
            database.child("houses").child(key).setValue(userValues);
        }

    }

}
