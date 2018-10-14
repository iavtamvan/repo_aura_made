package com.example.aura.submission4_basisdata.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aura.submission4_basisdata.helper.Config;

import static com.example.aura.submission4_basisdata.database.MovieContentProvider.MOVIE_ID;

public class FavoriteDataHelper extends SQLiteOpenHelper {
    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */

    private static final String DATABASE_NAME = "dbMovie";
    private static final int DATABASE_VERSION = 24;


//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE = "create table favorite(_id INTEGER primary key AUTOINCREMENT, id INTEGER not null, tittle text,  vote_average text null, original_language text null, overview text null, status_favorite text null);";
//        Log.d("Data", "onCreate: " + CREATE_TABLE);
//        String CREATE_TABLE_FAVORITE = "CREATE TABLE favorite (" +
//                Config.Movies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
//                Config.Movies.FIELD_ID + " INTEGER NOT NULL, " +
//                Config.Movies.FIELD_TITTLE + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_VOTE_AVERAGE + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_OVERVIEW + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_STATUS_FAVORITE + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_POSTER_PATH + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_RELEASE_DATE + " TEXT NOT NULL, " +
//                Config.Movies.FIELD_BACKDROP_PATH + " TEXT NOT NULL);";
//        db.execSQL(CREATE_TABLE_FAVORITE);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_FAVORITE = "CREATE TABLE favorite (" +
                Config.Movies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Config.Movies.ID + " INTEGER NOT NULL AUTOINCREMENT, " +
                Config.Movies.TITTLE + " TEXT NOT NULL, " +
                Config.Movies.VOTE + " TEXT NOT NULL, " +
                Config.Movies.LANGUAGE + " TEXT NOT NULL, " +
                Config.Movies.OVERVIEW + " TEXT NOT NULL, " +
                Config.Movies.STATUS_FAVORITE + " TEXT NOT NULL, " +
                Config.Movies.POSTER + " TEXT NOT NULL, " +
                Config.Movies.RELEASE_DATE + " TEXT NOT NULL, " +
                Config.Movies.BACKDROP_PATH + " TEXT NOT NULL);";
        Log.d("Data","onCreate: " + CREATE_TABLE_FAVORITE);
        db.execSQL(CREATE_TABLE_FAVORITE);
    }


//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE_FAV = "create table favorite(_id INTEGER primary key AUTOINCREMENT, id INTEGER not null, tittle text,  vote_average text null, original_language text null, overview text null, status_favorite text null);";
//        Log.d("Data", "onCreate: " + CREATE_TABLE_FAV);
//        db.execSQL(CREATE_TABLE_FAV);
//    }


//    private static final String CREATE_TABLE_FAV = String.format("CREATE TABLE %s"
//                        + "(%s INTEGER PRIMARY KEY AUTOINCEMENT, " +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL," +
//                    "%s TEXT NOT NULL)",
//            Config.Movies.TABLE_FAV,
//            Config.Movies.ID,
//            Config.Movies.ID_,
//            Config.Movies.TITTLE,
//            Config.Movies.VOTE,
//            Config.Movies.LANGUAGE,
//            Config.Movies.OVERVIEW,
//            Config.Movies.STATUS_FAVORITE,
//            Config.Movies.POSTER,
//            Config.Movies.RELEASE_DATE,
//            Config.Movies.BACKDROP_PATH
//    );


//    private static final String CREATE_TABLE_FAV = "CREATE TABLE favorite (" +
//            Config.Movies._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
//            Config.Movies.ID + " INTEGER NOT NULL, " +
//            Config.Movies.TITTLE + " TEXT NOT NULL, " +
//            Config.Movies.VOTE + " TEXT NOT NULL, " +
//            Config.Movies.LANGUAGE + " TEXT NOT NULL, " +
//            Config.Movies.OVERVIEW + " TEXT NOT NULL, " +
//            Config.Movies.STATUS_FAVORITE + " TEXT NOT NULL, " +
//            Config.Movies.POSTER + " TEXT NOT NULL, " +
//            Config.Movies.RELEASE_DATE + " TEXT NOT NULL, " +
//            Config.Movies.BACKDROP_PATH + " TEXT NOT NULL);";

    public FavoriteDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        Log.d("Data", "onCreate: " + CREATE_TABLE_FAV);
//        db.execSQL(CREATE_TABLE_FAV);
//    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Config.Movies.TABLE_FAV);
        onCreate(db);
    }

    public void deleteFavorite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(FavoriteDataHelper.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMN_MOVIEID+ "=" + id, null);
    }

}
