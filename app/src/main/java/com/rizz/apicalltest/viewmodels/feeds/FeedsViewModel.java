package com.rizz.apicalltest.viewmodels.feeds;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rizz.apicalltest.aaprepos.feeds.FeedsRepositoryManager;
import com.rizz.apicalltest.aaprepos.feeds.model.Feeds;
import com.rizz.apicalltest.utils.NetworkStatus;
import com.rizz.apicalltest.viewmodels.BaseViewModel;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FeedsViewModel extends BaseViewModel {
    private MutableLiveData<List<Feeds>> mutableLiveData;
    private FeedsRepositoryManager feedsRepositoryManager;

    public FeedsViewModel() {
        this.feedsRepositoryManager=new FeedsRepositoryManager();
        this.mutableLiveData=new MutableLiveData<>();
    }

    public LiveData<List<Feeds>> updateFeeds(){
        return mutableLiveData;
    }

    public class NetworkTag{
        public static final int FETCH_FEEDS=1;
    }

    public void fetchFeeds(){

        if(!isNetworkAvailable()){
            networkCall.postValue(new NetworkCall(NetworkTag.FETCH_FEEDS, NetworkStatus.NO_INTERNET));
            return;
        }

        networkCall.postValue(new NetworkCall(NetworkTag.FETCH_FEEDS, NetworkStatus.IN_PROCESS));

        Observer<List<Feeds>> observer=new Observer<List<Feeds>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Feeds> feeds) {
                if(feeds!=null && feeds.size()>0){
                    mutableLiveData.postValue(feeds);
                }else{
                    networkCall.postValue(new NetworkCall(NetworkTag.FETCH_FEEDS, NetworkStatus.NO_DATA));
                }
            }

            @Override
            public void onError(Throwable e) {
                networkCall.postValue(new NetworkCall(NetworkTag.FETCH_FEEDS, NetworkStatus.ERROR));
            }

            @Override
            public void onComplete() {

            }
        };

        if(feedsRepositoryManager==null)
            feedsRepositoryManager=new FeedsRepositoryManager();

        feedsRepositoryManager.getFeeds().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
