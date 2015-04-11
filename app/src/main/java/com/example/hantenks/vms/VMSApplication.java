package com.example.hantenks.vms;

import android.app.Application;

/**
 * Created by user on 4/10/2015.
 */
public class VMSApplication extends Application {
    private static VMSApplication singleton;
    private String authToken;

    public VMSApplication getInstance() {
        return singleton;
    }

    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String token) {
        authToken = token;


    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        authToken = "None";
    }

}
