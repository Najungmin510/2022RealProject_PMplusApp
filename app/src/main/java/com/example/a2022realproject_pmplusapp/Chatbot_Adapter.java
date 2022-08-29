package com.example.a2022realproject_pmplusapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Chatbot_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Chatbot_DataItem> ChatdataList = null; //리스트 초기화

    public Chatbot_Adapter(ArrayList<Chatbot_DataItem> datalist){
        ChatdataList = datalist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view;
       Context context = parent.getContext();
       LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

       if(viewType == Chatbot_Content_Code.ViewType.CENTER_Content){
           view = inflater.inflate(R.layout.chatbot_date_center_itemlist,parent,false);
           return new CenterViewHolder(view);
       } //만약
        //여기다가 날짜 표시하는 소스코드도 작성하면 될듯
        else if (viewType == Chatbot_Content_Code.ViewType.LEFT_Content){ //챗봇
            view = inflater.inflate(R.layout.chatbot_room_bot_left_itemlist,parent,false);
            return new LeftViewHolder(view);
       }
        else{//사용자
            view = inflater.inflate(R.layout.chatbot_room_user_right_itemlist, parent, false);
            return new RightViewHolder(view);
       }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewholder, int position) {

        if(viewholder instanceof CenterViewHolder){
            ((CenterViewHolder)viewholder).chatday.setText(ChatdataList.get(position).getContent());
        } else if (viewholder instanceof LeftViewHolder){
            //((LeftViewHolder)viewholder).chatbot_image.(ChatdataList.get(position).getContent());
            ((LeftViewHolder)viewholder).chatbot_name.setText(ChatdataList.get(position).getContent());

        } else{
           // ((RightViewHolder)viewholder).user_msg.setText(Chat)
        }
    }

    // 각 뷰 홀더에 데이터를 연결해주는 역할을 함

    @Override
    public int getItemCount() {
        return ChatdataList.size();
    }

    @Override
    public int getItemViewType(int position){
        return ChatdataList.get(position).getViewType();
    }

    public class CenterViewHolder extends RecyclerView.ViewHolder{
        TextView chatday;

        public CenterViewHolder(@NonNull View itemView){
            super(itemView);
            chatday =(TextView)itemView.findViewById(R.id.chatbot_today);
        }
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder{

        CircleImageView chatbot_image;
        TextView chatbot_name;
        ImageView chatbot_msg;

        public LeftViewHolder(@NonNull View itemView){
            super(itemView);

            chatbot_image = (CircleImageView)itemView.findViewById(R.id.chatbot_image);
            chatbot_name = (TextView)itemView.findViewById(R.id.chatbot_name);
            chatbot_msg = (ImageView)itemView.findViewById(R.id.chatbot_textmsg);
        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder{

        ImageView user_msg;

        public RightViewHolder(@NonNull View itemView){
            super(itemView);
            user_msg = (ImageView)itemView.findViewById(R.id.chatbot_user_textmsg);
        }
    }
}
