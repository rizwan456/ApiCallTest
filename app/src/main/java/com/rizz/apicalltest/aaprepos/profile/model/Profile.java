package com.rizz.apicalltest.aaprepos.profile.model;

public class Profile {
    private String fName;
    private String lName;
    private String email;
    private String mobile;
    private String address;
    private String imgUrl;

    public Profile(String fName, String lName, String email, String mobile, String address, String imgUrl) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.imgUrl = imgUrl;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
