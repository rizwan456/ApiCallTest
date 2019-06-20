package com.rizz.apicalltest.viewmodels.employee;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.net.Network;

import com.rizz.apicalltest.aaprepos.employees.EmployeeRepositoryManager;
import com.rizz.apicalltest.aaprepos.employees.model.Employee;
import com.rizz.apicalltest.utils.NetworkStatus;
import com.rizz.apicalltest.viewmodels.BaseViewModel;
import com.rizz.apicalltest.viewmodels.models.NetworkCall;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends BaseViewModel {
    private EmployeeRepositoryManager employeeRepositoryManager;
    private MutableLiveData<List<Employee>> mutableLiveData;

    public EmployeeViewModel() {
        this.employeeRepositoryManager = new EmployeeRepositoryManager();
        mutableLiveData = new MutableLiveData<>();
    }
    public class NetworkTags{
        public static final int FETCH_EMPLOYEE_TAG = 1;
    }

    public LiveData<List<Employee>> updateEmployees()
    {
        return mutableLiveData;
    }

    public void fetchEmployees()
    {
        if(!isNetworkAvailable())
        {
            networkCall.postValue(new NetworkCall(NetworkTags.FETCH_EMPLOYEE_TAG, NetworkStatus.NO_INTERNET));
            return;
        }
        networkCall.postValue(new NetworkCall(NetworkTags.FETCH_EMPLOYEE_TAG, NetworkStatus.IN_PROCESS));

        Observer<List<Employee>> observer = new Observer<List<Employee>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<Employee> employees) {
                networkCall.postValue(new NetworkCall(NetworkTags.FETCH_EMPLOYEE_TAG, NetworkStatus.DONE));
                if(employees!=null && employees.size()>0)
                {
                    mutableLiveData.postValue(employees);
                }
                else {
                    networkCall.postValue(new NetworkCall(NetworkTags.FETCH_EMPLOYEE_TAG, NetworkStatus.NO_DATA));
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                networkCall.postValue(new NetworkCall(NetworkTags.FETCH_EMPLOYEE_TAG, NetworkStatus.ERROR));
            }

            @Override
            public void onComplete() {

            }
        };

        if(employeeRepositoryManager==null)
            employeeRepositoryManager = new EmployeeRepositoryManager();

        employeeRepositoryManager.getEmployees()
                .delay(3,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);

    }
}
