package com.jackson.jacknews.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 90720 on 2016/8/26.
 */
public class RetrofitUtil {


    public static HttpService instanceHttpService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)//添加打印拦截器
                .connectTimeout(30, TimeUnit.SECONDS)//设置请求超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebUrl.BEAUTY_WORD)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        HttpService httpService = retrofit.create(HttpService.class);
        return httpService;
    }
}
