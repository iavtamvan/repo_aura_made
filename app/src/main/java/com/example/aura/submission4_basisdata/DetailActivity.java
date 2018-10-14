package com.example.aura.submission4_basisdata;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aura.submission4_basisdata.database.FavoriteDataHelper;
import com.example.aura.submission4_basisdata.helper.Config;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;

public class DetailActivity extends AppCompatActivity {

    private String id_movie;
    private String poster;
    private String backdrop;
    private String title;
    private String overview;
    private String relase_date;
    private String vote;
    private String language;
    private int id;

    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private Toolbar toolbar;
    private ImageView imgPoster;
    private ImageView imgBackdrop;
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvVote;
    private TextView tvLang;
    private TextView tvOverview;
    private FloatingActionButton fab;

    private FavoriteDataHelper favoriteDataHelper;
    private MaterialFavoriteButton btnFav;

    private SharedPreferences sharedPreferences;
//    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
//        toolbar = findViewById(R.id.toolbar);


        imgPoster = findViewById(R.id.poster);
        tvReleaseDate = findViewById(R.id.release_date);
        tvVote = findViewById(R.id.vote);
        tvLang = findViewById(R.id.language);
        tvOverview = findViewById(R.id.overview);
        fab = findViewById(R.id.fab);
        imgBackdrop = findViewById(R.id.backdrop);
        fab = findViewById(R.id.fab);
        btnFav = findViewById(R.id.btnFavorit);

        favoriteDataHelper = new FavoriteDataHelper(this);
        SQLiteDatabase sqLiteDatabase  = favoriteDataHelper.getWritableDatabase();
        sqLiteDatabase.isOpen();


//        SQLiteDatabase db  = favoriteDataHelper.getWritableDatabase();
////        db.isOpen();
//        db.execSQL("insert into favorite(_id, id, title, vote_average, original_language, overview, status_favorite) values('"+
////                _id.getText().toString() + " ','"+
////                id.getText().toString() + "','"+
//                tvTitle.getText().toString() + "','"+
//                tvVote.getText().toString() + "','"+
//                tvLang.getText().toString() + "','"+
//                tvOverview.getText().toString() + "','"+
////                tv.getText().toString() + "','"+
//                "')");
//                Toast.makeText(getApplicationContext(), "Insert Sukses!",
//                        Toast.LENGTH_LONG).show();
//                MainActivity.main.RefreshList();
//                finish();

        Intent intent = getIntent();
        id_movie = intent.getStringExtra("id_movie");
        poster = intent.getStringExtra("poster");
        backdrop = intent.getStringExtra("backdrop");
        title = intent.getStringExtra("title");
        getSupportActionBar().setTitle(title);
        overview = intent.getStringExtra("overview");
        relase_date = intent.getStringExtra("relase_date");
        vote = intent.getStringExtra("vote");
        language = intent.getStringExtra("language");

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + backdrop).error(R.drawable.ic_launcher_background).into(imgBackdrop);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + poster).error(R.drawable.ic_launcher_background).into(imgPoster);

        tvReleaseDate.setText(relase_date);
        tvVote.setText(vote);
        tvLang.setText(language);
        tvOverview.setText(overview);


        sharedPreferences = getApplicationContext().getSharedPreferences("SETTING", 0);
        Boolean favorit = sharedPreferences.getBoolean("Favorite"+ title, false);
        if (favorit){
            btnFav.setAnimateFavorite(true);
            btnFav.setVisibility(View.GONE);

        }
        btnFav.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener(){
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorit){
                        if (favorit){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putBoolean("Favorite Added", true);
                            editor.putBoolean("Favorite"+title,true);

                            editor.commit();
                            save();
                            Snackbar.make(buttonView, "Added to Favorite",
                                    Snackbar.LENGTH_SHORT).show();
                        }else{
                            int movie_id = getIntent().getExtras().getInt("id");
                            favoriteDataHelper = new FavoriteDataHelper(DetailActivity.this);
                            favoriteDataHelper.deleteFavorite(movie_id);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putBoolean("Favorite Removed", true);
                            editor.putBoolean("Favorite"+title,true);
                            editor.commit();
                            Snackbar.make(buttonView, "Removed from Favorite",
                                    Snackbar.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }



    private void save() {
        ContentValues contentValues  = new ContentValues();
        contentValues.put(Config.Movies.ID, String.valueOf(id));
        contentValues.put(Config.Movies.TITTLE, title);
        contentValues.put(Config.Movies.VOTE, vote);
        contentValues.put(Config.Movies.LANGUAGE, language);
        contentValues.put(Config.Movies.OVERVIEW, overview);
        contentValues.put(Config.Movies.STATUS_FAVORITE, "favorite");
        contentValues.put(Config.Movies.POSTER , poster);
        contentValues.put(Config.Movies.RELEASE_DATE , relase_date);
        contentValues.put(Config.Movies.BACKDROP_PATH , backdrop);
        Uri uri = getContentResolver().insert(Config.Movies.CONTENT_URI,contentValues);
        Log.d("uri", "datadisimpan: "+ uri);
    }


}
