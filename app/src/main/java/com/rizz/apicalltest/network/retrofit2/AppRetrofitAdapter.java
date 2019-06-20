package com.rizz.apicalltest.network.retrofit2;


import com.rizz.apicalltest.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppRetrofitAdapter {

    /*
     *   To send network requests to an API, we need to use the Retrofit Builder class and specify the base URL for the service.
     *   So, create a class named AppRetrofitAdapter.java .

     *   Here BASE_URL – it is basic URL of our API. We will use this URL for all requests later.
     */
    public static Retrofit getRetrofit() {
        return (new Retrofit.Builder().client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build());
    }

    public static Retrofit getRetrofitByBaseUrl(String baseUrl) {
        return (new Retrofit.Builder().client(getClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build());
    }


    /*
     *   you can use an interceptor for this.
     *   An interceptor is used to modify each request before it is performed and alters the request loggers
     */
    private static OkHttpClient getClient() {
        RetrofitNetworkLogger logging = new RetrofitNetworkLogger();
        // set your desired log level -> NONE
        logging.setLevel(RetrofitNetworkLogger.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(Constants.RETROFIT_CONNECTION_TIME_OUT, TimeUnit.MINUTES)
                .readTimeout(Constants.RETROFIT_CONNECTION_TIME_OUT, TimeUnit.MINUTES)
                .build();

        return client;
    }
}
