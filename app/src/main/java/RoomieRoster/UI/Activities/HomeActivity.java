package RoomieRoster.UI.Activities;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.RoomieRoster.R;

import RoomieRoster.UI.Fragments.HomeFragment;
import RoomieRoster.UI.Fragments.HouseOptionFragment;

/*
    THIS CLASS TO BE UPDATED
    Just acting as a place to route to after login
*/

public class HomeActivity extends AppCompatActivity {

    final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "HomeActivity: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "HomeActivity: Posting Fragment");
            fragment = new HomeFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "HomeActivity: onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "HomeActivity: onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "HomeActivity: onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "HomeActivity: onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "HomeActivity: onDestroy() called");
    }
}
