package com.example.a2022realproject_pmplusapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//서버로 데이터를 전달할 api 인터페이스

public interface ServiceApi {

    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);

}
