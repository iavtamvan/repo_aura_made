package com.example.aura.submission4_basisdata.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.aura.submission4_basisdata.helper.Config;

public class MovieContentProvider extends ContentProvider {
    public static final int ALL_MOVIE = 1;
    public static final int MOVIE_ID = 2;
    FavoriteDataHelper dbHelper;

//    private static final UriMatcher URI_MATCHER = buildurimatcher();
//
//    private static UriMatcher buildurimatcher() {
//        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_TASKS,ALL_MOVIE);
//        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_TASKS + "/#", MOVIE_ID);
//        return uriMatcher;
//    }
//
//
//    @Override
//    public boolean onCreate() {
//        Context context = getContext();
//        dbHelper = new FavoriteDataHelper(context);
//        return true;
//    }
//
//
//    @Nullable
//    @Override
//    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//        final SQLiteDatabase db = dbHelper.getReadableDatabase();
//        int match = URI_MATCHER.match(uri);
//        Cursor retCursor;
//        switch (match) {
//            case ALL_MOVIE:
//                retCursor =  db.query(Config.Movies.TABLE_FAV,
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder);
//                break;
//            default:
//                throw new UnsupportedOperationException("Unknown uri: " + uri);
//        }
//
//        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
//        return retCursor;
//    }
//
//    @Nullable
//    @Override
//    public String getType(@NonNull Uri uri) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        final SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        int match = URI_MATCHER.match(uri);
//        Uri returnUri; // URI to be returned
//
//        switch (match) {
//            case ALL_MOVIE:
//                long id = db.insert(Config.Movies.TABLE_FAV, null, values);
//                if ( id > 0 ) {
//                    returnUri = ContentUris.withAppendedId(Config.Movies.CONTENT_URI, id);
//                } else {
//                    throw new android.database.SQLException("Failed to insert row into " + uri);
//                }
//                break;
//
//            default:
//                throw new UnsupportedOperationException("Unknown uri: " + uri);
//        }
//
//        getContext().getContentResolver().notifyChange(uri, null);
//
//        return returnUri;
//    }
//
//
//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
//        int numRowsDeleted;
//
//        if (null == selection) selection = "1";
//
//        switch (URI_MATCHER.match(uri)) {
//
//            case ALL_MOVIE:
//                numRowsDeleted = dbHelper.getWritableDatabase().delete(
//                        Config.Movies.TABLE_FAV,
//                        selection,
//                        selectionArgs);
//                break;
//
//            default:
//                throw new UnsupportedOperationException("Unknown uri: " + uri);
//        }
//        if (numRowsDeleted != 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        return numRowsDeleted;
//    }
//
//    @Override
//    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
//        return 0;
//    }

//    private static final UriMatcher URI_MATCHER = buildurimatcher();
//
//    private static UriMatcher buildurimatcher() {
//        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_TASKS,ALL_MOVIE);
//        uriMatcher.addURI(Config.AUTHORITY, Config.PATH_TASKS + "/#", MOVIE_ID);
//        return uriMatcher;
//    }


//    nanti ini yg dipake
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(Config.AUTHORITY, Config.TABLE_NAME,ALL_MOVIE);
        sURIMatcher.addURI(Config.AUTHORITY, Config.TABLE_NAME + "/#", MOVIE_ID);
    }


    @Override
    public boolean onCreate() {
        Context context = getContext();
        dbHelper = new FavoriteDataHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = dbHelper.getReadableDatabase();
        int match = sURIMatcher.match(uri);
        Cursor mCursor;

        switch (match) {
            case ALL_MOVIE:
                mCursor =  db.query(Config.Movies.TABLE_FAV,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        mCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return mCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {


        int match = sURIMatcher.match(uri);

        final SQLiteDatabase db = dbHelper.getWritableDatabase();

 //       long id = 0;
        Uri returnUri; // URI to be returned

        switch (match) {
            case ALL_MOVIE:
                long id = db.insert(Config.Movies.TABLE_FAV, null, values);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(Config.Movies.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
//                id =db.insert(Config.Movies.TABLE_FAV, null,values);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
//                throw  new IllegalArgumentException("UNKNOWN URI: "+uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
//
//        return Uri.parse(Config.Movies.TABLE_FAV + "/" + id);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numRowsDeleted;

        if (null == selection) selection = "1";

        switch (sURIMatcher.match(uri)) {

            case ALL_MOVIE:
                numRowsDeleted = dbHelper.getWritableDatabase().delete(
                        Config.Movies.TABLE_FAV,
                        selection,
                        selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


//    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//
//    static {
//        sURIMatcher.addURI(Config.AUTHORITY, Config.TABLE_NAME,ALL_MOVIE);
//        sURIMatcher.addURI(Config.AUTHORITY, Config.TABLE_NAME + "/#", MOVIE_ID);
//    }
//
//    //
//    private MovieHelper movieHelper;
//
//    //buat obejct saat pertama kali
//    @Override
//    public boolean onCreate() {
//        movieHelper = new MovieHelper(getContext());
//        movieHelper.open();
//        return true;
//    }
//
//    @Nullable
//    @Override
//    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
//        Cursor cursor;
//        switch (sURIMatcher.match(uri)) {
//            case ALL_MOVIE:
//                cursor = movieHelper.queryProvider();
//                break;
//            case MOVIE_ID:
//                cursor = movieHelper.queryByIdProvider(uri.getLastPathSegment());
//                break;
//            default:
//                cursor = null;
//                break;
//        }
//
//        if (cursor != null) {
//            cursor.setNotificationUri(getContext().getContentResolver(), uri);
//        }
//
//        return cursor;
//    }
//
//    @Nullable
//    @Override
//    public String getType(@NonNull Uri uri) {
//        return null;
//    }
//
//    @Nullable
//    @Override
//    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
//        long added;
//        switch (sURIMatcher.match(uri)) {
//            case ALL_MOVIE:
//                added = movieHelper.insertProvider(values);
//                break;
//            default:
//                added = 0;
//                break;
//        }
//        if (added > 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        return Uri.parse(Config.Movies.CONTENT_URI + "/" + added);
//    }
//
//    @Override
//    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
//        int delete;
//        switch (sURIMatcher.match(uri)) {
//            case MOVIE_ID:
//                delete = movieHelper.deleteProvider(uri.getLastPathSegment());
//                break;
//            default:
//                delete = 0;
//                break;
//        }
//        if (delete > 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//        return delete;
//
//    }
//
//    @Override
//    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
//        int update;
//        switch (sURIMatcher.match(uri)) {
//            case MOVIE_ID:
//                update = movieHelper.updateProvider(uri.getLastPathSegment(), values);
//                break;
//            default:
//                update = 0;
//                break;
//        }
//        if (update > 0) {
//            getContext().getContentResolver().notifyChange(uri, null);
//        }
//
//        return update;
//    }
}
