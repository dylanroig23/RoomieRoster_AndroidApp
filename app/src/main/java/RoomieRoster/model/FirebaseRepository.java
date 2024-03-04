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
        //String key = database.child("users").push().getKey();
        String key = user.email;
        //Map<String, Object> userValues = user.toMap();
        if(key != null){
            database.child("users").child(key).setValue(user);
        }

    }


}
