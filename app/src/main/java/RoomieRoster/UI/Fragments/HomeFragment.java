package RoomieRoster.UI.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.RoomieRoster.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import RoomieRoster.UI.Activities.CreateHouseActivity;
import RoomieRoster.UI.Activities.JoinHouseActivity;
import RoomieRoster.UI.Activities.LoginActivity;
import RoomieRoster.model.viewmodel.HouseViewModel;
import RoomieRoster.model.viewmodel.UserViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    Button mDeleteButton;
    private UserViewModel mUserViewModel;
    private HouseViewModel mHouseViewModel;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();
        mHouseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HouseViewModel.class);
        mUserViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        mUserViewModel.setCurrentUser();
        Log.d(TAG, "HomeFragment: onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "HomeFragment: onCreateView()");

        v = inflater.inflate(R.layout.fragment_home, container, false);

        mDeleteButton = v.findViewById(R.id.btn_delete);

        if (mDeleteButton != null) {
            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "HomeFragment: Join House Button Pressed");

                    // Delete current user account in authentication
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.delete();
                    // Delete user data from real time database
                    mUserViewModel.deleteAccount(mUserViewModel.getCurrentUser().getValue());

                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        return v;
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "HomeFragment: onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "HomeFragment: onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "HomeFragment: onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "HomeFragment: onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "HomeFragment: onDestroy() called");
    }

}




