package com.example.favoritemovies;

import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.favoritemovies.adapter.FavoriteMovieAdapter;
import com.example.favoritemovies.helper.Config;
import com.example.favoritemovies.model.FavModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView recyclerView;
    private ArrayList<FavModel> favoriteModels;
    private com.example.favoritemovies.adapter.FavoriteMovieAdapter favoriteMovieAdapter;
    private static final int LOADER = 100;
    private String TAG = "Main";
    private Cursor  cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);

        favoriteModels = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        favoriteMovieAdapter  = new FavoriteMovieAdapter(MainActivity.this);
        favoriteMovieAdapter.setListMovie(cursor);
        recyclerView.setAdapter(favoriteMovieAdapter);

        if (savedInstanceState == null) {
            getSupportLoaderManager().restartLoader(LOADER, null, this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Config.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursor = data;
        favoriteMovieAdapter.setListMovie(cursor);
        favoriteMovieAdapter.notifyDataSetChanged();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        favoriteMovieAdapter.setListMovie(null);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportLoaderManager().destroyLoader(LOADER);
    }
}
