package com.rizz.apicalltest.aaprepos.home;

import com.rizz.apicalltest.aaprepos.BaseRepositoryManager;
import com.rizz.apicalltest.aaprepos.home.model.Home;
import com.rizz.apicalltest.utils.Utility;

import java.util.List;

import io.reactivex.Observable;

public class HomeRepositoryManager extends BaseRepositoryManager {
    public Observable<List<Home>> getHome(){
        return Observable.just(Utility.generateHome());
    }
}
