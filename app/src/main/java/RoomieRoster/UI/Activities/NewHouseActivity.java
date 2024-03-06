package RoomieRoster.UI.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.RoomieRoster.R;

import RoomieRoster.UI.Fragments.JoinHouseFragment;


public class NewHouseActivity extends AppCompatActivity {
    final String TAG = "JoinHouseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "JoinHouse: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "JoinHouse: Posting Fragment");
            fragment = new JoinHouseFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("JoinHouse", "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("JoinHouse", "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("JoinHouse", "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("JoinHouse", "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("JoinHouse", "onDestroy() called");
    }
}

