package com.anb.news.Retrofit;

import com.anb.news.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Agung Nursatria on 4/6/2018.
 */

public class RetroServer {

    public static final String base_url = Constant.BASE_URL;

    private static Retrofit retrofit;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static RequestInterface getRequestService() {
        return getClient().create(RequestInterface.class);
    }
}
