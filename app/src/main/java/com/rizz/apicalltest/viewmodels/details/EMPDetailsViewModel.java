package com.rizz.apicalltest.viewmodels.details;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rizz.apicalltest.aaprepos.details.EmployeeDetailsRepositoryManager;
import com.rizz.apicalltest.aaprepos.details.model.EmployeeDetails;
import com.rizz.apicalltest.utils.NetworkStatus;
import com.rizz.apicalltest.viewmodels.BaseViewModel;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EMPDetailsViewModel extends BaseViewModel {
    private EmployeeDetailsRepositoryManager employeeDetailsRepositoryManager;
    private MutableLiveData<List<EmployeeDetails>> mutableLiveData;

    public EMPDetailsViewModel() {
        this.employeeDetailsRepositoryManager = new EmployeeDetailsRepositoryManager();
        this.mutableLiveData = new MutableLiveData<>();
    }

    public class NetworkTag {
        public final static int FETCH_DETAILS_TAG = 1;
    }

    public LiveData<List<EmployeeDetails>> updateEmpDetails() {
        return mutableLiveData;
    }

    public void fetchEmpDetails() {
        if (!isNetworkAvailable()) {
            networkCall.postValue(new NetworkCall(NetworkTag.FETCH_DETAILS_TAG, NetworkStatus.NO_INTERNET));
            return;
        }

        networkCall.postValue(new NetworkCall(NetworkTag.FETCH_DETAILS_TAG, NetworkStatus.IN_PROCESS));

        Observer<List<EmployeeDetails>> observer = new Observer<List<EmployeeDetails>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<EmployeeDetails> employeeDetails) {
                networkCall.postValue(new NetworkCall(NetworkTag.FETCH_DETAILS_TAG, NetworkStatus.DONE));
                if (employeeDetails != null && employeeDetails.size() > 0) {
                    mutableLiveData.postValue(employeeDetails);
                }else{
                    networkCall.postValue(new NetworkCall(NetworkTag.FETCH_DETAILS_TAG, NetworkStatus.NO_DATA));
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                networkCall.postValue(new NetworkCall(NetworkTag.FETCH_DETAILS_TAG, NetworkStatus.ERROR));
            }

            @Override
            public void onComplete() {

            }
        };

        if(employeeDetailsRepositoryManager==null)
            employeeDetailsRepositoryManager=new EmployeeDetailsRepositoryManager();

        employeeDetailsRepositoryManager.getEmployee()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
