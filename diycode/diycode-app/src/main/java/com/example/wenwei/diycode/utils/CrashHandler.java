package com.example.wenwei.diycode.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";
    private static final boolean DEBUG = true;

    private static final String PATH = Environment.getExternalStorageDirectory().getPath()
            + "ryg_text/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";  // log文件的后缀名

    private String mPackageVersionName;
    private int mPackageVersionCode;

    // 系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private static CrashHandler sInstance = new CrashHandler();

    /**
     * 私有化构造函数，防止外部构造多个实例（即采用单例模式singleton）
     */
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return sInstance;
    }

    /**
     * 初始化
     *
     * @param context Context 上下文
     */
    public void init(Context context) {
        // 获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        // 获取Context，方便内部使用
        Context mContext = context.getApplicationContext();
        try {
            // 应用的版本名称和版本号
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
            mPackageVersionName = pi.versionName;
            mPackageVersionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Not found the version name of this package");
        }
    }

    /**
     * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
     * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            dumpExceptionToSDCard(e);
            uploadExceptionToServer(); // 这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
        } catch (Throwable ex) {
            e.printStackTrace();
        }

        //打印出当前调用栈信息
        e.printStackTrace();

        // 如果系统提供了默认的异常处理，则交给系统去结束我们的程序，否则就由我们自己结束
        if (mDefaultCrashHandler != null) {
            mDefaultCrashHandler.uncaughtException(t, e);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    /**
     * 导出异常信息到SD卡中
     *
     * @param ex Exception
     * @throws FileNotFoundException
     */
    private void dumpExceptionToSDCard(Throwable ex) throws FileNotFoundException {
        // 如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (DEBUG) {
                Log.w(TAG, "sdcard unmounted, skip dump exception");
                return;
            }
        }

        File dir = new File(PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));

        //以当前时间创建log文件
        File file = new File(PATH + time + FILE_NAME_SUFFIX);
        try {
            // TODO: Why not PrintWriter pw = new PrintWriter(file)?
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);   // 到处发生异常的时间

            dumpDeviceInfo(pw); // 导出手机信息

            pw.println();

            ex.printStackTrace(pw);

            pw.close();

        } catch (Exception e) {
            Log.e(TAG, "dump crash info failed");
        }
    }

    private void dumpDeviceInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        // 应用的版本名称和版本号
        pw.println("App Version: " + mPackageVersionName + "_" + mPackageVersionCode);

        // Android版本号
        pw.println("OS Version: " + Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT);

        // 手机制造商
        pw.println("Manufacturer: " + Build.MANUFACTURER);

        // 手机型号
        pw.println("Model: " + Build.MODEL);

        // CPU 架构
        pw.println("CPU ABI: " + Build.CPU_ABI);
    }

    private void uploadExceptionToServer() {
        // TODO: Upload Exception Message To Your Web Server
    }
}
