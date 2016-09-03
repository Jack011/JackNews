package com.jackson.jacknews.util;


import com.jackson.jacknews.moudle.HuaBianResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by 90720 on 2016/8/26.
 */
public interface HttpService {

//  示例方法
//    @FormUrlEncoded
//    @POST("/frist/noIntercept/user/login.do")
//    Call<UserResponse> getUserByLogin(@Field("password") String password, @Field("phone") String phone);
//

    /**
     * 娱乐花边
     * @param apikey
     * @param num
     * @param page
     * @return
     */
    @GET("huabian/newtop")
    Call<HuaBianResponse> getHuaBianNewsData(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);

    @GET("mvtp/meinv")
    Call<HuaBianResponse> getMeiNvPicData(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);

    @GET("keji/keji")
    Call<HuaBianResponse> getKejiNewsData(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);

    @GET("tiyu/tiyu")
    Call<HuaBianResponse> getTiYuNewsData(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);

    @GET("qiwen/qiwen")
    Call<HuaBianResponse> getQiWenNewsData(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);

    @GET("")
    Call<HuaBianResponse> getSocialNewsData(@Header("apikey") String apikey, @Query("num") String num, @Query("page") String page);




}
