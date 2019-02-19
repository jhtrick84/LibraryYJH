package yjhtest.basic.util.net;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import yjhtest.basic.util.LogYJH;

/**
 * RESTful 서버 통신 실시
 * @author 윤준혁
 */
public class ConnYJH extends UrlYJH {

    /**
     * 통신 기본 인터페이스
     */
    public interface IConn {
        void success(String data);

        void failed(String msg);
    }

    public static void post(final String url, Object body, final IConn listener) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();

            IRetrofit task = retrofit.create(IRetrofit.class);
            final Call<String> mCall = task.conn(url, GsonYJH.getHashMapFromVO(body));
            Callback<String> mCallback = new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        String data = response.body();
                        LogYJH.e("* RETURN : " + url + " : " + data);
                        listener.success(url + " : " + data);
                    } catch (Exception e) {
                        LogYJH.e(Log.getStackTraceString(e));
                        listener.failed("error");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    try {
                        LogYJH.e("-- : " + Log.getStackTraceString(t));
                    } catch (Exception e) {
                        LogYJH.e("** : " + e.getMessage());
                    }
                    listener.failed("");
                }
            };
            mCall.enqueue(mCallback);
        } catch (Exception e) {
            LogYJH.e(Log.getStackTraceString(e));
        }
    }

    public static void get(final String url, final IConn listener) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(GET_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();

            IRetrofit task = retrofit.create(IRetrofit.class);
            final Call<String> mCall = task.conn(url, "413893d90a34a0c40fdf9ae5c10df44a", "삼강빌딩");
            Callback<String> mCallback = new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    try {
                        String data = response.body();
                        LogYJH.e("* RETURN : " + url + " : " + data);
                        listener.success(url + " : " + data);
                    } catch (Exception e) {
                        LogYJH.e(Log.getStackTraceString(e));
                        listener.failed("error");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    try {
                        LogYJH.e("-- : " + Log.getStackTraceString(t));
                    } catch (Exception e) {
                        LogYJH.e("** : " + e.getMessage());
                    }
                    listener.failed("");
                }
            };
            mCall.enqueue(mCallback);
        } catch (Exception e) {
            LogYJH.e(Log.getStackTraceString(e));
        }
    }

    /**
     * Header 변조를 위해 OkHttpClient를 사용
     * @return
     */
    private static OkHttpClient getOkHttpClient() {

        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder mBuilder = chain.request().newBuilder();

//                mBuilder.header("User-Agent", "Android");
//                mBuilder.header("Content-Type", "application/x-www-form-urlencoded");
                Request request = mBuilder.build();

//                LogYJH.e("- HEADER : " + request.headers().toString());
                LogYJH.e("- REQUEST : " + request.url());
                LogYJH.e("- BODY : " + bodyToString(request));

                return chain.proceed(request);
            }
        })
                .writeTimeout(5, TimeUnit.MINUTES)
                .build();
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (Exception e) {
            return "did not work";
        }
    }
}
