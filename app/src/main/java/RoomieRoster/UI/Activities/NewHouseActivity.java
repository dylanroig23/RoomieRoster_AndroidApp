package RoomieRoster.UI.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.RoomieRoster.R;

import RoomieRoster.UI.Fragments.JoinHouseFragment;


public class NewHouseActivity extends AppCompatActivity {
    final String TAG = "NewHouseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "NewHouseActivity: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "NewHouseActivity: Posting Fragment");
            fragment = new JoinHouseFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("NewHouseActivity", "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("NewHouseActivity", "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("NewHouseActivity", "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("NewHouseActivity", "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("NewHouseActivity", "onDestroy() called");
    }
}

