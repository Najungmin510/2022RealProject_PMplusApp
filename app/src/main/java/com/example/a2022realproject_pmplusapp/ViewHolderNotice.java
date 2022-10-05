package com.example.a2022realproject_pmplusapp;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
public class ViewHolderNotice extends RecyclerView. ViewHolder{

    TextView notice_adapter_title;
    TextView notice_adapter_writer;
    TextView notice_adapter_date;
    TextView notice_adapter_content;
    ImageView notice_adapter_image;


    LinearLayout linearlayout;

    OnViewHolderItemClickListener onViewHolderItemClickListener;

    public ViewHolderNotice(View itemView){
        super(itemView);

        notice_adapter_title = itemView.findViewById(R.id.notice_adapter_title);
        notice_adapter_writer = itemView.findViewById(R.id.notice_adapter_writer);
        notice_adapter_date = itemView.findViewById(R.id.notice_adapter_date);
        //notice_adapter_image = itemView.findViewById(R.id.notice_adapter_image);
        notice_adapter_content = itemView.findViewById(R.id.notice_adapter_content);
        linearlayout = itemView.findViewById(R.id.linearlayout_notice_g);

        linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewHolderItemClickListener.onViewHolderItemClick();
            }
        });

    }


    public void onBind(NoticeData noticeData, int position, SparseBooleanArray selectedItems) {
        notice_adapter_title.setText(noticeData.getTitle());
        notice_adapter_writer.setText(noticeData.getWriter());
        notice_adapter_date.setText(noticeData.getDate());
       // notice_adapter_image.setImageResource(noticeData.getImage());
        notice_adapter_content.setText(noticeData.getContent());


        changeVisibility(selectedItems.get(position));
    }

    private void changeVisibility(final boolean isExpanded) {
        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 600) : ValueAnimator.ofInt(600, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 텍스트(내용)의 높이를 변경해주고
                notice_adapter_content.getLayoutParams().height = (int) animation.getAnimatedValue();
                notice_adapter_content.requestLayout();
                // 내용이 사라지고 보여지게 하는 내용
                notice_adapter_content.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }

}
*/