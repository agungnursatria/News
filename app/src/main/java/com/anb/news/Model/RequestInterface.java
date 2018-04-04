package com.anb.news.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Agung Nursatria on 3/28/2018.
 */

public interface RequestInterface {

    @GET("top-headlines")
    Call<Headline> getHeadlines(@Query("apiKey") String apiKey);

}
