package RoomieRoster.UI.RecyclerViews;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

public class ChoreViewHolder extends RecyclerView.ViewHolder {

    TextView mTextViewChoreName;
    TextView mTextViewAssignedTo;
    TextView mTextViewAssignment;
    Button mCompleteButton;
    public ChoreViewHolder(@NonNull View choreView) {
        super(choreView);
        mTextViewChoreName = choreView.findViewById(R.id.choreName);
        mTextViewAssignedTo = choreView.findViewById(R.id.assignment);
        mTextViewAssignment = choreView.findViewById(R.id.assign);
        mCompleteButton = choreView.findViewById(R.id.btn_choreComplete);

        mCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ChoreViewHolder", "ChoreViewHolder: Complete Chore Button Pressed");
            }
        });
    }
}
