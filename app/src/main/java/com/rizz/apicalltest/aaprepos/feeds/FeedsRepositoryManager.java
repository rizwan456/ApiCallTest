package com.rizz.apicalltest.aaprepos.feeds;

import com.rizz.apicalltest.aaprepos.BaseRepositoryManager;
import com.rizz.apicalltest.aaprepos.feeds.model.Feeds;
import com.rizz.apicalltest.utils.Utility;

import java.util.List;

import io.reactivex.Observable;

public class FeedsRepositoryManager extends BaseRepositoryManager {
    public Observable<List<Feeds>> getFeeds(){
        return Observable.just(Utility.generateFeeds());
    }
}
