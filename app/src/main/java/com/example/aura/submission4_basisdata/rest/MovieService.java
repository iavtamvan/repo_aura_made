package com.example.aura.submission4_basisdata.rest;

import com.example.aura.submission4_basisdata.BuildConfig;
import com.example.aura.submission4_basisdata.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET(BuildConfig.POPULAR_MOVIE)
    Call<MovieModel> getMovie();

    @GET("movie?api_key=0fe8ca3c33e758e0001314d6ed0415fc&language=en-US")
    Call<MovieModel> serachMovie(@Query("query") String query);
}
