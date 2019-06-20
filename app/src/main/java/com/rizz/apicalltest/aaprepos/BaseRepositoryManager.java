package com.rizz.apicalltest.aaprepos;

import com.rizz.apicalltest.network.NetworkManager;

import retrofit2.Retrofit;

/**
 * Created by Gokul Kalagara (Mr.Psycho) on 28-02-2019.
 * at 14:41
 * Frost-Interactive
 */

/*
 *   We extend this in network call places
 *   we use AppSharedPreferences for storing the data and retriving the data inside network calls
 *   AppRequest is used which type of request we want it return.
 *   NetworkManager is used to request which type of call you need (Mulitpart, JsonObject,KeyValuePair)
 */
public class BaseRepositoryManager {

    //public AppSharedPreferences appSharedPreferences = AppSharedPreferences.getInstance();
    private NetworkManager networkManager = null;


    public Retrofit getRetrofit() {
        try {
            if (networkManager == null)
                networkManager = new NetworkManager();

            return networkManager.getRetrofit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Retrofit getRetrofitByBaseUrl(String baseUrl) {
        try {
            if (networkManager == null)
                networkManager = new NetworkManager();


            return networkManager.getRetrofitByBaseUrl(baseUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getBufferedReaderByURL(String url) {
        if (networkManager == null)
            networkManager = new NetworkManager();

        return networkManager.getBufferedReaderByURL(url);
    }
}