package com.rizz.apicalltest.viewmodels.models;

import android.os.Bundle;

import com.rizz.apicalltest.utils.NetworkStatus;


/**
 * Created by Gokul Kalagara (Mr. Psycho) on 02-04-2019.
 * <p>
 * Frost
 */
public class NetworkCall {
    private String id;
    private int networkTag;
    private Bundle result;
    private NetworkStatus networkStatus;

    public NetworkCall(String id, int networkTag, Bundle result, NetworkStatus networkStatus) {
        this.id = id;
        this.networkTag = networkTag;
        this.result = result;
        this.networkStatus = networkStatus;
    }

    public NetworkCall(int networkTag, NetworkStatus networkStatus) {
        this.networkTag = networkTag;
        this.networkStatus = networkStatus;
    }

    public NetworkCall(int networkTag, Bundle result, NetworkStatus networkStatus) {
        this.networkTag = networkTag;
        this.result = result;
        this.networkStatus = networkStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int
    getNetworkTag() {
        return networkTag;
    }

    public void setNetworkTag(int networkTag) {
        this.networkTag = networkTag;
    }

    public Bundle getBundle() {
        return result;
    }

    public void setBundle(Bundle result) {
        this.result = result;
    }

    public NetworkStatus getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(NetworkStatus networkStatus) {
        this.networkStatus = networkStatus;
    }
}
