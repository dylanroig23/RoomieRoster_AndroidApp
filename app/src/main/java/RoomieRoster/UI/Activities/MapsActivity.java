package RoomieRoster.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.RoomieRoster.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import RoomieRoster.UI.Fragments.LoginFragment;
import RoomieRoster.UI.Fragments.MapFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.RoomieRoster.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    final String TAG = "MapsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        Log.d(TAG, "MapsActivity: onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            Log.d(TAG, "MapsActivity: Posting Fragment");
            fragment = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }


}