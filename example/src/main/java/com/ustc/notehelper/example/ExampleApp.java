package com.ustc.notehelper.example;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ustc.latte.app.Latte;
import com.ustc.latte.ec.icon.FontEcModule;

/**
 * Created by DELL on 2018/3/2.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule()) //字体库
                .withIcon(new FontEcModule())  //第三方图标
                .configure();
    }
}
