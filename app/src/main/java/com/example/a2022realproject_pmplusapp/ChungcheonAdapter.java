package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ChungcheonAdapter extends RecyclerView.Adapter<ChungcheonAdapter.ViewHolder> {

    ArrayList<chungcheon_item> items = new ArrayList<chungcheon_item>();

    public void setItems(ArrayList<chungcheon_item> items){this.items = items;}

    public void clearItems(){ items.clear(); }
    public void addItem(chungcheon_item sitem){ items.add(sitem); }

    @NonNull
    @Override
    public ChungcheonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_mini_lay, parent, false);
        return new ChungcheonAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChungcheonAdapter.ViewHolder holder, int position) {
        chungcheon_item item =items.get(position);
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

        public void setItem(chungcheon_item sitem){
            num.setText(sitem.ch_numEf);
            degree.setText(sitem.ch_ta);
            weather.setText(sitem.ch_wf);
            rainper.setText(sitem.ch_rnSt);
            rainshap.setText(sitem.ch_rnYn);
        }
    }
}
