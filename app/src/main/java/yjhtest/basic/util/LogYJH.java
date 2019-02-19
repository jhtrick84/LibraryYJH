package yjhtest.basic.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 로그 출력
 * @author 윤준혁
 */
public class LogYJH {

    private static boolean DEBUG = false;
    private final static boolean PLS_SHOW_LOG = false; // true : 디버깅모드가 아니라도 로그가 출력됨

    /**
     * E 로그 발생
     * @param str
     */
    public static void e(String str){
        if (isDebuging())
            Log.e("YJHTEST", str);
    }

    /**
     * Exception 로그 실행
     * @param e
     */
    public static void e(Exception e) {
        if (isDebuging())
            e(Log.getStackTraceString(e));
    }

    /**
     * Debug모드인지 확인
     * @return
     */
    private static boolean isDebuging(){
        if (!DEBUG && !PLS_SHOW_LOG)
            return false;
        return true;
    }

    /**
     * Application 클래스로부터 Context를 받아서 DEBUG모드인지를 세팅
     * @param mContext
     */
    public static void setDebuging(Context mContext){
        PackageManager mPackageManager = mContext.getPackageManager();
        try {
            ApplicationInfo mApplicationInfo = mPackageManager.getApplicationInfo(mContext.getPackageName(), 0);
            DEBUG = (0 != (mApplicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (Exception e) {
            DEBUG = false;
        }
    }
}
