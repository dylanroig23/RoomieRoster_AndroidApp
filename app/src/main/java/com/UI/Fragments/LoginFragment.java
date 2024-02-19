package com.UI.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;



public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private EditText mUsernameEditText;

    private EditText mPasswordEditText;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, "onCreate()");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "onCreateView()");
        Activity activity = requireActivity();

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

        v = inflater.inflate(R.layout.fragment_login, container, false);
        mUsernameEditText = v.findViewById(R.id.username_text);
        mPasswordEditText = v.findViewById(R.id.password_text);

        final Button loginButton = v.findViewById(R.id.login_button);
        if (loginButton != null) {
            //loginButton.setOnClickListener(this);
        }
        final Button cancelButton = v.findViewById(R.id.cancel_button);
        if (cancelButton != null) {
            //cancelButton.setOnClickListener();
        }

        final Button newUserButton = v.findViewById(R.id.new_user_button);
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

}




