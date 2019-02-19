package yjhtest.basic.util.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import yjhtest.basic.util.LogYJH;

import static yjhtest.basic.util.net.ConnYJH.BASE_URL;
import static yjhtest.basic.util.net.ConnYJH.bodyToString;

/**
 * Retrofit을 사용하기 위한 유틸
 * @author 윤준혁
 */
public class RetrofitManager {

    private static IRetrofit mRetrofitInterface;
    static volatile Retrofit mRetrofit = null;

    private RetrofitManager(){
    }

    public static IRetrofit getManager() {
        initManager();
        return mRetrofitInterface;
    }

    public static void initManager() {
        if (mRetrofitInterface == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitInterface == null) {
                    mRetrofitInterface = getRetrofit().create(IRetrofit.class);
                }
            }
        }
    }

    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofit == null) {mRetrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .client(getOkHttpClient())
                            .build();
                }
            }
        }

        return mRetrofit;
    }

    public static OkHttpClient getOkHttpClient(){
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request.Builder mBuilder = chain.request().newBuilder();

//                mBuilder.header("User-Agent", "Android");
//                mBuilder.header("Content-Type", "application/x-www-form-urlencoded");
                okhttp3.Request request = mBuilder.build();

//                LogYJH.e("MANAGER - HEADER : " + request.headers().toString());
                LogYJH.e("MANAGER * REQUEST : " + request.url());
                LogYJH.e("MANAGER - BODY : "  + bodyToString(request));

                return chain.proceed(request);
            }
        })
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();

        return httpClient;
    }
}
