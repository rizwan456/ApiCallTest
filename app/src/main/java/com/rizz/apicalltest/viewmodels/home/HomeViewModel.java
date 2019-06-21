package com.rizz.apicalltest.viewmodels.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rizz.apicalltest.aaprepos.home.HomeRepositoryManager;
import com.rizz.apicalltest.aaprepos.home.model.Home;
import com.rizz.apicalltest.utils.NetworkStatus;
import com.rizz.apicalltest.viewmodels.BaseViewModel;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    MutableLiveData<List<Home>> mutableLiveData;
    HomeRepositoryManager repositoryManager;

    public HomeViewModel() {
        this.mutableLiveData=new MutableLiveData<>();
        this.repositoryManager=new HomeRepositoryManager();
    }

    public class NetworkTag{
        public static final int GETHOME_DATA=1;
    }

    public LiveData<List<Home>> updateHome(){
        return mutableLiveData;
    }

    public void fechHome(){
        if(!isNetworkAvailable()){
            networkCall.postValue(new NetworkCall(NetworkTag.GETHOME_DATA, NetworkStatus.NO_INTERNET));
        }
        networkCall.postValue(new NetworkCall(NetworkTag.GETHOME_DATA, NetworkStatus.IN_PROCESS));
        Observer<List<Home>> observer=new Observer<List<Home>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Home> homes) {
                if(homes!=null && homes.size()>0){
                    mutableLiveData.postValue(homes);
                }else{
                    networkCall.postValue(new NetworkCall(NetworkTag.GETHOME_DATA, NetworkStatus.NO_DATA));
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        if(repositoryManager==null){
            repositoryManager=new HomeRepositoryManager();
        }

        repositoryManager.getHome()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
