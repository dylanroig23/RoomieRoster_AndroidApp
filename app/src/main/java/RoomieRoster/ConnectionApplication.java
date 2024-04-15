package RoomieRoster;

import android.app.Application;
import android.util.Log;

import RoomieRoster.model.NetworkMonitorUtility;

public class ConnectionApplication extends Application {
    public static final String TAG = "ConnectionApplication";
    public NetworkMonitorUtility mNetworkMonitorUtility;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG, "onCreate() called");

        mNetworkMonitorUtility = new NetworkMonitorUtility(getApplicationContext());
        mNetworkMonitorUtility.checkNetwork();
        mNetworkMonitorUtility.registerNetworkCallbacks();
    }
}
