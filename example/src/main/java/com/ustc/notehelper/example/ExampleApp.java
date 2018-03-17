package com.ustc.notehelper.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.ustc.latte.app.Latte;
import com.ustc.latte.ec.database.DatabaseManager;
import com.ustc.latte.ec.icon.FontEcModule;
import com.ustc.latte.net.interceptors.DebugInterceptor;

/**
 * Created by DELL on 2018/3/2.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://192.168.1.103:8080")
                .withIcon(new FontAwesomeModule()) //字体库
                .withIcon(new FontEcModule())  //第三方图标
                .withInterceptor(new DebugInterceptor("index",R.raw.abc))
                .configure();

//        initStetho();
//
//        DatabaseManager.getInstance().init(this);
    }

//        private void initStetho() {
//            Stetho.initialize(
//                    Stetho.newInitializerBuilder(this)
//                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                            .build());
//        }
}
