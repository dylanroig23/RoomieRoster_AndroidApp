package RoomieRoster.model.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

import RoomieRoster.model.Chore;
import RoomieRoster.model.FirebaseRepository;

public class ChoreViewModel extends AndroidViewModel {
    private final MutableLiveData<String> currentChores;
    private final FirebaseAuth auth_FB;

    private final FirebaseRepository db_FB;
    public ChoreViewModel(@NonNull Application application) {
        super(application);
        auth_FB = FirebaseAuth.getInstance();
        db_FB = new FirebaseRepository();
        currentChores = new MutableLiveData<>();
    }

//    public Chore getChore (String choreNumber) {
//
//    }

    public void insertNewChore(String choreName, String assignedTo, String userId) {
        db_FB.insertChore(choreName, assignedTo, userId);
    }
}
