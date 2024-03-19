package RoomieRoster.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public @NonNull DatabaseReference getUser (@NonNull String userId) {
        return database.child("users").child(userId);
    }

    // Delete a user from the "users" map and also the "houses/users" map
    public void deleteUser (@NonNull String userId) {
        DatabaseReference userHouseRef = database.child("users/" + userId + "/house");
        userHouseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String houseCode = snapshot.getValue(String.class);
                    database.child("users/" + userId).removeValue();
                    database.child("houses/" + houseCode + "/users/" + userId).removeValue();
                } else {
                    Log.e("FirebaseRepository: ", "House data does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseRepository", error.getMessage());
            }
        });

    }

    public void insertHouse(House house){
        String key = house.code;
        Map<String, Object> userValues = house.toMap();
        if(key != null){
            database.child("houses").child(key).setValue(userValues);
        }
    }

    public @NonNull DatabaseReference getHouse (@NonNull String houseCode) {
        return database.child("houses").child(houseCode);
    }

    public void insertChore(Chore chore) {
        String key = chore.choreID;
        Map<String, Object> choreValues = chore.toMap();
        if (key != null){
            database.child("chores").child(key).setValue(choreValues);
        }
    }

}
