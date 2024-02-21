package RoomieRoster.UI.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.RoomieRoster.R;


public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private EditText mUsernameEditText;

    private EditText mPasswordEditText;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, "onCreate() called");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "onCreateView() called");
        Activity activity = requireActivity();

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

        v = inflater.inflate(R.layout.fragment_login, container, false);

        mUsernameEditText = v.findViewById(R.id.email);
        mPasswordEditText = v.findViewById(R.id.password);

        final Button loginButton = v.findViewById(R.id.btn_login);
        if (loginButton != null) {
            //loginButton.setOnClickListener(this);
        }

        final TextView newUserButton = v.findViewById(R.id.btn_create_account);
        if (newUserButton != null) {
            if (rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) {
                //newUserButton.setOnClickListener(this);
            } else {
                newUserButton.setVisibility(View.GONE);
                newUserButton.invalidate();
            }
        }
        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

}




