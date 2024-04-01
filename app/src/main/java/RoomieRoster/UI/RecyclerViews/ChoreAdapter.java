package RoomieRoster.UI.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.List;

import RoomieRoster.model.Chore;

public class ChoreAdapter extends RecyclerView.Adapter<ChoreViewHolder> {
    private final ChoresViewInterface mChoresViewInterface;

    Context context;
    List<Chore> chores;

    public ChoreAdapter(Context context, List<Chore> chores, ChoresViewInterface choresViewInterface) {
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
