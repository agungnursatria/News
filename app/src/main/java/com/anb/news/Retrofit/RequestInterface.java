package com.anb.news.Retrofit;

import com.anb.news.Model.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public interface RequestInterface {

    @GET("top-headlines")
    Call<News> getHeadlines(
            @Query("sources") String source,
            @Query("apiKey") String apiKey);

    @GET("everything")
    Call<News> getEverything(
            @Query("sources") String source,
            @Query("apiKey") String apiKey);

}
