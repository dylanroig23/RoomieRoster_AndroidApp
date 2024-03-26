package RoomieRoster.UI.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.ArrayList;
import java.util.List;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.NewChoreActivity;
import RoomieRoster.UI.RecyclerViews.ChoreAdapter;
import RoomieRoster.UI.RecyclerViews.ChoresViewInterface;
import RoomieRoster.model.Chore;
import RoomieRoster.model.viewmodel.ChoreViewModel;
import RoomieRoster.model.viewmodel.UserViewModel;

public class ChoresFragment extends Fragment implements ChoresViewInterface {
    private static final String TAG = "ChoresFragment";
    Button mHomeButton;
    Button mNewChoreButton;

    UserViewModel mUserViewModel;
    ChoreViewModel mChoreViewModel;

    List<Chore> recyclerViewChores;
    ChoreAdapter mChoreAdapter;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Activity activity = requireActivity();
        mUserViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(UserViewModel.class);
        mChoreViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ChoreViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");
        v = inflater.inflate(R.layout.fragment_chores, container, false);
        mHomeButton = v.findViewById(R.id.btn_choresHome);
        mNewChoreButton = v.findViewById(R.id.btn_choresNewChore);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);


        recyclerViewChores = new ArrayList<>();
        String uid = mUserViewModel.getCurrentUser().getValue();
        mUserViewModel.getUserHouse(uid).observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String house) {
                //Log.i(TAG, TAG + ": house: " + house);
                mChoreViewModel.getChoresForHouse(house).observe(getViewLifecycleOwner(), new Observer<List<Chore>>() {
                    @Override
                    public void onChanged(List<Chore> chores) {
                        recyclerViewChores.clear();
                        for (Chore chore : chores) {
                            //Log.i(TAG, TAG + ": ChoreName: " + chore.name + " ChoreID: " + chore.choreID);
                            recyclerViewChores.add(chore);
                        }

                        mChoreAdapter = new ChoreAdapter(getActivity().getApplicationContext(), recyclerViewChores, ChoresFragment.this);
                        recyclerView.setAdapter(mChoreAdapter);
                    }
                });
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // home button controller
        if (mHomeButton != null) {
            mHomeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": Home Button Clicked");
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }

        // new chore button controller
        if (mNewChoreButton != null) {
            mNewChoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, TAG + ": New Chore Button Clicked");
                    Intent intent = new Intent(getActivity(), NewChoreActivity.class);
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

    @Override
    public void onCompleteClick(int position) {
        Chore choreToRemove = recyclerViewChores.get(position);
        Log.i(TAG, TAG + ": ChoreID: " + choreToRemove.getChoreID() + " ChoreHouse: " + choreToRemove.house);
        recyclerViewChores.remove(position);
        mChoreAdapter.notifyItemRemoved(position);
        mChoreViewModel.deleteChore(choreToRemove.getChoreHouse(), choreToRemove.getChoreID());
    }
}