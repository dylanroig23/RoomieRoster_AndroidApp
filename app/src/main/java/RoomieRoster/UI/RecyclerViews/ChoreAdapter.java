package RoomieRoster.UI.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.List;

public class ChoreAdapter extends RecyclerView.Adapter<ChoreViewHolder> {
    private final ChoresViewInterface mChoresViewInterface;

    Context context;
    List<SingleChore> chores;

    public ChoreAdapter(Context context, List<SingleChore> chores, ChoresViewInterface choresViewInterface) {
        this.context = context;
        this.chores = chores;
        this.mChoresViewInterface = choresViewInterface;
    }


    @NonNull
    @Override
    public ChoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChoreViewHolder(LayoutInflater.from(context).inflate(R.layout.chore_view, parent, false), mChoresViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoreViewHolder holder, int position) {
        holder.mTextViewChoreName.setText(chores.get(position).getChoreTitle());
        holder.mTextViewAssignedTo.setText(chores.get(position).getAssignedTo());
        holder.mTextViewAssignment.setText("Assignment: ");
    }

    @Override
    public int getItemCount() {
        return chores.size();
    }
}
