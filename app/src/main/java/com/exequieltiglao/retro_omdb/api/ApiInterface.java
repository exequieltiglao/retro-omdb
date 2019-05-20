package com.exequieltiglao.retro_omdb.api;

import com.exequieltiglao.retro_omdb.model.Search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/")
    Call<Search> getSearch(@Query("s") String search, @Query("apikey") String apikey);

}
