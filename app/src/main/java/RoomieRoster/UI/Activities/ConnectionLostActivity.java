package RoomieRoster.UI.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.RoomieRoster.R;

import RoomieRoster.UI.Fragments.ConnectionLostFragment;
import RoomieRoster.UI.Fragments.HouseOptionFragment;


public class ConnectionLostActivity extends AppCompatActivity {
    final String TAG = "ConnectionLostActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "ConnectionLostActivity: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "ConnectionLostActivity: Posting Fragment");
            fragment = new ConnectionLostFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "ConnectionLostActivity: onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "ConnectionLostActivity: onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "ConnectionLostActivity: onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "ConnectionLostActivity: onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "ConnectionLostActivity: onDestroy() called");
    }
}

