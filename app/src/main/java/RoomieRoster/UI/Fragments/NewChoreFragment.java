package RoomieRoster.UI.Fragments;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.RoomieRoster.R;
import com.google.android.material.textfield.TextInputEditText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import RoomieRoster.UI.Activities.ChoresActivity;


public class NewChoreFragment extends Fragment {
    private static final String TAG = "NewChoreFragment";
    TextInputEditText mEditTextChoreName;
    TextInputEditText mEditTextAssignment;

    Button mGoBackButton;
    Button mAddChoreButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();
        // put the chore view model here?
        Log.d(TAG, TAG + ": onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");
        v = inflater.inflate(R.layout.fragment_new_chore, container, false);
        mEditTextChoreName = v.findViewById(R.id.NewChore_newChore);
        mEditTextAssignment = v.findViewById(R.id.NewChore_assignment);
        mGoBackButton = v.findViewById(R.id.btn_backButton);
        mAddChoreButton = v.findViewById(R.id.btn_addChore);

        if (mGoBackButton != null) {
            mGoBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Go Back Button Pressed");
                    Intent intent = new Intent(getActivity(), ChoresActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        if (mAddChoreButton != null) {
            mAddChoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Add New Chore Button Clicked");
                    String choreName;
                    String assignedTo;

                    choreName = String.valueOf(mEditTextChoreName.getText());
                    assignedTo = String.valueOf(mEditTextAssignment.getText());

                    if (TextUtils.isEmpty(choreName)) {
                        Log.d(TAG, TAG + ": No Chore Name Provided");
                        Toast.makeText(view.getContext(), "Chore Name Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(assignedTo)) {
                        Log.d(TAG, TAG + ": No Assignment Provided");
                        Toast.makeText(view.getContext(), "Chore Assignment Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //Section to add the new chore to the database

                    Intent intent = new Intent(getActivity(), ChoresActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        return v;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, TAG + ": onStart() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, TAG + ": onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, TAG + ": onPause() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, TAG + ": onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, TAG + ": onDestroy() called");
    }


}
