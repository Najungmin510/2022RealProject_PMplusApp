package com.example.a2022realproject_pmplusapp;


//뷰에 보여주기 위해 필요한 데이터들을 하나의 클래스로 모아줌


public class NoticeData {
    private String title; //제목
    private String writer; //작성자
    private String date; //날짜
    private String content; // 내용 (클릭 시 내용을 보여줌)

    public NoticeData(String title, String writer, String date, String content) {
        this.title = title;
        this.writer = writer;
        this.date = date;
        this.content = content; //내용
    }

    public String getTitle(){
        return title;
    }

    public String getWriter(){
        return writer;
    }

    public String getDate(){
        return date;
    }

    public String getContent(){
        return content;
    }






}
