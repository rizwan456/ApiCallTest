package com.rizz.apicalltest.viewmodels.profile;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rizz.apicalltest.aaprepos.profile.ProfileRepositoryManager;
import com.rizz.apicalltest.aaprepos.profile.model.Profile;
import com.rizz.apicalltest.utils.NetworkStatus;
import com.rizz.apicalltest.viewmodels.BaseViewModel;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel {
    MutableLiveData<List<Profile>> mutableLiveData;
    ProfileRepositoryManager repositoryManager;

    public ProfileViewModel() {
        this.mutableLiveData = new MutableLiveData<>();
        this.repositoryManager = new ProfileRepositoryManager();
    }

    public class NetworkTag{
        public final static int FETCH_PROFILE=1;
    }

    public LiveData<List<Profile>> updateProfileData(){
        return mutableLiveData;
    }

    public void fecthProfileDetails(){
        if(!isNetworkAvailable()){
            networkCall.postValue(new NetworkCall(NetworkTag.FETCH_PROFILE, NetworkStatus.NO_INTERNET));
            return;
        }
        networkCall.postValue(new NetworkCall(NetworkTag.FETCH_PROFILE, NetworkStatus.IN_PROCESS));

        Observer<List<Profile>> observer=new Observer<List<Profile>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Profile> profiles) {
                networkCall.postValue(new NetworkCall(NetworkTag.FETCH_PROFILE, NetworkStatus.DONE));

                if(profiles!=null && profiles.size()>0){
                    mutableLiveData.postValue(profiles);
                }else{
                    networkCall.postValue(new NetworkCall(NetworkTag.FETCH_PROFILE, NetworkStatus.NO_DATA));
                }
            }

            @Override
            public void onError(Throwable e) {
                networkCall.postValue(new NetworkCall(NetworkTag.FETCH_PROFILE, NetworkStatus.ERROR));
            }

            @Override
            public void onComplete() {

            }
        };

        if(repositoryManager==null)
            repositoryManager=new ProfileRepositoryManager();

        repositoryManager.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }


}
