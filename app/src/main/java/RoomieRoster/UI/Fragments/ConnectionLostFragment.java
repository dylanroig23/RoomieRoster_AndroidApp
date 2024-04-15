package RoomieRoster.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.RoomieRoster.R;

import RoomieRoster.UI.Activities.ConnectionLostActivity;
import RoomieRoster.UI.Activities.CreateHouseActivity;
import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.JoinHouseActivity;
import RoomieRoster.model.NetworkManager;

public class ConnectionLostFragment extends Fragment {

    private static final String TAG = "ConnectionLostFragment";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, "ConnectionLostFragment: onCreate()");
        NetworkManager.getInstance().getNetworkStatus().observe(this, activeNetworkObserver);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "ConnectionLostFragment: onCreateView()");

        v = inflater.inflate(R.layout.fragment_lost_connection, container, false);

        return v;
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "ConnectionLostFragment: onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "ConnectionLostFragment: onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "ConnectionLostFragment: onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "ConnectionLostFragment: onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "ConnectionLostFragment: onDestroy() called");
    }

    private final Observer<Boolean> activeNetworkObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean hasInternet) {
            if(hasInternet){
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);
                if(getActivity() != null) getActivity().finish();
            }
        }
    };

}




