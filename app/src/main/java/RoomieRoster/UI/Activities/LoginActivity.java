package RoomieRoster.UI.Activities;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import RoomieRoster.UI.Fragments.LoginFragment;

import android.os.Bundle;
import android.util.Log;

import com.RoomieRoster.R;

public class LoginActivity extends AppCompatActivity {
    final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "LoginActivity: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "LoginActivity: Posting Fragment");
            fragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d("LoginActivity", "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d("LoginActivity", "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d("LoginActivity", "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d("LoginActivity", "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("LoginActivity", "onDestroy() called");
    }
}

