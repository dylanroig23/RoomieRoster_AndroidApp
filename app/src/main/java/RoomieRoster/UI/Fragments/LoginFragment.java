package RoomieRoster.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.RoomieRoster.R;
import com.google.android.material.textfield.TextInputEditText;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.RegisterActivity;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    TextInputEditText mEditTextEmail;
    TextInputEditText mEditTextPassword;
    Button mContinueButton;
    TextView mCreateAccountText;




    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, "LoginFragment: onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "LoginFragment: onCreateView()");

        v = inflater.inflate(R.layout.fragment_login, container, false);

        mEditTextEmail = v.findViewById(R.id.email);
        mEditTextPassword = v.findViewById(R.id.password);
        mContinueButton = v.findViewById(R.id.btn_login);

        if (mContinueButton != null) {
            mContinueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "LoginFragment: Continue Button Clicked");

                    String email;
                    String password;
                    email = String.valueOf(mEditTextEmail.getText());
                    password = String.valueOf(mEditTextPassword.getText());

                    if (TextUtils.isEmpty(email)) {
                        Log.d(TAG, "LoginFragment: No Email Provided");
                        Toast.makeText(view.getContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        Log.d(TAG, "LoginFragment: No Password Provided");
                        Toast.makeText(view.getContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Intent intent = new Intent(getActivity(), HomeActivity.class);

                    // ADD FIREBASE LOGIN CHECK HERE

                    // Start the SecondActivity
                    startActivity(intent);

                    // Finish the LoginActivity
                    getActivity().finish();
                }
            });
        }

        mCreateAccountText = v.findViewById(R.id.text_create_account);
        if (mCreateAccountText != null) {
            mCreateAccountText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "LoginFragment: Create Account Button Clicked");
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);

                    //start the register activity
                    startActivity(intent);

                    //finish the login activity
                    getActivity().finish();
                }
            });
        }
        return v;
    }

}




