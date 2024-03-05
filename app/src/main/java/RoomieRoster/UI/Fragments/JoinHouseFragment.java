package RoomieRoster.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.RoomieRoster.R;
import com.google.android.material.textfield.TextInputEditText;

import RoomieRoster.UI.Activities.LoginActivity;

public class JoinHouseFragment extends Fragment {
    private static final String TAG = "JoinHouseFragment";
    TextInputEditText mEditTextHouseCode;
    Button mJoinButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, TAG + ": onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");

        v = inflater.inflate(R.layout.fragment_join_house, container, false);
        mEditTextHouseCode = v.findViewById(R.id.join_houseCode);
        mJoinButton = v.findViewById(R.id.btn_join);

        if (mJoinButton != null) {
            mJoinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Join House Button Clicked");
                    String houseCode;

                    houseCode = String.valueOf(mEditTextHouseCode.getText());
                    if (TextUtils.isEmpty(houseCode)) {
                        Log.d(TAG, TAG + ": No House Code provided");
                        Toast.makeText(view.getContext(), "House Code Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // add logic for adding house code to the current user
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
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
