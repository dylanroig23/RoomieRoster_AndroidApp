package RoomieRoster.UI.Fragments;

import android.app.Activity;
import android.content.Intent;
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


public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    TextInputEditText mEditTextEmail;
    TextInputEditText mEditTextPassword;
    Button mContinueButton;
    TextView mCreateAccountText;

    private EditText mPasswordEditText;


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




