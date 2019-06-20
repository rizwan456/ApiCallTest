package com.rizz.apicalltest.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Base64;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rizz.apicalltest.utils.Utility;
import com.rizz.apicalltest.viewmodels.models.Message;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by Gokul Kalagara (Mr.Psycho) on 28-02-2019.
 * at 14:55
 * Frost-Interactive
 */
    public class BaseViewModel extends ViewModel {

    public MutableLiveData<NetworkCall> networkCall;
    public MutableLiveData<Integer> isRefreshToken;
    public MutableLiveData<Message> message;

    public BaseViewModel() {
        message = new MediatorLiveData<>();
        isRefreshToken = new MediatorLiveData<>();
        addNetworkCall();
    }


    public void addNetworkCall() {
        networkCall = new MediatorLiveData<>();
    }

    public LiveData<Message> getMessage() {
        return message;
    }

    public LiveData<NetworkCall> getNetworkCallStatus() {
        return networkCall;
    }

    public LiveData<Integer> controlRefreshToken() {
        return isRefreshToken;
    }

    public boolean isNetworkAvailable() {
        return Utility.isNetworkAvailable(Utility.getContext());
    }



    public void refreshToken(IRefreshToken iRefreshToken)
    {
        if(!isNetworkAvailable())
        {
            isRefreshToken.postValue(-1);
            iRefreshToken.isTokenRefreshed(false);
            return;
        }

        isRefreshToken.postValue(0);


    }


    public interface IRefreshToken
    {
        public void isTokenRefreshed(boolean isRefresh);
    }

}
