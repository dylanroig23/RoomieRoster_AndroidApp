package RoomieRoster.UI.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.ArrayList;
import java.util.List;

import RoomieRoster.UI.RecyclerViews.ChoreAdapter;
import RoomieRoster.UI.RecyclerViews.Chore;

public class ChoresFragment extends Fragment {
    private static final String TAG = "ChoresFragment";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        Log.d(TAG, TAG + ": onCreateView()");

        v = inflater.inflate(R.layout.fragment_chores, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);

        List<Chore> chores = new ArrayList<>();
        chores.add(new Chore("Wash Dishes", "Dylan"));
        chores.add(new Chore("Take Out Trash", "Nick"));
        chores.add(new Chore("Clean Carpet", "Owen"));
        chores.add(new Chore("Get Mail", "Jacob"));
        chores.add(new Chore("Wash Dishes", "Dylan"));
        chores.add(new Chore("Take Out Trash", "Nick"));
        chores.add(new Chore("Clean Carpet", "Owen"));
        chores.add(new Chore("Get Mail", "Jacob"));
        chores.add(new Chore("Wash Dishes", "Dylan"));
        chores.add(new Chore("Take Out Trash", "Nick"));
        chores.add(new Chore("Clean Carpet", "Owen"));
        chores.add(new Chore("Get Mail", "Jacob"));


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ChoreAdapter(getActivity().getApplicationContext(), chores));

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