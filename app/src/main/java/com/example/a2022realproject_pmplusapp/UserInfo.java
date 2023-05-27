package com.example.a2022realproject_pmplusapp;

//회원정보수정 DB


public class UserInfo {

    private String user_info_name;
    private String birthday;
    private String phone;
    private String address;
    private String photoUrl;

    public UserInfo(String user_info_name, String birthday, String phone, String address, String photoUrl){
        this.user_info_name = user_info_name;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.photoUrl = photoUrl;
    }

    public String getUser_info_name(){
        return user_info_name;
    }

    public void setUser_info_name(String user_info_name) {
        this.user_info_name = user_info_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
