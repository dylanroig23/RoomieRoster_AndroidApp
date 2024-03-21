package RoomieRoster.model.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import RoomieRoster.model.Chore;
import RoomieRoster.model.FirebaseRepository;

public class ChoreViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Chore>> currentChores;
    private final FirebaseAuth auth_FB;

    private final FirebaseRepository db_FB;
    public ChoreViewModel(@NonNull Application application) {
        super(application);
        auth_FB = FirebaseAuth.getInstance();
        db_FB = new FirebaseRepository();
        currentChores = new MutableLiveData<>();
    }

    public void insertNewChore(String choreName, String assignedTo, String userId) {
        db_FB.insertChore(choreName, assignedTo, userId);
    }

    public LiveData<List<Chore>> getChoresForHouse(String houseId) {
        FirebaseRepository.OnChoresDataCallback callbackHandle = new FirebaseRepository.OnChoresDataCallback() {
            @Override
            public void onDataLoaded(List<Chore> chores) {
                currentChores.setValue(chores);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("ChoreViewModel", ": " + errorMessage);
            }
        };


        db_FB.getChoresForHouse(houseId, callbackHandle);
        return currentChores;
    }
}
