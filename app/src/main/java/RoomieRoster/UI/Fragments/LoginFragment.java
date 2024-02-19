package RoomieRoster.UI.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.RoomieRoster.R;
import com.google.android.material.textfield.TextInputEditText;

import RoomieRoster.UI.Activities.LoginActivity;


public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    TextInputEditText mEditTextEmail;
    TextInputEditText mEditTextPassword;
    Button mContinueButton;




    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, "onCreate()");
        Log.d(TAG, "WE IN THIS THING");

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "GOT HERERE LETS GOOO");
        View v;
        Log.d(TAG, "onCreateView()");
        Activity activity = requireActivity();

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

        v = inflater.inflate(R.layout.fragment_login, container, false);

        mEditTextEmail = v.findViewById(R.id.email);
        mEditTextPassword = v.findViewById(R.id.password);
        mContinueButton = v.findViewById(R.id.btn_login);

        if (mContinueButton != null) {
            mContinueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email;
                    String password;
                    email = String.valueOf(mEditTextEmail.getText());
                    password = String.valueOf(mEditTextPassword.getText());

                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(view.getContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(view.getContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // add Firebase login information here
                }
            });
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

}




