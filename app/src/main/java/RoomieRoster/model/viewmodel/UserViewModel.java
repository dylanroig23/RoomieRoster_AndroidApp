package RoomieRoster.model.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    public void updateName(){

    }

    public void updateEmail(){

    }

    public void deleteAccount(){

    }


}
