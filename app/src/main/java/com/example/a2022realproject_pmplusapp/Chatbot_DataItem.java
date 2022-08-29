package com.example.a2022realproject_pmplusapp;

public class Chatbot_DataItem {

    private String content; //내용

    private String name; //챗봇의 이름 (사용자 이름 x)

    private int viewType;


    public Chatbot_DataItem(String content, String name, int viewType){
        this.content = content;
        this.name = name;
        this.viewType = viewType;
    }

    public String getContent(){
        return content;
    }
    public String getName(){
        return name;
    }
    public int getViewType(){
        return viewType;
    }


}
