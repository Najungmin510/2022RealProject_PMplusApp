package com.example.a2022realproject_pmplusapp;

import com.google.gson.annotations.SerializedName;

//회원가입 요청에 대한 "응답"으로 돌아올 데이터

public class JoinResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
