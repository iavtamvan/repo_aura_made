package com.example.aura.submission4_basisdata.rest;

import com.example.aura.submission4_basisdata.BuildConfig;
import com.example.aura.submission4_basisdata.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET(BuildConfig.POPULAR_MOVIE)
    Call<MovieModel> getMovie();
}
