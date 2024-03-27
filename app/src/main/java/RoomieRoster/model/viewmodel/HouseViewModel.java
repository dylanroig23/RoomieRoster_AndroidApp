package RoomieRoster.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import RoomieRoster.model.FirebaseRepository;
import RoomieRoster.model.House;

public class HouseViewModel extends AndroidViewModel {
    // I am not sure what mutable livedata we would need here?
    private final FirebaseAuth auth_FB;
    private final FirebaseRepository db_FirebaseRepo;

    public HouseViewModel(@NonNull Application application) {
        super(application);
        auth_FB = FirebaseAuth.getInstance();
        db_FirebaseRepo = new FirebaseRepository();
    }

    public void addUserToHouse(String user_id, String houseCode) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("users/" + user_id, true);
        db_FirebaseRepo.getHouse(houseCode).updateChildren(childUpdates);
    }

    public void insert(House house) { db_FirebaseRepo.insertHouse(house); }

}
