package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShipAdapter extends RecyclerView.Adapter <ShipAdapter.ViewHolder>{

    ArrayList<shipdata_Item> items = new ArrayList<shipdata_Item>();

    public void setItems(ArrayList<shipdata_Item> items){
        this.items = items;
    }

    public void clearItems(){
        items.clear();
    }
    public void addItem(shipdata_Item item) {
        items.add(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.shipdata_adapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        shipdata_Item item =items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
            return items.size();
    }

    //화면에 표시하기 위한 메소드 정의( setItem ), id 값들 전부 가져오기기

   static class ViewHolder extends RecyclerView.ViewHolder {
       TextView a_prtAgCd; //항구청코드
       TextView a_prtAgNm; //항구청명
       TextView a_clsgn; //호출부호
       TextView a_vsslNm; //선박명
       TextView a_vsslNltyNm; //선박 국가명
       TextView a_vsslKndNm; // 선박종류명
       TextView a_etryptPurpsNm; //입항목적명
       TextView a_dstnPrtNm; //목적지항구명

       TextView a_etryptDt; //입항일시
       TextView a_tkoffDT; //관제작업일시
       TextView a_ibobprtNm; //시설코드
       TextView a_laidupFcltyNm;
       TextView a_ldadngFrghtClCd; //관제작업일시
       TextView a_grtg; //시설코드
       TextView a_satmntEntrpsNm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            a_prtAgCd = itemView.findViewById(R.id.shipdata_adapter_prtAgCd);
            a_prtAgNm = itemView.findViewById(R.id.shipdata_adapter_prtAgNm);
            a_clsgn = itemView.findViewById(R.id.shipdata_adapter_clsgn);
            a_vsslNm = itemView.findViewById(R.id.shipdata_adapter_vsslNm);
            a_vsslNltyNm = itemView.findViewById(R.id.shipdata_adapter_vsslNltyNm);
            a_vsslKndNm = itemView.findViewById(R.id.shipdata_adapter_vsslKndNm);
            a_etryptPurpsNm = itemView.findViewById(R.id.shipdata_adapter_etryptPurpsNm);
            a_dstnPrtNm = itemView.findViewById(R.id.shipdata_adapter_dstnPrtNm);

            a_etryptDt = itemView.findViewById(R.id.shipdata_adapter_etryptDt);
            a_tkoffDT = itemView.findViewById(R.id.shipdata_adapter_tkoffDt);
            a_ibobprtNm = itemView.findViewById(R.id.shipdata_adapter_ibobprtNm);
            a_laidupFcltyNm = itemView.findViewById(R.id.shipdata_adapter_laidupFcltyNm);
            a_ldadngFrghtClCd = itemView.findViewById(R.id.shipdata_adapter_ldadngFrghtClCd);
            a_grtg = itemView.findViewById(R.id.shipdata_adapter_grtg);
            a_satmntEntrpsNm = itemView.findViewById(R.id.shipdata_adapter_satmntEntrpsNm);
        }

        public void setItem(shipdata_Item item){
            a_prtAgCd.setText(item.prtAgCd);
            a_prtAgNm.setText(item.prtAgNm);
            a_clsgn.setText(item.clsgn);
            a_vsslNm.setText(item.vsslNm);
            a_vsslNltyNm.setText(item.vsslNltyNm);
            a_vsslKndNm.setText(item.vsslKndNm);
            a_etryptPurpsNm.setText(item.etryptPurpsNm);
            a_dstnPrtNm.setText(item.dstnPrtNm);

            a_etryptDt.setText(item.etryptDt);
            a_tkoffDT.setText(item.tkoffDT);
            a_ibobprtNm.setText(item.ibobprtNm);
            a_laidupFcltyNm.setText(item.laidupFcltyNm);
            a_ldadngFrghtClCd.setText(item.ldadngFrghtClCd);
            a_grtg.setText(item.grtg);
            a_satmntEntrpsNm.setText(item.satmntEntrpsNm);
        }
    }
}
