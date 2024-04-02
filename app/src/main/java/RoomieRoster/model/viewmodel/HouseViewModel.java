package RoomieRoster.model.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;

import RoomieRoster.model.FirebaseRepository;
import RoomieRoster.model.House;
import RoomieRoster.model.MapPoint;

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

    public LiveData<ArrayList<MapPoint>> getRoommateData(String houseId) {
        MutableLiveData<ArrayList<MapPoint>> roommateLiveData = new MutableLiveData<>();
        FirebaseRepository.OnRoommatesHouseCallback callbackStructure = new FirebaseRepository.OnRoommatesHouseCallback() {
            @Override
            public void OnRoommatesHouseRetrieved(ArrayList<MapPoint> roommateData) {
                roommateLiveData.setValue(roommateData);
                Log.d("HouseViewModel", ": OnRoommatesHouseRetrieved called");
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("HouseViewModel", ": " + errorMessage);
            }
        };

        db_FirebaseRepo.geHouseRoommates(houseId, callbackStructure);

        return roommateLiveData;
    }

    public void insert(House house) { db_FirebaseRepo.insertHouse(house); }

}
