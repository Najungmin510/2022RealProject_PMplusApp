package com.example.a2022realproject_pmplusapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Board> datas;

    public PostAdapter(List<Board> datas) { //어댑터 생성자
        this.datas = datas;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_board, parent, false));
        //게시판 레이아웃을 불러옴
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Board data = datas.get(position); //각 위치마다 아이템들을 넣어주는 코드
        holder.title.setText(data.getTitle()); //각 포지션들이 홀더임
        holder.contents.setText(data.getContent());
        holder.name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView contents;
        private TextView name;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title_text);
            contents = itemView.findViewById(R.id.item_content_text);
            name = itemView.findViewById(R.id.item_name_text);
        }
    }
}
