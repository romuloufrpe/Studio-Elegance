package com.example.romulo.studioelegancy.Application;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by romulo on 29/07/16.
 */
public class CustomApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
