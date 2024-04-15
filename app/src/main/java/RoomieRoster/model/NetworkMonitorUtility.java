package RoomieRoster.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.util.Log;
import RoomieRoster.model.NetworkManager;

import androidx.annotation.NonNull;

public class NetworkMonitorUtility extends ConnectivityManager.NetworkCallback {
    private NetworkRequest mNetworkReq;
    private ConnectivityManager mConnectivityManager;
    private NetworkManager mNetworkManager;

    final String TAG = "NetworkMonitorUtility";

    public NetworkMonitorUtility(Context context){
        mNetworkReq = new NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build();
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        mNetworkManager = mNetworkManager.getInstance();
    }

    @Override
    public void onAvailable(@NonNull Network network){
        super.onAvailable(network);
        mNetworkManager.setNetworkStatus(true);
    }

    @Override
    public void onLost(@NonNull Network network){
        super.onLost(network);
        Log.e(TAG, "onLost() called: Lost network connection");
        mNetworkManager.setNetworkStatus(false);
    }


    public void registerNetworkCallbacks(){
        mConnectivityManager.registerNetworkCallback(mNetworkReq, this);
    }

    public void checkNetwork(){
        try{
            NetworkInfo netInfo = mConnectivityManager.getActiveNetworkInfo();
            mNetworkManager.setNetworkStatus(netInfo != null && netInfo.isConnected());
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
