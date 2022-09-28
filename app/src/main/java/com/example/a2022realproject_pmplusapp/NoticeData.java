package com.example.a2022realproject_pmplusapp;


//뷰에 보여주기 위해 필요한 데이터들을 하나의 클래스로 모아줌


public class NoticeData {
    private String title; //제목
    private String writer; //작성자
    private String date; //날짜
    private int image; //어떤 종류의 공지인지 알려주는 텍스트의 배경


    public NoticeData(String title, String writer, String date, int image) {
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.image = image;

    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getWriter(){
        return writer;
    }

    public void setWriter(){
        this.writer = writer;
    }

    public String getDate(){
        return date;
    }

    public void setDate(){
        this.date = date;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }



}
