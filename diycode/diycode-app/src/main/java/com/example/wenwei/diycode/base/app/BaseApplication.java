package com.example.wenwei.diycode.base.app;

import android.app.Application;

import com.example.wenwei.diycode.utils.Config;
import com.example.wenwei.diycode.utils.CrashHandler;
import com.example.wenwei.diycode_sdk.api.Diycode;
import com.squareup.leakcanary.LeakCanary;

public class BaseApplication extends Application {

    public static final String client_id = "eafd369b";
    public static final String client_secret = "23ff7dcaa97ea9e8160098757d5ca6c5e11c15dbc9510155be1bf6d490e40b25";

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        CrashHandler.getInstance().init(this);

        Diycode.init(this, client_id, client_secret);

        Config.init(this);
    }
}
