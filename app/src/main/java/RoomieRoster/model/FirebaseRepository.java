package RoomieRoster.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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

    /*
        getUserHouse() gets the houseId of a particular user using a callback
    */
    public interface OnUserHouseCallback {
        void onUserHouseRetrieved(String house);
        void onError(String errorMessage);
    }

    public interface OnRoommatesHouseCallback {
        void OnRoommatesHouseRetrieved(ArrayList<MapPoint> roommateData);
        void onError(String errorMessage);
    }

    public void getUserHouse(String userId, OnUserHouseCallback callback) {
        DatabaseReference userRef = database.child("users").child(userId);
        Log.i("FirebaseRepository", "FirebaseRespository: " + userRef.toString());
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String house = snapshot.child("house").getValue(String.class);
                    Log.i("FirebaseRepository", "FirebaseRespository: " + house);
                    callback.onUserHouseRetrieved(house);
                } else {
                    callback.onError("User not found");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
            }
        });
    }

    public void geHouseRoommates(String houseId, OnRoommatesHouseCallback callback) {
        DatabaseReference houseRef = database.child("houses").child(houseId).child("users");
        houseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MapPoint> userLocations = new ArrayList<>();
                if(snapshot.exists()){
                    for(DataSnapshot houseSnap : snapshot.getChildren()){
                        String name = houseSnap.child("name").getValue(String.class);
                        String latitudeS = houseSnap.child("latitude").getValue(String.class);
                        String longitudeS = houseSnap.child("longitude").getValue(String.class);
                        float latitudeF = Float.parseFloat(latitudeS);
                        float longitudeF = Float.parseFloat(longitudeS);
                        MapPoint point = new MapPoint(name, latitudeF, longitudeF);
                        userLocations.add(point);
                        Log.e("FirebaseRepository", ": MapPoint for " + name + "made");
                    }
                    callback.OnRoommatesHouseRetrieved(userLocations);
                }
                else{
                    callback.onError("Error with mapPoints");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /*
        insertChore() inserts the chore into the "chores" map, the "houses/chores" map, and
        to the "users/chores" map who created the chore
     */
    public void insertChore(String choreName, String assignedTo, String userId) {
        DatabaseReference userHouseRef = database.child("users/" + userId + "/house");
        userHouseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String houseCode = snapshot.getValue(String.class);

                    // add chore to the "chores" map
                    Chore newChore = new Chore(houseCode, choreName, false, assignedTo, UUID.randomUUID().toString());
                    String key = newChore.choreID;
                    Map<String, Object> choreValues = newChore.toMap();
                    if (key != null){
                        database.child("chores").child(key).setValue(choreValues);
                    }

                    // add chore to the "houses" map
                    Map<String, Object> choreUpdateHouses = new HashMap<>();
                    choreUpdateHouses.put("houses/" + houseCode + "/chores/" + key, true);
                    database.updateChildren(choreUpdateHouses);

                    // add chore to the "users" map (commented out for the time being, the reason
                    // being is that we want the chores assigned to the one who they are assigned to
                    // not the user who created them
//                    Map<String, Object> choreUpdateUsers = new HashMap<>();
//                    choreUpdateUsers.put("users/" + userId + "/chores/" + key, true);
//                    database.updateChildren(choreUpdateUsers);
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

     /*
        getChoresForHouse() gets all of the chores associated to a particular house and returns
        them to the caller through the use of a callback
     */
    public interface OnChoresDataCallback {
         void onDataLoaded(List<Chore> chores);

         void onError(String errorMessage);
    }

    public void getChoresForHouse(String houseId, OnChoresDataCallback callback) {
        DatabaseReference choresRef = database.child("chores");
        choresRef.orderByChild("house").equalTo(houseId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Chore> chores = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Chore addChore = snap.getValue(Chore.class);
                    addChore.choreID = snap.getKey();
                    chores.add(addChore);
                }
                callback.onDataLoaded(chores);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onError(error.getMessage());
            }
        });
    }

    public void deleteChore(String houseId, String choreId) {
        //remove from the "houses" map
        database.child("houses").child(houseId).child("chores").child(choreId).removeValue();

        //remove from the "chores" map
        database.child("chores").child(choreId).removeValue();
    }

}
