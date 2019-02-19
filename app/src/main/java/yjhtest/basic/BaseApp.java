package yjhtest.basic;

import android.app.Application;

import com.bumptech.glide.Glide;

import yjhtest.basic.util.LogYJH;

/**
 * 애플리케이션 클래스
 * @author 윤준혁
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initDebug();
    }

    private void initDebug(){
        LogYJH.setDebuging(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

        Glide.get(this).clearMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        Glide.get(this).trimMemory(level);
    }
}
