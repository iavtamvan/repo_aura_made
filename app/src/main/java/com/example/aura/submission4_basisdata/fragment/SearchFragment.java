package com.example.aura.submission4_basisdata.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aura.submission4_basisdata.R;
import com.example.aura.submission4_basisdata.adapter.MovieAdapter;
import com.example.aura.submission4_basisdata.model.MovieModel;
import com.example.aura.submission4_basisdata.model.ResultItem;
import com.example.aura.submission4_basisdata.rest.Client;
import com.example.aura.submission4_basisdata.rest.MovieService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {


    private RecyclerView rv;
    private Button btnSearch;
    private EditText eSearch;

    private MovieAdapter movieAdapter;
    private ArrayList<ResultItem> movieModels;




    public SearchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        rv = view.findViewById(R.id.recycler_movie);
        btnSearch = view.findViewById(R.id.btnSearch);
        movieModels= new ArrayList<>();

        eSearch = view.findViewById(R.id.search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSearchMovie();
            }
        });

        // kamu sholatvsek geee
        return view;
    }

    private void getSearchMovie() {

        MovieService movieService= Client.getInstanceRetrofit1();
        movieService.serachMovie(eSearch.getText().toString().trim())
                .enqueue(new Callback<MovieModel>() {
                    @Override
                    public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                        if (response.isSuccessful()){
                            movieModels = response.body().getResults();
                            movieAdapter = new MovieAdapter(getActivity(), movieModels);
                            rv.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv.setAdapter(movieAdapter);
                            movieAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(Call<MovieModel> call, Throwable t) {
                        Toast.makeText(getActivity(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
