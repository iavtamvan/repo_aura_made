package com.example.favoritemovies.helper;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public final class Config {
//    sama kayak database contract

    public static final String PATH_LIST_FILM ="listfilm";

    public static final class MoviesEntry implements BaseColumns {

        public static final String DATABASE_TABLE = "favorite";
        public static final String CREATE_TABLE = "create table favorite(id integer primary key, tittle text null, tgl text null, vote_average text null, vote_count text null, original_language text null, overview text null, status_favorite text null);";
        public static final String FIELD_ID = "id";
        public static final String FIELD_ID_ = "_id";
        public static final String FIELD_TITTLE = "tittle";
        public static final String FIELD_VOTE_AVERAGE = "vote_average";
        public static final String FIELD_ORIGINAL_LANGUAGE =  "original_language";
        public static final String FIELD_OVERVIEW = "overview";
        public static final String FIELD_POSTER_PATH = "poster";
        public static final String FIELD_RELEASE_DATE = "release_date";
        public static final String FIELD_BACKDROPH_PATH = "backdroph_path";
    }


    public static final String AUTHORITY = "com.example.aura.submission4_basisdata";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(PATH_LIST_FILM)
            .build();

    public static String getColomnString(Cursor cursor, String colomnName) {
        return cursor.getString(cursor.getColumnIndex(colomnName));
    }


    public static int getColomnInt(Cursor cursor, String colomnName) {
        return cursor.getInt(cursor.getColumnIndex(colomnName));
    }


    public static long getColomnLong(Cursor cursor, String colomnName) {
        return cursor.getLong(cursor.getColumnIndex(colomnName));
    }
}
