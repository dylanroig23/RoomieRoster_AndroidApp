package RoomieRoster.UI.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.RoomieRoster.R;

import RoomieRoster.UI.Fragments.HouseOptionFragment;
import RoomieRoster.UI.Fragments.LoginFragment;


public class HouseOptionActivity extends AppCompatActivity {
    final String TAG = "HouseOptionActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "HouseOption: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "HouseOption: Posting Fragment");
            fragment = new HouseOptionFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("HouseOption", "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("HouseOption", "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("HouseOption", "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("HouseOption", "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("HouseOption", "onDestroy() called");
    }
}

