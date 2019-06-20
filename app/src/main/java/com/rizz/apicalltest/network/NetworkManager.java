package com.rizz.apicalltest.network;


import com.rizz.apicalltest.network.retrofit2.AppRetrofitAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import retrofit2.Retrofit;


public class NetworkManager {

    /*
     *   This methods are used to control the retrofit
     */
    public Retrofit getRetrofit() {
        return AppRetrofitAdapter.getRetrofit();
    }

    public Retrofit getRetrofitByBaseUrl(String baseUrl) {
        return AppRetrofitAdapter.getRetrofitByBaseUrl(baseUrl);
    }

    public Object getBufferedReaderByURL(String url)
    {
        try
        {
            URL u = new URL(url);
            URLConnection conn = u.openConnection();
            return new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e;
        }
    }
}
