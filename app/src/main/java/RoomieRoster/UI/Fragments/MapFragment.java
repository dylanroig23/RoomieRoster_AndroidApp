package RoomieRoster.UI.Fragments;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLng;

import android.app.Activity;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.NewChoreActivity;
import RoomieRoster.UI.RecyclerViews.ChoreAdapter;
import RoomieRoster.UI.RecyclerViews.ChoresViewInterface;
import RoomieRoster.UI.RecyclerViews.MapPointAdapter;
import RoomieRoster.UI.RecyclerViews.MapPointsViewInterface;
import RoomieRoster.model.Chore;
import RoomieRoster.model.MapPoint;
import RoomieRoster.model.viewmodel.ChoreViewModel;
import RoomieRoster.model.viewmodel.UserViewModel;

public class MapFragment extends Fragment implements MapPointsViewInterface {
    private static final String TAG = "MapFragment";
    private GoogleMap mMap;
    Button mHomeButton;
    RecyclerView recyclerView;

    List<MapPoint> recyclerViewRoommates;
    Map<MapPoint, LatLng> roommateLocations;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");
        v = inflater.inflate(R.layout.activity_maps, container, false);

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

        recyclerView = v.findViewById(R.id.maps_recyclerview);
        mHomeButton = v.findViewById(R.id.btn_mapHome);

        recyclerViewRoommates = new ArrayList<>();
        roommateLocations = new HashMap<>();

        // PLACE HOLDER ROOMMATE DUMMY DATA
        recyclerViewRoommates.add(new MapPoint("Dwight", 40, -83));
        recyclerViewRoommates.add(new MapPoint("Jim", 50, -90));
        recyclerViewRoommates.add(new MapPoint("Pam", 42, -85));

        Log.d(TAG, TAG + ": make dummy data");

        MapPointAdapter mapPointAdapter = new MapPointAdapter(recyclerViewRoommates, MapFragment.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mapPointAdapter);

        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;

                // TODO: On click of a roommate in the recycler will zoom to their marker?

                // Create a marker for each roommate

                for(MapPoint m : recyclerViewRoommates){
                    float latitude = m.getLat();
                    float longitude = m.getLong();
                    MarkerOptions nextMarker= new MarkerOptions().position(new LatLng(latitude, longitude));

                    mMap.addMarker(nextMarker);
                    roommateLocations.put(m, new LatLng(latitude, longitude));
                }
            }
        });

        // home button controller
        if (mHomeButton != null) {
            mHomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Home Button Clicked");
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, TAG + ": onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, TAG + ": onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, TAG + ": onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, TAG + ": onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, TAG + ": onDestroy() called");
    }


    public void onCompleteMapClick(int position) {
        MapPoint currentPoint = recyclerViewRoommates.get(position);
        LatLng pos = roommateLocations.get(currentPoint);
        //mMap.moveCamera(CameraUpdateFactory.zoomIn(roommateLocations.get(m).getPosition()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 20), 200, null);
        Log.i(TAG, TAG + " clicked");

    }
}