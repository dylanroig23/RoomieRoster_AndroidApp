package RoomieRoster.UI.Fragments;

import static androidx.core.content.ContextCompat.startForegroundService;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.RoomieRoster.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import RoomieRoster.UI.Activities.ChoresActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import RoomieRoster.UI.Activities.CreateHouseActivity;
import RoomieRoster.UI.Activities.JoinHouseActivity;
import RoomieRoster.UI.Activities.LoginActivity;
import RoomieRoster.UI.Activities.MapsActivity;
import RoomieRoster.model.LocationService;
import RoomieRoster.model.viewmodel.HouseViewModel;
import RoomieRoster.model.viewmodel.UserViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    Button mDeleteButton;
    private UserViewModel mUserViewModel;
    private HouseViewModel mHouseViewModel;
    private static final int FOREGROUND_LOCATION_PERMISSION_REQUEST_CODE = 1001;
    private static final int BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 1002;
    private ActivityResultLauncher<String> foregroundPermissionLauncher;
    private ActivityResultLauncher<String> backgroundPermissionLauncher;
    private boolean isForegroundServiceRunning = false;

    Button mChoresButton;
    Button mMapButton;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();
        mHouseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HouseViewModel.class);
        mUserViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        mUserViewModel.setCurrentUser();
        Log.d(TAG, "HomeFragment: onCreate()");
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        // Initialize ActivityResultLauncher for foreground location permission
        foregroundPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        // Foreground permission is granted, request background permission
                        Log.d(TAG, "Foreground PERMISSIONS SUCCESS");
                        if (currentApiVersion >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                            requestBackgroundLocationPermission();
                        } else{
                            startLocationService();
                        }
                    } else {
                        // Foreground permission is denied
                        Log.d(TAG, "Foreground PERMISSIONS NOT SUCCESS");
                    }
                });

        // Initialize ActivityResultLauncher for background location permission
        backgroundPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted) {
                        // Background permission is granted
                        // Do your location-related task
                        if (!isForegroundServiceRunning) {
                            Log.d(TAG, "Background PERMISSIONS SUCCESS");
                            startLocationService();
                        }
                    } else {
                        // Background permission is denied
                        Log.d(TAG, "Background PERMISSIONS NOT SUCCESS");
                    }
                });
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "HomeFragment: onCreateView()");

        v = inflater.inflate(R.layout.fragment_home, container, false);

//        mDeleteButton = v.findViewById(R.id.btn_delete);
        mChoresButton = v.findViewById(R.id.btn_chores);
        mMapButton = v.findViewById(R.id.btn_map);

//        if (mDeleteButton != null) {
//            mDeleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d(TAG, "HomeFragment: Delete Account Button Pressed");
//
//                    // Delete current user account in authentication
//                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    user.delete();
//                    // Delete user data from real time database
//                    mUserViewModel.deleteAccount(mUserViewModel.getCurrentUser().getValue());
//
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
//                }
//            });
//        }
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request foreground location permission
            foregroundPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        } else {
            startLocationService();
            // Foreground location permission is already granted, request background permission
            //requestBackgroundLocationPermission();
        }

        if (mChoresButton != null) {
            mChoresButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "HomeFragment: Chores Button Pressed");

                    Intent intent = new Intent(getActivity(), ChoresActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        if (mMapButton != null) {
            mMapButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "HomeFragment: Chores Button Pressed");

                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        return v;
    }

    private void requestBackgroundLocationPermission() {
        // Check for background location permission
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Request background location permission
            backgroundPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        } else {
            // Background location permission is already granted
            // Do your location-related task
            if (!isForegroundServiceRunning) {
                startLocationService();
            }
        }
    }

    private void startLocationService(){
        Intent background_location = new Intent(getActivity(), LocationService.class);
        startForegroundService(requireActivity(), background_location);
        isForegroundServiceRunning = true;
    }

    private void stopForegroundService() {
        if (isForegroundServiceRunning) {
            Intent serviceIntent = new Intent(requireContext(),  LocationService.class);
            requireContext().stopService(serviceIntent);
            isForegroundServiceRunning = false;
        }
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




