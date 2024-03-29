package RoomieRoster.UI.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.List;
import java.util.Map;

import RoomieRoster.model.Chore;
import RoomieRoster.model.MapPoint;


public class MapPointAdapter extends RecyclerView.Adapter<MapPointAdapter.MapPointViewHolder> {

    Context context;
    private List<MapPoint> mapPoints;
    private final MapPointsViewInterface mMapPointsViewInterface;

    public MapPointAdapter(List<MapPoint> mapPoints, MapPointsViewInterface mapPointsViewInterface) {
        this.mapPoints = mapPoints;
        this.mMapPointsViewInterface = mapPointsViewInterface;
    }

    public class MapPointViewHolder extends RecyclerView.ViewHolder{
        private TextView roommateNameText;

        public MapPointViewHolder(final View view){
            super(view);
            roommateNameText = view.findViewById(R.id.roommateName);
        }
    }

    @NonNull
    @Override
    public MapPointAdapter.MapPointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_roommate_view, parent, false);
        return new MapPointViewHolder(itemView);
     }

    @Override
    public void onBindViewHolder(@NonNull MapPointAdapter.MapPointViewHolder holder, int position) {
        String name = mapPoints.get(position).getRoommateName();
        holder.roommateNameText.setText(name);
    }

    @Override
    public int getItemCount() {
        return mapPoints.size();
    }

}
