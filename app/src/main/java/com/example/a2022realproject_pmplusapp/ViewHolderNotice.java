package com.example.a2022realproject_pmplusapp;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHolderNotice extends RecyclerView. ViewHolder{

    TextView notice_adapter_title;
    TextView notice_adapter_writer;
    TextView notice_adapter_date;
    TextView item_notice_text1;
    ImageView notice_adapter_image;

    public ViewHolderNotice(View itemView){
        super(itemView);

        notice_adapter_title = itemView.findViewById(R.id.notice_adapter_title);
        notice_adapter_writer = itemView.findViewById(R.id.notice_adapter_writer);
        notice_adapter_date = itemView.findViewById(R.id.notice_adapter_date);
        notice_adapter_image = itemView.findViewById(R.id.notice_adapter_image);
    }


    public void onBind(NoticeData noticeData, int position, SparseBooleanArray selectedItems) {
        notice_adapter_title.setText(noticeData.getTitle());
        notice_adapter_writer.setText(noticeData.getWriter());
        notice_adapter_date.setText(noticeData.getDate());
        notice_adapter_image.setImageResource(noticeData.getImage());

        item_notice_text1.setText(noticeData.getTitle());
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
                // imageView의 높이 변경
                item_notice_text1.getLayoutParams().height = (int) animation.getAnimatedValue();
                item_notice_text1.requestLayout();
                // imageView가 실제로 사라지게하는 부분
                item_notice_text1.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
            }
        });
        // Animation start
        va.start();
    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }

}
