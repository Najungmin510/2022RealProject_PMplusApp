package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SeoulAdapter extends RecyclerView.Adapter<SeoulAdapter.ViewHolder>{

    ArrayList<seoul_item> items = new ArrayList<seoul_item>();

    public void setItems(ArrayList<seoul_item> items){
        this.items = items;
    }

    public void clearItems(){
        items.clear();
    }
    public void addItem(seoul_item sitem){
        items.add(sitem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_mini_lay, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        seoul_item item =items.get(position);
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

        public void setItem(seoul_item sitem){
            num.setText(sitem.s_numEf);
            degree.setText(sitem.s_ta);
            weather.setText(sitem.s_wf);
            rainper.setText(sitem.s_rnSt);
            rainshap.setText(sitem.s_rnYn);
        }
    }

}
