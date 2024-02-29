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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.RegisterActivity;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    TextInputEditText mEditTextEmail;
    TextInputEditText mEditTextPassword;
    TextInputLayout mTextInputLayoutEmail;
    TextInputLayout mTextInputLayoutPassword;
    Button mContinueButton;
    TextView mCreateAccountText;
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        mAuth = FirebaseAuth.getInstance();
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

        mTextInputLayoutEmail = v.findViewById(R.id.email_hint);
        mTextInputLayoutEmail.setHintEnabled(false);

        mTextInputLayoutPassword = v.findViewById(R.id.password_hint);
        mTextInputLayoutPassword.setHintEnabled(false);


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

                    // Firebase Sign-In, go to home screen on success
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.i(TAG, "LoginFragment: User Login Success");
                                        Intent intent = new Intent(getActivity(), HomeActivity.class);

                                        // start to the home activity
                                        startActivity(intent);

                                        // finish the login activity
                                        getActivity().finish();
                                    } else {
                                        Log.d(TAG, "LoginFragment: User Login Fail: " + task.getException().getMessage());
                                        Toast.makeText(view.getContext(), "Login Failed: " + task.getException().getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

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




