package com.example.a2022realproject_pmplusapp;

import android.app.Activity;
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
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

   private List<Chatbot_DataItem> messageList;
   private Activity activity;

   public ChatAdapter(List<Chatbot_DataItem> messageList, Activity activity){
       this.messageList = messageList;
       this.activity = activity;
   }


    @NonNull
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(activity).inflate(R.layout.activity_main_chat_bot,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.MyViewHolder holder, int position) {
       String message = messageList.get(position).getMessage();
       boolean isReceived = messageList.get(position).getIsReceived();

       if(isReceived){
           holder.messageReceive.setVisibility(View.VISIBLE);
           holder.messageSend.setVisibility(View.GONE);
           holder.messageReceive.setText(message);
       }else{
           holder.messageSend.setVisibility(View.VISIBLE);
           holder.messageReceive.setVisibility(View.GONE);
           holder.messageSend.setText(message);
       }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

       TextView messageSend;
       TextView messageReceive;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            messageSend = itemView.findViewById(R.id.chat_user);
            messageReceive = itemView.findViewById(R.id.chat_bot);
        }
    }
}
