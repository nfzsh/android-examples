package com.example.myexample04.service;

import com.example.myexample04.util.MyApplication;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .cache(new Cache(MyApplication.getInstance().getCacheDir(),10 * 1024 * 1024))
            .build();
    private static Retrofit retrofit = new Retrofit.Builder()
            // 本地测试不能使用localhost，使用本地IP
            // 根路径必须已，/，结束
            // .baseUrl("http://192.168.1.3:8080/api/")
            .baseUrl("http://www.whyman.site/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static NewsService getNewsService(){
        return retrofit.create(NewsService.class);
    }
}
