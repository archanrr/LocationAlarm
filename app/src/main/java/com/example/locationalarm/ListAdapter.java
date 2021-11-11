package com.example.locationalarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.locationalarm.DatabaseOperations.AlarmDetails;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<AlarmDetails> alarmModels = new ArrayList<>();
    public ListAdapter(ArrayList<AlarmDetails> alarmModels){
        this.alarmModels = alarmModels;
    }
    @NonNull
    @NotNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(alarmModels.get(position));
        //holder.lat.setText((int)alarmModels.get(position).getLat());
        //holder.lon.setText((int )alarmModels.get(position).getLon());
        holder.reason.setText((String) alarmModels.get(position).getReason());
    }

    @Override
    public int getItemCount() {
        return alarmModels.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView lat;
        TextView lon;
        TextView reason;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            lat = (TextView) itemView.findViewById(R.id.ilat);
            lon = (TextView) itemView.findViewById(R.id.ilon);
            reason = (TextView) itemView.findViewById(R.id.ireason);
        }
    }
}
