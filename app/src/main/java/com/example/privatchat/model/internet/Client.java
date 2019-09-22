package com.example.privatchat.model.internet;



import com.example.privatchat.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class Client {
    private static Client mInstance = new Client();
    private static API api;
    private static final String BASE_URL = "http://10.1.196.2:8080/";

    public static Client getInstance(){
        return mInstance;
    }

    private Client(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel((BuildConfig.DEBUG) ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE))
                .readTimeout(100,TimeUnit.MILLISECONDS)
                .connectTimeout(100, TimeUnit.MILLISECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        api = retrofit.create(API.class);
    }
    public API getApi() {
        return api;
    }
}

