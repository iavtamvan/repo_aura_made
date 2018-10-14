package com.example.favoritemovies;

import android.content.Intent;
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
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.favoritemovies.helper.Config;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        imgPoster = findViewById(R.id.poster);
        tvReleaseDate = findViewById(R.id.release_date);
        tvVote = findViewById(R.id.vote);
        tvLang = findViewById(R.id.language);
        tvOverview = findViewById(R.id.overview);
        fab = findViewById(R.id.fab);
        imgBackdrop = findViewById(R.id.backdrop);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        Log.d("TEST", "onCreatePOSTER COBA: " +  poster);
        Log.d("TEST", "onCreateBACK COBA: " +  backdrop);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + backdrop).error(R.drawable.ic_launcher_background).into(imgBackdrop);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + poster).error(R.drawable.ic_launcher_background).into(imgPoster);

        tvReleaseDate.setText(relase_date);
        tvVote.setText(vote);
        tvLang.setText(language);
        tvOverview.setText(overview);

    }




}
