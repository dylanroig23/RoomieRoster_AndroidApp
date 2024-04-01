package RoomieRoster.UI.Fragments;

import android.app.Activity;
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
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewModelProvider;

import com.RoomieRoster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import RoomieRoster.UI.Activities.HouseOptionActivity;
import RoomieRoster.UI.Activities.LoginActivity;
import RoomieRoster.UI.Activities.RegisterActivity;
import RoomieRoster.model.User;
import RoomieRoster.model.viewmodel.UserViewModel;

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";

    TextInputEditText mEditTextName;
    TextInputEditText mEditTextEmail;
    TextInputEditText mEditTextPhone;
    TextInputEditText mEditTextPassword;
    TextInputLayout mTextInputLayoutName;
    TextInputLayout mTextInputLayoutEmail;
    TextInputLayout mTextInputLayoutPhone;
    TextInputLayout mTextInputLayoutPassword;
    Button mCreateAccountButton;
    private FirebaseAuth mAuth;

    private UserViewModel mUserViewModel;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        mAuth = FirebaseAuth.getInstance();
        Activity activity = requireActivity();
        mUserViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        Log.d(TAG, "RegisterFragment: onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, "RegisterFragment: onCreateView()");

        v = inflater.inflate(R.layout.fragment_register, container, false);

        mEditTextName = v.findViewById(R.id.register_name);
        mEditTextEmail = v.findViewById(R.id.register_email);
        mEditTextPhone = v.findViewById(R.id.register_phone);
        mEditTextPassword = v.findViewById(R.id.register_password);
        mCreateAccountButton = v.findViewById(R.id.btn_register);

        mTextInputLayoutName = v.findViewById(R.id.name_hint);
        mTextInputLayoutName.setHintEnabled(false);

        mTextInputLayoutEmail = v.findViewById(R.id.email_reg_hint);
        mTextInputLayoutEmail.setHintEnabled(false);

        mTextInputLayoutPhone = v.findViewById(R.id.phone_reg_hint);
        mTextInputLayoutPhone.setHintEnabled(false);

        mTextInputLayoutPassword = v.findViewById(R.id.password_reg_hint);
        mTextInputLayoutPassword.setHintEnabled(false);

        if (mCreateAccountButton != null) {
            mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "RegisterFragment: Create Account Button Clicked");

                    String name;
                    String email;
                    String phone;
                    String password;

                    name = String.valueOf(mEditTextName.getText());
                    email = String.valueOf(mEditTextEmail.getText());
                    phone = String.valueOf(mEditTextPhone.getText());
                    password = String.valueOf(mEditTextPassword.getText());

                    if (TextUtils.isEmpty(name)) {
                        Log.d(TAG, "RegisterFragment: No Name Provided");
                        Toast.makeText(view.getContext(),"Enter Name",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(email)) {
                        Log.d(TAG, "RegisterFragment: No Email Provided");
                        Toast.makeText(view.getContext(),"Enter Email",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(phone)) {
                        Log.d(TAG, "RegisterFragment: No Phone Provided");
                        Toast.makeText(view.getContext(),"Enter Phone",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(password)) {
                        Log.d(TAG, "RegisterFragment: No Password Provided");
                        Toast.makeText(view.getContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //Create a User within Firebase using FirebaseAuth, go to LoginActivity on Success
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user_FB = mAuth.getCurrentUser();

                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(name)
                                                .build();

                                        user_FB.updateProfile(profileUpdates)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d(TAG, "User profile updated.");
                                                        }
                                                    }
                                                });

                                        Log.i(TAG, "RegisterFragment: Create User Account Success");
                                        Toast.makeText(view.getContext(), "Create User Account Success.",
                                                Toast.LENGTH_SHORT).show();
                                        FirebaseUser curr = mAuth.getCurrentUser();
                                        String uid = curr.getUid();
                                        User user = new User(name, email, phone, uid, "1234");
                                        mUserViewModel.insert(user);
                                        Intent intent = new Intent(getActivity(), HouseOptionActivity.class);

                                        // Start the HouseOptions
                                        startActivity(intent);

                                        // Finish the RegisterActivity
                                        getActivity().finish();
                                    } else {
                                        Log.e(TAG, "RegisterFragment: Create User Account Failed: " + task.getException().getMessage());
                                        Toast.makeText(view.getContext(), "Create User Account Failed: " + task.getException().getMessage() + ". Please Try Again.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }

        return v;
    }
}
