package com.example.wenwei.diycode.base.app;

import android.app.Application;
import android.content.Context;

import com.example.wenwei.diycode.data.cache.Config;
import com.example.wenwei.diycode.CrashHandler;
import com.example.wenwei.utils.ProcessUtil;
import com.example.wenwei.diycode_sdk.api.Diycode;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

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
        initCrashReport();

        Diycode.init(this, client_id, client_secret);

        Config.init(this);
    }

    /**
     * 如果App使用了多进程且各个进程都会初始化Bugly（例如在Application类onCreate()中初始化Bugly），
     * 那么每个进程下的Bugly都会进行数据上报，造成不必要的资源浪费。
     * <p>
     * 因此，为了节省流量、内存等资源，建议初始化的时候对上报进程进行控制，只在主进程下上报数据：判断是否
     * 是主进程（通过进程名是否为包名来判断），并在初始化Bugly时增加一个上报进程的策略配置。
     */
    private void initCrashReport() {
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        String processName = ProcessUtil.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, strategy);

    }
}
