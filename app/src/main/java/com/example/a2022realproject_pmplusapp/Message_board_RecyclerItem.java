package com.example.a2022realproject_pmplusapp;

import android.graphics.drawable.Drawable;

//혜린님이 작성하신 소스코드임, 게시판 관련 소스코드

public class Message_board_RecyclerItem {

    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;

    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }

}
