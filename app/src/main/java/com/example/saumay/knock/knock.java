package com.example.saumay.knock;

import android.app.Application;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by saumay on 10/5/17.
 */

public class knock extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty())
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
