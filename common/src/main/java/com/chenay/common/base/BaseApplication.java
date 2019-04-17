package com.chenay.common.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.apkfuns.logutils.LogUtils;
import com.chenay.common.storage.PreferenceUtil;
import com.chenay.common.thread.ThreadPoolUtil;
import com.chenay.common.utils.AlertMediaUtil;
import com.chenay.common.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 *  要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 *  * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 *  * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 *  * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 * @author Y.Chen5
 */
public class BaseApplication extends Application {
    private RefWatcher refWatcher;
    public static Context context;

    private static BaseApplication sInstance;
//    private List<ApplicationDelegate> mAppDelegateList;
    public static final String ROOT_PACKAGE = "com.chenay.common";

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        Utils.init(this);
//        mAppDelegateList = ClassUtils.getObjectsWithInterface(this, ApplicationDelegate.class, ROOT_PACKAGE);
//        for (ApplicationDelegate delegate : mAppDelegateList) {
//            delegate.onCreate();
//        }

        LogUtils.getLogConfig()
                .configAllowLog(true)
                .configTagPrefix("tti")
                .configShowBorders(true)
                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}");

        ThreadPoolUtil.initThreadPool();
        AlertMediaUtil.initAll(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        context = base;
        super.attachBaseContext(base);
        MultiDex.install(this);
        PreferenceUtil.install(this);
//        refWatcher = setupLeakCanary(base);
    }


    private RefWatcher setupLeakCanary(Context context) {
        if (LeakCanary.isInAnalyzerProcess(context)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install((Application) context);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication leakApplication = (BaseApplication) context.getApplicationContext();
        return leakApplication.refWatcher;
    }


//    @Override
//    public void onTerminate() {
//        super.onTerminate();
//        for (ApplicationDelegate delegate : mAppDelegateList) {
//            delegate.onTerminate();
//        }
//    }
//
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        for (ApplicationDelegate delegate : mAppDelegateList) {
//            delegate.onLowMemory();
//        }
//    }
//
//    @Override
//    public void onTrimMemory(int level) {
//        super.onTrimMemory(level);
//        for (ApplicationDelegate delegate : mAppDelegateList) {
//            delegate.onTrimMemory(level);
//        }
//    }
}
