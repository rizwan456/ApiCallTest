package com.rizz.apicalltest.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rizz.apicalltest.aaprepos.details.model.EmployeeDetails;
import com.rizz.apicalltest.aaprepos.employees.model.Employee;
import com.rizz.apicalltest.aaprepos.feeds.model.Feeds;
import com.rizz.apicalltest.aaprepos.home.model.Home;
import com.rizz.apicalltest.aaprepos.notification.model.Notification;
import com.rizz.apicalltest.aaprepos.profile.model.Profile;
import com.rizz.apicalltest.applications.HelperApplication;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                return false;
                /* aka, do nothing */
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Context getContext() {
        return HelperApplication.getInstance().getApplicationContext();
    }

    public static  List<Employee> generateList(){
        List<Employee> list=new ArrayList<>();
        list.add(new Employee("Rizwan","9823886966","https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"));
        list.add(new Employee("Gokul","7896523654","https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"));
        list.add(new Employee("Chinna","1236457896","https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"));
        return list;
    }

    public static List<EmployeeDetails> generateDetails(){
        List<EmployeeDetails> list=new ArrayList<>();
        list.add(new EmployeeDetails("Rizwan","9823886966"));
        list.add(new EmployeeDetails("Gokul","7896523654"));
        list.add(new EmployeeDetails("Chinna","1236457896"));
        return list;
    }

    public static List<Profile> getProfile(){
        List<Profile> list=new ArrayList<>();
        list.add(new Profile("Rizwan","Sheikh","Rizzsheikh786@gmail.com","+91-9823886966","Shivbag Colony AmmerPeth Hyderabad","https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"));
        return list;
    }

    public static List<Notification> generateNotification(){
        List<Notification> list=new ArrayList<>();

        return list;
    }

    public static List<Home> generateHome(){
        List<Home> list=new ArrayList<>();

        return list;
    }

    public static List<Feeds> generateFeeds(){
        List<Feeds> list=new ArrayList<>();
        list.add(new Feeds("https://img1.thelist.com/img/gallery/the-stunning-transformation-of-robert-downey-jr/intro-1499809511.jpg","https://static.gamespot.com/uploads/scale_super/171/1712892/3550623-avengers-endgame-finale-top3.jpg"));
        list.add(new Feeds("https://img1.thelist.com/img/gallery/the-stunning-transformation-of-robert-downey-jr/intro-1499809511.jpg","https://static.gamespot.com/uploads/scale_super/171/1712892/3550623-avengers-endgame-finale-top3.jpg"));
        list.add(new Feeds("https://img1.thelist.com/img/gallery/the-stunning-transformation-of-robert-downey-jr/intro-1499809511.jpg","https://static.gamespot.com/uploads/scale_super/171/1712892/3550623-avengers-endgame-finale-top3.jpg"));
        list.add(new Feeds("https://img1.thelist.com/img/gallery/the-stunning-transformation-of-robert-downey-jr/intro-1499809511.jpg","https://static.gamespot.com/uploads/scale_super/171/1712892/3550623-avengers-endgame-finale-top3.jpg"));
        list.add(new Feeds("https://img1.thelist.com/img/gallery/the-stunning-transformation-of-robert-downey-jr/intro-1499809511.jpg","https://static.gamespot.com/uploads/scale_super/171/1712892/3550623-avengers-endgame-finale-top3.jpg"));
        list.add(new Feeds("https://img1.thelist.com/img/gallery/the-stunning-transformation-of-robert-downey-jr/intro-1499809511.jpg","https://static.gamespot.com/uploads/scale_super/171/1712892/3550623-avengers-endgame-finale-top3.jpg"));
        return list;
    }
}
