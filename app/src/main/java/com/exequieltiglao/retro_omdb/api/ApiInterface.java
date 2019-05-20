package com.exequieltiglao.retro_omdb.api;

import com.exequieltiglao.retro_omdb.model.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/")
    Call<List<Search>> getSearch(@Query("s") String search, @Query("apikey") String apikey);

}
