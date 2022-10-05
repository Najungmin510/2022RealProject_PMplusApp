package com.example.a2022realproject_pmplusapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//공지사항 리사이클러뷰 어댑터

public class RecyclerViewAdpter extends RecyclerView.Adapter<RecyclerViewAdpter.ViewHolder> {

    private ArrayList<NoticeData> mList; //어댑터에 들어갈 list(안전관련 제목, 타이틀 등이 들어감)


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView_date;
        TextView textView_content;
        TextView textView_writer ;
        TextView textView_title;


        public ViewHolder(View itemView) {
            super(itemView);

             textView_title = (TextView) itemView.findViewById(R.id.notice_adapter_title);
             textView_writer = (TextView) itemView.findViewById(R.id.notice_adapter_writer);
             textView_date = (TextView)itemView.findViewById(R.id.notice_adapter_date);
             textView_content = (TextView)itemView.findViewById(R.id.notice_adapter_content);

        }
    }

    public RecyclerViewAdpter(ArrayList<NoticeData> list) {
        this.mList = list;
    }


    @NonNull
    @Override
    public RecyclerViewAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdpter.ViewHolder holder, int position) {

        holder.textView_title.setText(String.valueOf(mList.get(position).getTitle()));
        holder.textView_writer .setText(String.valueOf(mList.get(position).getWriter()));
        holder.textView_date.setText(String.valueOf(mList.get(position).getDate()));
        holder.textView_content.setText(String.valueOf(mList.get(position).getContent()));
        //다 해줬는데도 GlideApp 에러가 나면 rebuild project를 해주자.
    }

    @Override
    public int getItemCount() {
        Log.d("size", String.valueOf(mList.size()));
        return mList.size();
    }
}
