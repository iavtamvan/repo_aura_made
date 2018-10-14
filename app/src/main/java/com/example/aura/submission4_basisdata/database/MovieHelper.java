package com.example.aura.submission4_basisdata.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import static android.provider.BaseColumns._ID;
import static com.example.aura.submission4_basisdata.helper.Config.TABLE_NAME;
import static com.example.aura.submission4_basisdata.helper.Config.Movies.TITTLE;

public class MovieHelper {
    //buat variabel yang dibutuhkan
    private static String DATABASE_TABLE = TABLE_NAME;
    private Context context;
    private FavoriteDataHelper favoriteDataHelper;

    private SQLiteDatabase database;

    public MovieHelper(Context context) {
        this.context = context;
    }

    //membuka db
    //dan supaya bisa menulis di db
    public MovieHelper open() throws SQLException {
        favoriteDataHelper = new FavoriteDataHelper(context);
        database = favoriteDataHelper.getWritableDatabase();
        return this;
    }

    //tutup koneksi
    public void close() {
        database.close();
    }

    //ambil nilai sesuai id
    public Cursor queryByIdProvider(String id) {
        return database.query(DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);

    }

    //ambil nilai sesuai id
    //query pencarian by name
    public Cursor queryByNameProvider(String name) {
        return database.query(DATABASE_TABLE,
                null,
                TITTLE + " = ?",
                new String[]{name},
                null,
                null,
                null,
                null);

    }

    //ambil semua nilai dr nilai atas sampai ke bawah
    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " DESC");
    }

    //tambah data
    public long insertProvider(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    //ubah data
    public int updateProvider(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    //hapus data
    public int deleteProvider(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
