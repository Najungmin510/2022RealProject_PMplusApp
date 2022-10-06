package com.example.a2022realproject_pmplusapp;

import com.google.gson.annotations.SerializedName;

//로그인 요청 시 서버로 보낼 데이터

public class LoginData {
    @SerializedName("userid")
    String userid; //사용자 아이디


    @SerializedName("userPwd") //사용자 비밀번호
    String userPwd;

    public LoginData(String userid, String userPwd) {
        this.userid = userid;
        this.userPwd = userPwd;
    }

}
