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
import RoomieRoster.UI.Activities.JoinHouseActivity;
import RoomieRoster.model.NetworkManager;

public class HouseOptionFragment extends Fragment {

    private static final String TAG = "HouseOptionFragment";
    Button mJoinHouseButton;
    Button mNewHouseButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
//        NetworkManager.getInstance().getNetworkStatus().observe(this, activeNetworkObserver);
        Log.d(TAG, "HouseOptionFragment: onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "HouseOptionFragment: onCreateView()");

        v = inflater.inflate(R.layout.fragment_house_option, container, false);

        mJoinHouseButton = v.findViewById(R.id.btn_join_house);
        mNewHouseButton = v.findViewById(R.id.btn_new_house);

        if (mJoinHouseButton != null) {
            mJoinHouseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "HouseOptionFragment: Join House Button Pressed");

                    Intent intent = new Intent(getActivity(), JoinHouseActivity.class);
                    startActivity(intent);
                    if(getActivity() != null) getActivity().finish();
                }
            });
        }

        if (mNewHouseButton != null) {
            mNewHouseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "HouseOptionFragment: New House Button Pressed");

                    Intent intent = new Intent(getActivity(), CreateHouseActivity.class);
                    startActivity(intent);
                    if(getActivity() != null) getActivity().finish();
                }
            });
        }
        return v;
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "HouseOptionFragment: onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "HouseOptionFragment: onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "HouseOptionFragment: onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "HouseOptionFragment: onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "HouseOptionFragment: onDestroy() called");
    }
 //   private final Observer<Boolean> activeNetworkObserver = new Observer<Boolean>() {
//        @Override
//        public void onChanged(Boolean hasInternet) {
//            if(!hasInternet){
//                Intent intent = new Intent(getActivity(), ConnectionLostActivity.class);
//                startActivity(intent);
//                if(getActivity() != null) getActivity().finish();
//            }
//        }
//    };
}




