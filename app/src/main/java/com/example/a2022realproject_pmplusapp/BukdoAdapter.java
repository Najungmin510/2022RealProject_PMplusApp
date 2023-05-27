package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BukdoAdapter extends RecyclerView.Adapter<BukdoAdapter.ViewHolder> {

    ArrayList<bukdo_item> items = new ArrayList<bukdo_item>();

    public void setItems(ArrayList<bukdo_item> items){this.items = items;}

    public void clearItems(){ items.clear(); }
    public void addItem(bukdo_item sitem){ items.add(sitem); }

    @NonNull
    @Override
    public BukdoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_mini_lay, parent, false);
        return new BukdoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BukdoAdapter.ViewHolder holder, int position) {
        bukdo_item item =items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView degree;
        TextView weather;
        TextView rainper;
        TextView rainshap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            degree = itemView.findViewById(R.id.text_miniw_degree);
            weather = itemView.findViewById(R.id.text_miniw_weather);
            rainper = itemView.findViewById(R.id.text_miniw_rainper);
            rainshap = itemView.findViewById(R.id.text_miniw_rainshap);
        }

        public void setItem(bukdo_item sitem){
            degree.setText(sitem.buk_ta);
            weather.setText(sitem.buk_wf);
            rainper.setText(sitem.buk_rnSt);
            rainper.setText(sitem.buk_rnYn);
        }
    }
}
