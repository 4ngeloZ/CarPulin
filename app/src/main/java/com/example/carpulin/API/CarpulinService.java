package com.example.carpulin.API;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarpulinService {

    static final String baseUrl = "https://carpulin-api.herokuapp.com/api/v1/";
    private static CarpulinServiceDefinition service = null;

    private static void initialize(Context ctx){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(ctx.getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(CarpulinServiceDefinition.class);
    }

    public static CarpulinServiceDefinition getService(Context context) {
        if (service == null) {
            initialize(context);
        }
        return service;
    }
}

