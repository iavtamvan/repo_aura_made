package com.example.aura.submission4_basisdata.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.aura.submission4_basisdata.helper.Config.Movies.TABLE_FAV;

public final class Config {

    public static final String TABLE_NAME ="listfilm";
    public static final String AUTHORITY = "com.example.aura.submission4_basisdata";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

//    public  static  final Uri CONTENT_URI = new Uri.Builder().scheme("content://")
//            .authority(AUTHORITY)
//            .appendPath(TABLE_FAV)
//            .build();

//            public static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(PATH_TASKS).build();
    public static final class Movies implements BaseColumns {
        public static Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendEncodedPath(TABLE_NAME).build();

        public static final String TABLE_FAV = "favorite";
        public static final String CREATE_TABLE = "create table favorite(id integer primary key, tittle text null, release_date text null, vote_average text null,  original_language text null, overview text null, status_favorite text null);";
        public static final String ID = "id";
        public static final String ID_ = "_id";
        public static final String TITTLE = "tittle";
        public static final String VOTE = "vote_average";
        public static final String LANGUAGE =  "original_language";
        public static final String OVERVIEW = "overview";
        public static final String STATUS_FAVORITE = "status_favorite";
        public static final String POSTER = "poster";
        public static final String RELEASE_DATE = "release_date";
        public static final String BACKDROP_PATH = "backdroph_path";
    }
    public static final String ERROR_NETWORK = "Periksai koneksi anda";
    public static final String ERROR_LIST = "Response Skiped";



    private final static String PREF_NAME = "reminderMoviePreferences";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public Config(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public static String getColomnString(Cursor cursor, String colomnName) {
        return cursor.getString(cursor.getColumnIndex(colomnName));
    }

    public static  int getCoulumnInt (Cursor cursor, String colomnName){
        return  cursor.getInt(cursor.getColumnIndex(colomnName));
    }

    public static  long getColumnLong(Cursor cursor, String colomnName){
        return  cursor.getLong(cursor.getColumnIndex(colomnName));
    }


}
