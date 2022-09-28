package com.example.a2022realproject_pmplusapp;

import android.annotation.SuppressLint;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//아코디언 형식, adpter에서 클릭 시 펼쳐지고 재클릭시 닫힘

public class RecyclerViewAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<NoticeData> listData = new ArrayList<>(); //어댑터에 들어갈 list(안전관련 제목, 타이틀 등이 들어감)

    private SparseBooleanArray selectedItems = new SparseBooleanArray();    // item의 클릭 상태를 저장할 array객체

    private int prePosition = -1; // 직전에 클릭됐던 Item의 position (클릭한 item은 -1로)


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // 뷰 홀더를 생성 후 이어주는 역할
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notice, parent, false);
        return new ViewHolderNotice(view); //item notice 객체를 리턴
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        ViewHolderNotice viewHolderNotice = (ViewHolderNotice)holder;
        viewHolderNotice.onBind(listData.get(position),position, selectedItems);
        viewHolderNotice.setOnViewHolderItemClickListener(new OnViewHolderItemClickListener() {
            @Override
            public void onViewHolderItemClick() {
                if (selectedItems.get(position)) {
                    // 펼쳐진 Item을 클릭 시 (공지사항 타이틀)
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 Item의 클릭상태를 지움
                    selectedItems.delete(prePosition);
                    // 클릭한 Item의 position을 저장
                    selectedItems.put(position, true);
                }
                // 해당 포지션의 변화를 알림
                if (prePosition != -1) notifyItemChanged(prePosition);
                notifyItemChanged(position);
                // 클릭된 position 저장
                prePosition = position;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size(); //데이터 즉, 리스트가 몇개 나와야 하는지 반환해주는 역할
    }

    void addItem(NoticeData data){ //외부에서 데이터를 추가시킬 수 있게 함함
       listData.add(data);
    }
}
