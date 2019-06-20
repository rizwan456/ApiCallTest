package com.rizz.apicalltest.viewmodels.notification;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rizz.apicalltest.aaprepos.notification.NotificationRepositoryManager;
import com.rizz.apicalltest.aaprepos.notification.model.Notification;
import com.rizz.apicalltest.utils.NetworkStatus;
import com.rizz.apicalltest.viewmodels.BaseViewModel;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotificationViewModel extends BaseViewModel {
    MutableLiveData<List<Notification>> mutableLiveData;
    NotificationRepositoryManager repositoryManager;

    public NotificationViewModel() {
        this.mutableLiveData = new MutableLiveData<>();
        this.repositoryManager = new NotificationRepositoryManager();
    }

    class NetworkTag {
        public final static int GET_NOTIFICATIONS = 1;
    }

    public LiveData<List<Notification>> updateNotification(){
        return mutableLiveData;
    }

    public void fetchNotifications(){
        if(!isNetworkAvailable()){
            networkCall.postValue(new NetworkCall(NetworkTag.GET_NOTIFICATIONS, NetworkStatus.NO_INTERNET));
        }
        networkCall.postValue(new NetworkCall(NetworkTag.GET_NOTIFICATIONS, NetworkStatus.IN_PROCESS));

        Observer<List<Notification>> observer=new Observer<List<Notification>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Notification> notifications) {
                if(notifications!=null && notifications.size()>0){
                    mutableLiveData.postValue(notifications);
                }else{
                    networkCall.postValue(new NetworkCall(NetworkTag.GET_NOTIFICATIONS, NetworkStatus.NO_DATA));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        if(repositoryManager==null)
            repositoryManager=new NotificationRepositoryManager();

        repositoryManager.getNotifications()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
