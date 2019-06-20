package com.rizz.apicalltest.aaprepos.details;

import com.rizz.apicalltest.aaprepos.BaseRepositoryManager;
import com.rizz.apicalltest.aaprepos.details.model.EmployeeDetails;
import com.rizz.apicalltest.utils.Utility;

import java.util.List;

import io.reactivex.Observable;

public class EmployeeDetailsRepositoryManager extends BaseRepositoryManager {
    public Observable<List<EmployeeDetails>> getEmployee(){
        return Observable.just(Utility.generateDetails());
    }
}
