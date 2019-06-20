package com.rizz.apicalltest.aaprepos.profile;

import com.rizz.apicalltest.aaprepos.BaseRepositoryManager;
import com.rizz.apicalltest.aaprepos.profile.model.Profile;
import com.rizz.apicalltest.utils.Utility;

import java.util.List;

import io.reactivex.Observable;

public class ProfileRepositoryManager extends BaseRepositoryManager {
    public Observable<List<Profile>> getProfile(){
        return Observable.just(Utility.getProfile());
    }
}
