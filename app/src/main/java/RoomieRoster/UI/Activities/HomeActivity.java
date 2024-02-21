package RoomieRoster.UI.Activities;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import com.RoomieRoster.R;

/*
    THIS CLASS TO BE UPDATED
    Just acting as a place to route to after login
*/

public class HomeActivity extends AppCompatActivity {

    final String TAG = "HomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "HomeActivity: onCreate()");
    }
}
