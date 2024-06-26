package RoomieRoster.UI.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

import java.util.List;

import RoomieRoster.model.MapPoint;


public class MapPointAdapter extends RecyclerView.Adapter<MapPointViewHolder> {

    Context context;
    private final List<MapPoint> mapPoints;
    private final MapPointsViewInterface mMapPointsViewInterface;

    public MapPointAdapter(Context context, List<MapPoint> mapPoints, MapPointsViewInterface mapPointsViewInterface) {
        this.context = context;
        this.mapPoints = mapPoints;
        this.mMapPointsViewInterface = mapPointsViewInterface;
    }

    @NonNull
    @Override
    public MapPointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MapPointViewHolder(LayoutInflater.from(context).inflate(R.layout.map_roommate_view, parent, false), mMapPointsViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MapPointViewHolder holder, int position) {
        holder.mTextViewRoommateName.setText(mapPoints.get(position).getRoommateName());
    }

    @Override
    public int getItemCount() {
        return mapPoints.size();
    }


//    public MapPointAdapter(List<MapPoint> mapPoints, MapPointsViewInterface mapPointsViewInterface) {
//        this.mapPoints = mapPoints;
//        this.mMapPointsViewInterface = mapPointsViewInterface;
//    }
//
//    public class MapPointViewHolder extends RecyclerView.ViewHolder{
//        private TextView roommateNameText;
//
//        public MapPointViewHolder(final View view){
//            super(view);
//            roommateNameText = view.findViewById(R.id.roommateName);
//        }
//    }
//
//    @NonNull
//    @Override
//    public MapPointAdapter.MapPointViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.map_roommate_view, parent, false);
//        return new MapPointViewHolder(itemView);
//     }
//
//    @Override
//    public void onBindViewHolder(@NonNull MapPointAdapter.MapPointViewHolder holder, int position) {
//        String name = mapPoints.get(position).getRoommateName();
//        holder.roommateNameText.setText(name);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mapPoints.size();
//    }

}
