package com.rizz.apicalltest.aaprepos.employees.model;

public class Employee {
    private String name;
    private String mobile;
    private String imageURL;

    public Employee(String name, String mobile, String imageURL) {
        this.name = name;
        this.mobile = mobile;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
