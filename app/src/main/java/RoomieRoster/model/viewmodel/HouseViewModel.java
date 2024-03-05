package RoomieRoster.model.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

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

    public void insert(House house) { db_FirebaseRepo.insertHouse(house); }

}
