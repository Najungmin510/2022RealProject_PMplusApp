package com.example.a2022realproject_pmplusapp;

import java.util.Date;

//게시판 목록 보여주기 위한 코드
//작성자 : 나정민

public class messageboard_list_item {

    private String messageboard_username;
    private String messageboard_title;
    private Date messageboard_date;
    private String messageboard_content;

    public messageboard_list_item(String messageboard_username, String messageboard_title, Date messageboard_date, String messageboard_content) {
        this.messageboard_username = messageboard_username;
        this.messageboard_title = messageboard_title;
        this.messageboard_date = messageboard_date;
        this.messageboard_content = messageboard_content;
    } //생성자

    public String getMessageboard_username() {
        return messageboard_username;
    }

    public void setMessageboard_username(String messageboard_username) {
        this.messageboard_username = messageboard_username;
    }

    public String getMessageboard_title() {
        return messageboard_title;
    }

    public void setMessageboard_title(String messageboard_title) {
        this.messageboard_title = messageboard_title;
    }

    public Date getMessageboard_date() {
        return messageboard_date;
    }

    public void setMessageboard_date(Date messageboard_date) {
        this.messageboard_date = messageboard_date;
    }

    public String getMessageboard_content() {
        return messageboard_content;
    }

    public void setMessageboard_content(String messageboard_content) {
        this.messageboard_content = messageboard_content;
    }


}
