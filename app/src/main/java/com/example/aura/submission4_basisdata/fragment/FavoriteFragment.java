package com.example.aura.submission4_basisdata.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aura.submission4_basisdata.R;
import com.example.aura.submission4_basisdata.adapter.FavoriteAdapter;
import com.example.aura.submission4_basisdata.helper.Config;
import com.example.aura.submission4_basisdata.model.FavModel;

import java.util.ArrayList;


public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView recyclerFav;
    private ArrayList<FavModel> FavoriteModels;
    private FavoriteAdapter FavAdapter ;
    private static final int FILM_LOADER = 100;
    private String TAG = "Main";

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null){
            getActivity().getSupportLoaderManager().initLoader(FILM_LOADER, null, this);
            FavoriteModels = new ArrayList<>();
            initAdapter(FavoriteModels);
            recyclerFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null){
            getActivity().getSupportLoaderManager().initLoader(FILM_LOADER, null, this);
            FavoriteModels = new ArrayList<>();
            initAdapter(FavoriteModels);
            recyclerFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }


    private void initAdapter(ArrayList<FavModel> FavoriteModels) {
        FavAdapter = new FavoriteAdapter(getActivity(), FavoriteModels);
        recyclerFav.setAdapter(FavAdapter);
    }

    private void initView(View view) {
        recyclerFav = view.findViewById(R.id.recycler_fav);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case FILM_LOADER:
                Uri filmUri = Config.Movies.CONTENT_URI;
                Log.d(TAG, "onCreateLoader: "+ filmUri.toString());
                return new CursorLoader(getActivity(), filmUri, null, null, null, null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data.getCount()>0) {
            initAdapter(getMoviesFromCursor(data));
        } else {
            Toast.makeText(getActivity(), "favorite movie not found", Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<FavModel> getMoviesFromCursor(Cursor cursor) {
        ArrayList<FavModel> items = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()){
                do{
                    FavModel FavoriteModel = new FavModel(cursor);
                    FavoriteModel.setId(cursor.getString(cursor.getColumnIndex(Config.Movies.ID)));
                    FavoriteModel.setPosterPath(cursor.getString(cursor.getColumnIndex(Config.Movies.POSTER)));
                    FavoriteModel.setBackdropPath(cursor.getString(cursor.getColumnIndex(Config.Movies.BACKDROP_PATH)));
                    FavoriteModel.setTitle(cursor.getString(cursor.getColumnIndex(Config.Movies.TITTLE)));
                    FavoriteModel.setVoteAverage(cursor.getString(cursor.getColumnIndex(Config.Movies.VOTE)));
                    FavoriteModel.setReleaseDate(cursor.getString(cursor.getColumnIndex(Config.Movies.RELEASE_DATE)));
                    FavoriteModel.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(Config.Movies.LANGUAGE)));
                    FavoriteModel.setOverview(cursor.getString(cursor.getColumnIndex(Config.Movies.OVERVIEW)));
                    items.add(FavoriteModel);
                }while(cursor.moveToNext());
            }
        }
        return items;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
