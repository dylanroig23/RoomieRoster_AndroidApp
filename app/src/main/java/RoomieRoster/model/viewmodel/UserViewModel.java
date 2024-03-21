package RoomieRoster.model.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;


import RoomieRoster.model.FirebaseRepository;
import RoomieRoster.model.User;

public class UserViewModel extends AndroidViewModel{
    private final MutableLiveData<String> currentUser;
    private final FirebaseAuth auth_FB;

    private final FirebaseRepository db_FB;

    public UserViewModel(@NonNull Application application){
        super(application);
        auth_FB = FirebaseAuth.getInstance();
        db_FB = new FirebaseRepository();
        currentUser = new MutableLiveData<>();
    }

    public void setCurrentUser(){
        FirebaseUser user = auth_FB.getCurrentUser();
        if(user != null){
            currentUser.setValue(user.getUid());
        }
    }
    public LiveData<String> getCurrentUser(){
        String currentString = currentUser.getValue();
        if (currentString == null){
            FirebaseUser user = auth_FB.getCurrentUser();
            if (user != null){
                currentUser.setValue(user.getUid());
            }
        }
        return currentUser;
    }

    public void insert(User user){
        db_FB.insertUser(user);
    }

    public void updateHouse(String user_id, String houseCode){
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("house", houseCode);
        db_FB.getUser(user_id).updateChildren(childUpdates);
    }

    /*

        Returns the house associated with the userId
        *could return LiveData if we wanted it to*
     */
    public String getUserHouse(String userId) {
        MutableLiveData<String> houseLiveData = new MutableLiveData<>();
        FirebaseRepository.OnUserHouseCallback callbackStructure = new FirebaseRepository.OnUserHouseCallback() {
            @Override
            public void onUserHouseRetrieved(String house) {
                houseLiveData.setValue(house);
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("UserViewModel", ": " + errorMessage);
            }
        };

        db_FB.getUserHouse(userId, callbackStructure);

        return houseLiveData.getValue();
    }

    public void deleteAccount(String user_id){
        db_FB.deleteUser(user_id);
    }


}
