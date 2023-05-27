package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GangwonAdapter extends RecyclerView.Adapter<GangwonAdapter.ViewHolder> {

    ArrayList<gangwon_item> items = new ArrayList<gangwon_item>();

    public void setItems(ArrayList<gangwon_item> items){this.items = items;}

    public void clearItems(){ items.clear(); }
    public void addItem(gangwon_item sitem){ items.add(sitem); }

    @NonNull
    @Override
    public GangwonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_mini_lay, parent, false);
        return new GangwonAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GangwonAdapter.ViewHolder holder, int position) {
        gangwon_item item =items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView num;
        TextView degree;
        TextView weather;
        TextView rainper;
        TextView rainshap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.text_miniw_num);
            degree = itemView.findViewById(R.id.text_miniw_degree);
            weather = itemView.findViewById(R.id.text_miniw_weather);
            rainper = itemView.findViewById(R.id.text_miniw_rainper);
            rainshap = itemView.findViewById(R.id.text_miniw_rainshap);
        }

        public void setItem(gangwon_item sitem){
            num.setText(sitem.gangwon_numEf);
            degree.setText(sitem.gangwon_ta);
            weather.setText(sitem.gangwon_wf);
            rainper.setText(sitem.gangwon_rnSt);
            rainper.setText(sitem.gangwon_rnYn);
        }
    }
}
