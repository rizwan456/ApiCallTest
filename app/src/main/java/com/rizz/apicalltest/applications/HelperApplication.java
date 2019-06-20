package com.rizz.apicalltest.applications;

import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;



public class HelperApplication extends MultiDexApplication
{
    private static HelperApplication helperApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        helperApplication = this;
    }

    public static synchronized HelperApplication getInstance()
    {
        return helperApplication;
    }



}
