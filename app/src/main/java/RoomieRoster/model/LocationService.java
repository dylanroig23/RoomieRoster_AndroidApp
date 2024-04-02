package RoomieRoster.model;

import static android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION;
import static android.os.Build.ID;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.ServiceCompat;
import androidx.core.content.ContextCompat;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import RoomieRoster.model.FirebaseRepository;

import com.RoomieRoster.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import java.time.Duration;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.model.viewmodel.ChoreViewModel;
import RoomieRoster.model.viewmodel.HouseViewModel;

public class LocationService extends Service {

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private static final String TAG = "MyLocationService";
    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "LocationServiceChannel";

    private Handler handler;
    private Runnable runnable;

    HouseViewModel mHouseViewModel;
    private FirebaseRepository db_FB;
    private String currentHouse;


    @Override
    public void onCreate() {
        super.onCreate();
        db_FB = new FirebaseRepository();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //Context activityContext = getApplicationContext();
        //mHouseViewModel = new ViewModelProvider((ViewModelStoreOwner) activityContext).get(HouseViewModel.class);
        createLocationRequest();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location location = locationResult.getLastLocation();
                if (location != null) {
                    FirebaseRepository.OnUserHouseCallback callbackStructure = new FirebaseRepository.OnUserHouseCallback(){
                        @Override
                        public void onUserHouseRetrieved(String house) {
                            currentHouse = house;
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Log.e("UserViewModel", ": " + errorMessage);
                        }
                    };
                    db_FB.getUserHouse(db_FB.getFB_Auth_ID(), callbackStructure);
                    db_FB.insertLocation(currentHouse, location.getLatitude(), location.getLongitude());
                    Log.d(TAG, "Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
                }
            }
        };

        startLocationUpdates();

        // Create the NotificationChannel
        CharSequence name = getString(R.string.channel_name);
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel channel = new NotificationChannel("LocationServiceChannel", name, importance);
        channel.setDescription(description);

        // Register the channel with the system
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        // Create a notification for the foreground service
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Location Service")
                .setContentText("Location Running")
                .setSmallIcon(R.drawable.rr_icon)
                .setContentIntent(PendingIntent.getActivity(this, 0,
                        new Intent(this, HomeActivity.class), PendingIntent.FLAG_IMMUTABLE))
                .setChannelId(CHANNEL_ID)
                .build();

        startForeground(NOTIFICATION_ID, notification);

    }

    private void createLocationRequest() {
        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 30000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(30000)
                .setMaxUpdateDelayMillis(30000)
                .build();
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }



}
