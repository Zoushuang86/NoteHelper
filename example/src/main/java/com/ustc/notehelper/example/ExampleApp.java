package com.ustc.notehelper.example;

import android.app.Application;

import com.ustc.latte.app.Latte;

/**
 * Created by DELL on 2018/3/2.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("127.0.0.1/")
                .configure();
    }
}
