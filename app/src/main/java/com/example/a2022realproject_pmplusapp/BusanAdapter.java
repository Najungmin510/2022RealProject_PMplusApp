package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusanAdapter extends RecyclerView.Adapter<BusanAdapter.ViewHolder> {


    ArrayList<busan_item> items = new ArrayList<busan_item>();

    public void setItems(ArrayList<busan_item> items){this.items = items;}

    public void clearItems(){ items.clear(); }
    public void addItem(busan_item sitem){ items.add(sitem); }

    @NonNull
    @Override
    public BusanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_mini_lay_2, parent, false);
        return new BusanAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BusanAdapter.ViewHolder holder, int position) {
        busan_item item =items.get(position);
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
            num = itemView.findViewById(R.id.text_miniw_num2);
            degree = itemView.findViewById(R.id.text_miniw_degree2);
            weather = itemView.findViewById(R.id.text_miniw_weather2);
            rainper = itemView.findViewById(R.id.text_miniw_rainper2);
            rainshap = itemView.findViewById(R.id.text_miniw_rainshap2);
        }

        public void setItem(busan_item sitem){
            num.setText(sitem.busan_numEf);
            degree.setText(sitem.busan_ta);
            weather.setText(sitem.busan_wf);
            rainper.setText(sitem.busan_rnSt);
            rainshap.setText(sitem.busan_rnYn);
        }
    }
}
