package com.rizz.apicalltest.aaprepos.notification;

import android.arch.lifecycle.LiveData;

import com.rizz.apicalltest.aaprepos.BaseRepositoryManager;
import com.rizz.apicalltest.aaprepos.notification.model.Notification;
import com.rizz.apicalltest.utils.Utility;

import java.util.List;

import io.reactivex.Observable;

public class NotificationRepositoryManager extends BaseRepositoryManager {
    public Observable<List<Notification>> getNotifications(){
        return Observable.just(Utility.generateNotification());
    }
}
