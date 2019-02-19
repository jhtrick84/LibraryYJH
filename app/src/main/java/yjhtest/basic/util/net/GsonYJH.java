package yjhtest.basic.util.net;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yjhtest.basic.util.LogYJH;

/**
 * GSON을 이용하여 Json 컨트롤
 * @author 윤준혁
 */
public class GsonYJH {
    /**
     * json 데이터와 클래스명을 전달하면 VO에 데이터를 삽입하여 리턴
     * @param str
     * @param cls
     * @return
     */
    public static <T> Object getObject(String str, final Class<T> cls){
        try {
            Gson gson = new Gson();
            return gson.fromJson(str, cls);
        }catch(Exception e){
            LogYJH.e(Log.getStackTraceString(e));
            return null;
        }
    }

    /**
     * json 데이터와 클래스명을 전달하면 리스트VO에 데이터를 삽입하여 리턴
     * @param str
     * @param <T>
     * @return
     */
    public static <T> Object getListObject(String str){
        try {
            Gson gson = new Gson();
            return gson.fromJson(str, new TypeToken<List<T>>(){}.getType());
        }catch(Exception e){
            LogYJH.e(Log.getStackTraceString(e));
            return null;
        }
    }

    /**
     * Object를 전달받아서 String(json)으로 리턴
     * @param obj
     * @return
     */
    public static <T> String getString(T obj){
        try {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            return gson.toJson(obj);
        }catch(Exception e){
            LogYJH.e(Log.getStackTraceString(e));
            return null;
        }
    }

    /**
     * Json을 해쉬맵으로 변경
     * @param jsonString
     * @return
     */
    public static Map<String, String> getMap(String jsonString){
        return new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    /**
     * Object를 전달받아서 해쉬맵으로 변경
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> Map<String, String> getMap(T obj){
        String jsonString = getString(obj);
        return new Gson().fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
    }
}
