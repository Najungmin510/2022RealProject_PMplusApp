package com.example.a2022realproject_pmplusapp;

public class Chatbot_DataItem {

    private String message; //내용
    private boolean isReceived;


    public Chatbot_DataItem(String message, boolean isReceived){
        this.message = message;
        this.isReceived = isReceived;

    }

 public String getMessage(){
        return message;
 }
 public void setMessage(String message){
        this.message = message;
 }

 public boolean getIsReceived(){
        return isReceived;
 }
 public void setReceived(boolean isReceived){
        this.isReceived = isReceived;
 }
}
