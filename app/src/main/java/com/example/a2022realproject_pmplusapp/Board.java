package com.example.a2022realproject_pmplusapp;

public class Board {

//게시판 모델

   // private String documentid; //게시글 id
    private String title; //제목
    private String contents; //내용
    private String name; //사용자이름

    public Board(){

    }

    public Board(String id, String title, String content, String name) {
       // this.id = id;
        this.title = title;
        this.contents = content;
        this.name = name;
    }

    //public String getId() {
        //return id;
    //}

    //public void setId(String id) {
       // this.id = id;
    //}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return contents;
    }

    public void setContent(String content) {
        this.contents = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Board{" +
                //"id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + contents + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
