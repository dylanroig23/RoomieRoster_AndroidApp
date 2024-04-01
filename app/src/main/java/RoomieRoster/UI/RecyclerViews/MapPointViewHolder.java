package RoomieRoster.UI.RecyclerViews;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.RoomieRoster.R;

public class MapPointViewHolder extends RecyclerView.ViewHolder {

    TextView mTextViewRoommateName;

    public MapPointViewHolder(@NonNull View mapPointView, MapPointsViewInterface mapPointsViewInterface) {
        super(mapPointView);
        mTextViewRoommateName = mapPointView.findViewById(R.id.roommateName);


        mTextViewRoommateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mapPointsViewInterface != null){
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mapPointsViewInterface.onCompleteMapClick(position);
                    }
                }
                Log.d("MapPointViewHolder", "MapPointViewHolder: Roommate Name Pressed");
            }
        });
    }
}
