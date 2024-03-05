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

public class CreateHouseFragment extends Fragment {
    private static final String TAG = "CreateHouseFragment";
    TextInputEditText mEditTextHouseNumber;
    TextInputEditText mEditTextStreet;

    TextInputEditText mEditTextCity;

    TextInputEditText mEditTextState;

    TextInputEditText mEditTextZipCode;

    TextInputEditText mEditTextNickname;
    Button mCreateHouseButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.d(TAG, TAG + ": onCreate()");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");

        v = inflater.inflate(R.layout.fragment_new_house, container, false);
        mEditTextHouseNumber = v.findViewById(R.id.number);
        mEditTextStreet = v.findViewById(R.id.street);
        mEditTextCity = v.findViewById(R.id.city);
        mEditTextState = v.findViewById(R.id.state);
        mEditTextZipCode = v.findViewById(R.id.zipcode);
        mEditTextNickname = v.findViewById(R.id.nickname);
        mCreateHouseButton = v.findViewById(R.id.btn_createHouse);

        if (mCreateHouseButton != null) {
            mCreateHouseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Create House Button Clicked");
                    String houseNumber;
                    String streetName;
                    String cityName;
                    String stateName;
                    String zipcodeNumber;
                    String nickname;

                    houseNumber = String.valueOf(mEditTextHouseNumber.getText());
                    streetName = String.valueOf(mEditTextStreet.getText());
                    cityName = String.valueOf(mEditTextCity.getText());
                    stateName = String.valueOf(mEditTextState.getText());
                    zipcodeNumber = String.valueOf(mEditTextZipCode.getText());
                    nickname = String.valueOf(mEditTextNickname.getText());

                    if (TextUtils.isEmpty(houseNumber)) {
                        Log.d(TAG, TAG + ": No House Number Provided");
                        Toast.makeText(view.getContext(), "House Number Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(streetName)) {
                        Log.d(TAG, TAG + ": No Street Name provided");
                        Toast.makeText(view.getContext(), "Street Name Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(cityName)) {
                        Log.d(TAG, TAG + ": No City Name provided");
                        Toast.makeText(view.getContext(), "City Name Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(stateName)) {
                        Log.d(TAG, TAG + ": No State Name provided");
                        Toast.makeText(view.getContext(), "State Name Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(zipcodeNumber)) {
                        Log.d(TAG, TAG + ": No ZipCode provided");
                        Toast.makeText(view.getContext(), "ZipCode Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(nickname)) {
                        Log.d(TAG, TAG + ": No Nickname provided");
                        Toast.makeText(view.getContext(), "Nickname Required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    //add logic for creating a new house here



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
