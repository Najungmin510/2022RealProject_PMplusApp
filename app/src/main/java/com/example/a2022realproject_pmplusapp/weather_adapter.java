package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

//날씨 어댑터
public class weather_adapter extends RecyclerView.Adapter<weather_adapter.ViewHolder> {

    ArrayList<weather_item> items = new ArrayList<weather_item>();

    public void setItems(ArrayList<weather_item> items){
        this.items = items;
    }

    public void clearItems(){
        items.clear();
    }

    public void addItem(weather_item weatherItem){
        items.add(weatherItem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.weather_adapter,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull weather_adapter.ViewHolder holder, int position) {
        weather_item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView detail;
        TextView sky;
        TextView wind;
        TextView wave;
        TextView rain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            detail = itemView.findViewById(R.id.detail_weather);
            sky = itemView.findViewById(R.id.sky_weather);
            wind = itemView.findViewById(R.id.wind_weather_detail);
            wave = itemView.findViewById(R.id.wave_height_detail);
            rain = itemView.findViewById(R.id.rain_weather_detail);

        }

        public void setItem(weather_item weatherItem){
            detail.setText(weatherItem.numEf);
            sky.setText(weatherItem.wf);
            wind.setText(weatherItem.ws1);
            wave.setText(weatherItem.wh1);
            rain.setText(weatherItem.rnYn);
        }
    }
}
