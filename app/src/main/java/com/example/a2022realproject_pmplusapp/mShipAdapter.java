package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//선박 관제 현황 어댑터
//코드작성 : 나정민

public class mShipAdapter extends RecyclerView.Adapter <mShipAdapter.ViewHolder>{

    ArrayList<mshipdata_Item> items = new ArrayList<mshipdata_Item>();

    public void setItems(ArrayList<mshipdata_Item> items){
        this.items = items;
    }

    public void clearItems(){
        items.clear();
    }
    public void addItem(mshipdata_Item item) {
        items.add(item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.mshipdata_adapter, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mshipdata_Item item =items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //화면에 표시하기 위한 메소드 정의( setItem ), id 값들 전부 가져오기기

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView a_m_prtAgCd; //항구청코드
        TextView a_m_prtAgNm; //항만명
        TextView a_m_clsgn; //호출부호
        TextView a_m_vsslNm; //선박한글명
        TextView a_m_vsslNltyNm; //선박국적명
        TextView a_m_vsslKndNm; // 선박종류명
        TextView a_m_vsslGrtg; //선박총톤수
        TextView a_m_aprtfEtryptDt; //기항지입항일시

        TextView a_m_harborEntrpsNm; //항만업체명
        TextView a_m_cntrlNm; //관제구분명
        TextView a_m_cntrlOpertDt; //관제작업일시
        TextView a_m_fcltyCd; //시설코드
        TextView a_m_fcltySubCd; //시설서브코드
        TextView a_m_fcltyNm; // 시설한글명


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            a_m_prtAgCd = itemView.findViewById(R.id.mshipdata_adapter_m_prtAgCd);
            a_m_prtAgNm = itemView.findViewById(R.id.mshipdata_adapter_m_prtAgNm);
            a_m_clsgn = itemView.findViewById(R.id.mshipdata_adapter_m_clsgn);
            a_m_vsslNm = itemView.findViewById(R.id.mshipdata_adapter_m_vsslNm);
            a_m_vsslNltyNm = itemView.findViewById(R.id.mshipdata_adapter_m_vsslNltyNm);
            a_m_vsslKndNm = itemView.findViewById(R.id.mshipdata_adapter_m_vsslKndNm);
            a_m_vsslGrtg = itemView.findViewById(R.id.mshipdata_adapter_m_vsslGrtg);
            a_m_aprtfEtryptDt = itemView.findViewById(R.id.mshipdata_adapter_m_aprtfEtryptDt);

            a_m_harborEntrpsNm = itemView.findViewById(R.id.mshipdata_adapter_m_harborEntrpsNm);
            a_m_cntrlNm = itemView.findViewById(R.id.mshipdata_adapter_m_cntrlNm);
            a_m_cntrlOpertDt = itemView.findViewById(R.id.mshipdata_adapter_m_cntrlOpertDt);
            a_m_fcltyCd = itemView.findViewById(R.id.mshipdata_adapter_m_fcltyCd);
            a_m_fcltySubCd = itemView.findViewById(R.id.mshipdata_adapter_m_fcltySubCd);
            a_m_fcltyNm = itemView.findViewById(R.id.mshipdata_adapter_m_fcltyNm);

        }

        public void setItem(mshipdata_Item item){
            a_m_prtAgCd.setText(item.m_prtAgCd);
            a_m_prtAgNm.setText(item.m_prtAgNm);
            a_m_clsgn.setText(item.m_clsgn);
            a_m_vsslNm.setText(item.m_vsslNm);
            a_m_vsslNltyNm.setText(item.m_vsslNltyNm);
            a_m_vsslKndNm.setText(item.m_vsslKndNm);
            a_m_vsslGrtg.setText(item.m_vsslGrtg);
            a_m_aprtfEtryptDt.setText(item.m_aprtfEtryptDt);

            a_m_harborEntrpsNm.setText(item.m_harborEntrpsNm);
            a_m_cntrlNm.setText(item.m_cntrlNm);
            a_m_cntrlOpertDt.setText(item.m_cntrlOpertDt);
            a_m_fcltyCd.setText(item.m_fcltyCd);
            a_m_fcltySubCd.setText(item.m_fcltySubCd);
            a_m_fcltyNm.setText(item.m_fcltyNm);

        }
    }
}
