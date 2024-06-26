package RoomieRoster.UI.Fragments;

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
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import RoomieRoster.UI.Activities.ConnectionLostActivity;
import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.RecyclerViews.MapPointAdapter;
import RoomieRoster.UI.RecyclerViews.MapPointsViewInterface;
import RoomieRoster.model.MapPoint;
import RoomieRoster.model.NetworkManager;
import RoomieRoster.model.viewmodel.HouseViewModel;
import RoomieRoster.model.viewmodel.UserViewModel;

public class MapFragment extends Fragment implements MapPointsViewInterface {
    private static final String TAG = "MapFragment";
    private GoogleMap mMap;
    Button mHomeButton;
    RecyclerView recyclerView;

    List<MapPoint> recyclerViewRoommates;
    Map<MapPoint, LatLng> roommateLocations;

    HouseViewModel mHouseViewModel;
    UserViewModel mUserViewModel;
    MapPointAdapter mMapPointAdapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();
        mHouseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(HouseViewModel.class);
        mUserViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        NetworkManager.getInstance().getNetworkStatus().observe(this, activeNetworkObserver);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");
        v = inflater.inflate(R.layout.activity_maps, container, false);

        recyclerView = v.findViewById(R.id.maps_recyclerview);
        mHomeButton = v.findViewById(R.id.btn_mapHome);

        recyclerViewRoommates = new ArrayList<>();
        roommateLocations = new HashMap<>();
        String uid = mUserViewModel.getCurrentUser().getValue();
        mUserViewModel.getUserHouse(uid).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String house) {
                Log.e("MapFragment: onChanged(string house)", ": OnChanged called");
                mHouseViewModel.getRoommateData(house).observe(getViewLifecycleOwner(), new Observer<ArrayList<MapPoint>>() {
                    @Override
                    public void onChanged(ArrayList<MapPoint> mapPoints) {
                        Log.e("MapFragment: onChanged(mapPoints)", ": OnChanged called");
                        recyclerViewRoommates.clear();
                        for(MapPoint point : mapPoints){
                            recyclerViewRoommates.add(point);
                            Log.i("MapFragment", ": Pointed added to list");
                        }

                        for(MapPoint m : recyclerViewRoommates){
                            float latitude = m.getLat();
                            float longitude = m.getLong();
                            MarkerOptions nextMarker= new MarkerOptions().position(new LatLng(latitude, longitude));

                            mMap.addMarker(nextMarker);
                            roommateLocations.put(m, new LatLng(latitude, longitude));
                            Log.i("MapFragment", ": MapPoint Made");
                        }
                        if(getActivity() != null) {
                            mMapPointAdapter = new MapPointAdapter(getActivity().getApplicationContext(), recyclerViewRoommates, MapFragment.this);
                            recyclerView.setAdapter(mMapPointAdapter);
                        }
                    }
                });
            }
        });
        if(getActivity() != null) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        // Initialize map fragment
        SupportMapFragment supportMapFragment=(SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
    if(supportMapFragment != null){
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
         mMap = googleMap;

        }
    });}


        // home button controller
        if (mHomeButton != null) {
            mHomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Home Button Clicked");
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    if(getActivity() != null)getActivity().finish();
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


    @Override
    public void onCompleteMapClick(int position) {
        MapPoint currentPoint = recyclerViewRoommates.get(position);
        LatLng pos = roommateLocations.get(currentPoint);
        //mMap.moveCamera(CameraUpdateFactory.zoomIn(roommateLocations.get(m).getPosition()));
        if(pos != null){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 7), 1000, null);
        }
        Log.i(TAG, TAG + " clicked");

    }

    private final Observer<Boolean> activeNetworkObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean hasInternet) {
            if(!hasInternet){
                Intent intent = new Intent(getActivity(), ConnectionLostActivity.class);
                startActivity(intent);
                if(getActivity() != null) getActivity().finish();
            }
        }
    };
}