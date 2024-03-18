package RoomieRoster.UI.RecyclerViews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

public class ChoreViewHolder extends RecyclerView.ViewHolder {

    TextView mTextViewChoreName;
    TextView mTextViewAssignedTo;
    public ChoreViewHolder(@NonNull View choreView) {
        super(choreView);
        mTextViewChoreName = choreView.findViewById(R.id.choreName);
        mTextViewAssignedTo = choreView.findViewById(R.id.assignment);
    }
}
