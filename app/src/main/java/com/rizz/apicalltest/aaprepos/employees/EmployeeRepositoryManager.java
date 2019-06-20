package com.rizz.apicalltest.aaprepos.employees;

import com.rizz.apicalltest.aaprepos.BaseRepositoryManager;
import com.rizz.apicalltest.aaprepos.employees.model.Employee;
import com.rizz.apicalltest.utils.Utility;

import java.util.List;

import io.reactivex.Observable;

public class EmployeeRepositoryManager extends BaseRepositoryManager {

    public Observable<List<Employee>> getEmployees(){
        return Observable.just(Utility.generateList());
    }
}
