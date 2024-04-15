package RoomieRoster.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NetworkManager extends ConnectivityManager.NetworkCallback {
    private static NetworkManager INSTANCE;
    private static final MutableLiveData<Boolean> activeNetworkStatus = new MutableLiveData<>();
    private NetworkManager(){}
    static String TAG = "NetworkManager";

    public static synchronized NetworkManager getInstance(){
        if(INSTANCE == null){
            Log.d(TAG, "Creating new instance");
            INSTANCE = new NetworkManager();
        }
        return INSTANCE;
    }

    public void setNetworkStatus(boolean status){
        Log.d(TAG, "network status: " + status);
        if(Looper.myLooper() == Looper.getMainLooper()){
            activeNetworkStatus.setValue(status);
        }
        else{
            activeNetworkStatus.postValue(status);
        }
    }

    public LiveData<Boolean> getNetworkStatus(){
        Log.d(TAG, "getNetworkStatus() called");
        return activeNetworkStatus;
    }


}
