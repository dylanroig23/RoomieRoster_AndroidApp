package RoomieRoster.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.ArrayList;
import java.util.List;

import RoomieRoster.UI.Activities.HomeActivity;
import RoomieRoster.UI.Activities.NewChoreActivity;
import RoomieRoster.UI.RecyclerViews.ChoreAdapter;
import RoomieRoster.UI.RecyclerViews.SingleChore;

public class ChoresFragment extends Fragment {
    private static final String TAG = "ChoresFragment";
    Button mHomeButton;
    Button mNewChoreButton;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");

        v = inflater.inflate(R.layout.fragment_chores, container, false);
        mHomeButton = v.findViewById(R.id.btn_choresHome);
        mNewChoreButton = v.findViewById(R.id.btn_choresNewChore);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);

        //replace with getting the chores list from the ViewModel
        List<SingleChore> chores = new ArrayList<>();
        chores.add(new SingleChore("Wash Dishes", "Dylan"));
        chores.add(new SingleChore("Take Out Trash", "Nick"));
        chores.add(new SingleChore("Clean Carpet", "Owen"));
        chores.add(new SingleChore("Get Mail", "Jacob"));
        chores.add(new SingleChore("Wash Dishes", "Dylan"));
        chores.add(new SingleChore("Take Out Trash", "Nick"));
        chores.add(new SingleChore("Clean Carpet", "Owen"));
        chores.add(new SingleChore("Get Mail", "Jacob"));
        chores.add(new SingleChore("Wash Dishes", "Dylan"));
        chores.add(new SingleChore("Take Out Trash", "Nick"));
        chores.add(new SingleChore("Clean Carpet", "Owen"));
        chores.add(new SingleChore("Get Mail", "Jacob"));

        // display the chores
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ChoreAdapter(getActivity().getApplicationContext(), chores));

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
}