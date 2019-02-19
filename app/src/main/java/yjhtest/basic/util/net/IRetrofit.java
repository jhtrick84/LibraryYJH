package yjhtest.basic.util.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 *
 * @author 윤준혁
 */

public interface IRetrofit {

    @FormUrlEncoded
    @POST
    Call<String> conn(@Url String url, @FieldMap Map<String, String> obj);

    @POST
    Call<String> conn(@Url String url, @Body String obj);

    @GET
    Call<String> conn(@Url String url,  @Query("apikey") String apikey, @Query("query") String query);
}
